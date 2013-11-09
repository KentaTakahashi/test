package 高橋健太.JPL.ch16.ex16_04;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.Test;

public class DispAnnotationTest {

	//実行中の表示ON
	@Retention(RetentionPolicy.RUNTIME)
	public @interface StringAnnotation_1{String value();} //実行中の表示ON

	//実行中の表示OFF
	public @interface StringAnnotation_2{String value();}

	//実行中の表示ON
	@Retention(RetentionPolicy.RUNTIME)
	public @interface StringAnnotation_3{String value();}

	@StringAnnotation_1("StringAnnotation_1は表示される")
	@StringAnnotation_2("StringAnnotation_2は表示されない")
	@StringAnnotation_3("StringAnnotation_3は表示される")
	class AnnoTest{}

	@Test
	public void test() {
		AnnoTest a = new AnnoTest();
		Class<?> c = a.getClass();
		DispAnnotation.display(c);

		//以下が表示される
		//@高橋健太.JPL.ch16.ex16_04.DispAnnotationTest$StringAnnotation_1(value=StringAnnotation_1は表示される)
		//@高橋健太.JPL.ch16.ex16_04.DispAnnotationTest$StringAnnotation_3(value=StringAnnotation_3は表示される)

	}

}
