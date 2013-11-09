package 高橋健太.JPL.ch16.ex16_05;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);

			//対象クラス名の表示
			System.out.println(c);

			printMembers(c.getFields());
			printMembers(c.getConstructors());
			printMembers(c.getMethods());
		} catch(ClassNotFoundException e) {
			System.err.println(e); //report the error
		}
	}
	private static void printMembers(Member[] mems) {
		for(Member m: mems) {
			if(m.getDeclaringClass() == Object.class)
				continue;
			String decl = m.toString();
			for(Annotation anno: ((AnnotatedElement) m).getAnnotations())
				System.out.println(" " + anno.toString());

			System.out.print(" ");
			System.out.println(strip(decl, "java.lang."));
		}
	}
	private static String strip(String str, String stripStr) {
		return str.replaceAll(stripStr, "");
	}
}
