package 高橋健太.JPL.ch16.ex16_03;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			Set<Member> members = new TreeSet<Member>();

			System.out.println(c);

			while(c != null) {
				for(Field f:c.getFields())
					members.add(f);
				//for(Constructor<?> con:c.getConstructors())
					//members.add(con);
				for(Method m:c.getMethods())
					members.add(m);

				c = c.getGenericSuperclass().getClass();
			}
			printMembers((Member[]) members.toArray());

			//printMembers(c.getFields());
			//printMembers(c.getConstructors());
			//printMembers(c.getMethods());
		} catch(ClassNotFoundException e) {
			System.err.println(e); //report the error
		}
	}
	private static void printMembers(Member[] mems) {
		for(Member m: mems) {
			if(m.getDeclaringClass() == Object.class)
				continue;
			String decl = m.toString();
			System.out.print(" ");
			System.out.println(strip(decl, "java.lang."));
		}
	}
	private static String strip(String str, String stripStr) {
		return str.replaceAll(stripStr, "");
	}
}
