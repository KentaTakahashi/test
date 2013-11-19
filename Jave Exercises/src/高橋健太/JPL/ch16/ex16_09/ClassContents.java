package 高橋健太.JPL.ch16.ex16_09;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public final class ClassContents {

	public int i;
	private static String str;

	public  ClassContents(){}
	public  ClassContents(int i, int j){}

	public static void main(String[] args) {
		try {
			//Class<?> c = Class.forName(args[0]);
			Class<?> c = Class.forName("高橋健太.JPL.ch16.ex16_09.ClassContents");

			//packageme名表示
			String class_full_name = c.toString().replaceAll("class ", "");
			String[] split_str = class_full_name.split("\\.");
			String strip_str = "\\." + split_str[split_str.length - 1];
			String pak_name = class_full_name.replaceAll(strip_str , "");
			System.out.println("package " + pak_name + ";");


			//対象クラス名の表示
			System.out.println("");
			String cls_name = split_str[split_str.length - 1];
			System.out.println(addModifier(c.getModifiers()) + "class " + cls_name + " {");

			//Field宣言
			System.out.println("");
			for (Field f : c.getDeclaredFields()){
				f.setAccessible(true);
				System.out.println(
						"\t"
						+ addModifier(f.getModifiers())
						+ stripPrefix(f.getType().toString())
						+ " "
						+ stripPrefix(f.toString())
						+ ";");
			}

			//Constructor宣言
			System.out.println("");
			for (Constructor<?> cons : c.getDeclaredConstructors()){
				cons.setAccessible(true);

				//parm格納
				Type[] types = cons.getGenericParameterTypes();
				String parm = "(";
				for(Type t:types) {
					parm += stripPrefix(t.toString()) + ", ";
				}
				if(parm.length() > 2)parm = parm.substring(0, parm.length() - 2);
				parm += ")";

				System.out.println(
						"\t"
						+ addModifier(cons.getModifiers())
						+ " "
						+ stripPrefix(cons.toString())
						+ parm
						+ " {}");
			}

			//Methods宣言
			System.out.println("");
			for (Method m : c.getDeclaredMethods()){
				m.setAccessible(true);

				//parm格納
				Type[] types = m.getGenericParameterTypes();
				String parm = "(";
				for(Type t:types) {
					parm += stripPrefix(t.toString()) + ", ";
				}
				if(parm.length() > 2)parm = parm.substring(0, parm.length() - 2);
				parm += ")";

				System.out.println(
						"\t"
						+ addModifier(m.getModifiers())
						+ stripPrefix(m.getGenericReturnType().toString())
						+ " "
						+ stripPrefix(m.toString())
						+ parm
						+ " {}");
			}

			System.out.println("}");
		} catch(ClassNotFoundException e) {
			System.err.println(e); //report the error
		}
	}
	private static String strip(String str, String stripStr) {
		return str.replaceAll(stripStr, "");
	}
	private static String addModifier(int modifier) {
		String ret = "";

		if(Modifier.isPublic(modifier))
			ret += "public ";
		if(Modifier.isProtected(modifier))
			ret += "protected";
		if(Modifier.isPrivate(modifier))
			ret += "private ";
		if(Modifier.isAbstract(modifier))
			ret += "abstract ";
		if(Modifier.isStatic(modifier))
			ret += "static ";
		if(Modifier.isFinal(modifier))
			ret += "final ";
		if(Modifier.isTransient(modifier))
			ret += "transient ";
		if(Modifier.isVolatile(modifier))
			ret += "volatile ";
		if(Modifier.isSynchronized(modifier))
			ret += "synchronized ";
		if(Modifier.isNative(modifier))
			ret += "native ";
		if(Modifier.isStrict(modifier))
			ret += "strictfp ";
		if(Modifier.isInterface(modifier))
			ret += "interface ";
		return ret;
	}
	private static String stripPrefix(String str) {

		str = str.replaceAll("\\(.+?\\)", "");

		String[] strs = str.split("\\.");
		return strs[strs.length - 1];
	}
}
