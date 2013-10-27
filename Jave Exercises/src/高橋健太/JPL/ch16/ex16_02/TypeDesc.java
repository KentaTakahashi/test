package 高橋健太.JPL.ch16.ex16_02;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeDesc {
	public static void main(String[] args) {
		for(String name : args) {
			try {
				Class<?> startClass = Class.forName(name);
				TypeDesc.printType(startClass, 0, basic);
			} catch(ClassNotFoundException e) {
				System.err.println(e); //report the error
			}
		}
	}

	//デフォルト出力で標準出力に表示する
	private static java.io.PrintStream out = System.out;

	//型名にラベル付けするprintType()で使用される
	public static String[]
			basic = {"class", "interface", "enum", "annotation"},
			supercl = {"extends", "implements"},
			iFace = {null, "extends"};

	public static void printType(Type type, int depth, String[] labels) {
		if(type == null)//再起呼び出し停止：スーパータイプが存在しない
			return;

		//Type を Class オブジェクトに変換する
		Class<?> cls = null;
		if(type instanceof Class<?>)
			cls = (Class<?>)type;
		else if(type instanceof ParameterizedType)
			cls = (Class<?>) ((ParameterizedType)type).getRawType();
		else
			throw new Error("Unexpected non-class type");

		//オブジェクト型であれば冗長なので表示しない
		if(cls.getCanonicalName().equals("java.lang.Object"))
			return;

		//この型を表示
		for(int i = 0; i < depth; i++)
			out.print(" ");

		int kind =
				cls.isAnnotation() ? 3:
					cls.isEnum() ? 2:
						cls.isInterface() ? 1:0;
		out.print(labels[kind] + " ");
		out.print(cls.getCanonicalName());

		//あれば、ジェネリックス型のパラメータを表示
		TypeVariable<?>[] params = cls.getTypeParameters();
		if(params.length > 0) {
			String str = "<";
			for(TypeVariable<?> param: params) {
				str += param.getName();
				str += ", ";
			}
			str = str.substring(0, str.length() - 2);
			str += ">\n";
			out.print(str);
		}else
			out.println();

		//このクラスが実装しているすべてのインタフェースを表示
		Type[] interfaces = cls.getGenericInterfaces();
		for(Type iface: interfaces)
			printType(iface, depth + 1, cls.isInterface() ? iFace: supercl);

		//スーパークラスに対して再帰
		printType(cls.getGenericSuperclass(), depth + 1, supercl);

		//このクラスがネスとした型であれば、どの型の中にネストしているかを表示
		Class<?> enc = cls.getEnclosingClass();
		if((enc != null) && (depth == 0))
			out.print(" enclosing " + enc.getCanonicalName());
	}
}
