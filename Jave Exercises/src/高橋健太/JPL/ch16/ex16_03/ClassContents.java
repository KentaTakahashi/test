package 高橋健太.JPL.ch16.ex16_03;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			//表示に重複が無いよう、Setに表示情報を格納する、Set内で比較できるようString型で統一
			Set<String> memsString = new TreeSet<String>();

			//対象クラス名の表示
			System.out.println(c);

			while(c != null) {
				for(Field f:c.getFields())
					memsString.add(f.toString());
				for(Constructor<?> con:c.getConstructors())
					memsString.add(con.toString());
				for(Method m:c.getMethods())
					memsString.add(m.toString());

				c = c.getSuperclass();
			}
			printMembers(memsString.toArray(new String[0]));
		} catch(ClassNotFoundException e) {
			System.err.println(e); //report the error
		}
	}
	private static void printMembers(String[] mems) {
		for(String m: mems) {
			System.out.print(" ");
			System.out.println(strip(m, "java.lang."));
		}
	}
	private static String strip(String str, String stripStr) {
		return str.replaceAll(stripStr, "");
	}
}
