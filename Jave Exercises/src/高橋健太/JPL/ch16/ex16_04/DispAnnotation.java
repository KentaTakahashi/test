package 高橋健太.JPL.ch16.ex16_04;

import java.lang.annotation.Annotation;

public class DispAnnotation {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			display(c);
		} catch(ClassNotFoundException e) {
			System.err.println(e); //report the error
		}
	}
	static void display(Class<?> c) {
		for(Annotation anno:c.getAnnotations())
			System.out.println(anno.toString());
	}
}
