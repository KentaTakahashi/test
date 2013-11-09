package 高橋健太.JPL.ch16.ex16_05;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.Test;

public class ClassContentsTest {
	//実行中の表示ON
	@Retention(RetentionPolicy.RUNTIME)
	public @interface StringAnnotation_1{String value();} //実行中の表示ON

	@StringAnnotation_1("StringAnnotation_1は表示される")
	class AnnoTest {
		@StringAnnotation_1("クラスメンバ")
		public int i;

		@StringAnnotation_1("コンストラクタ")
		public AnnoTest(){}

		@StringAnnotation_1("クラスメソッド")
		public void hoge(){};
	}

	@Test
	public void test() {
		String[] param = {"高橋健太.JPL.ch16.ex16_05.ClassContentsTest$AnnoTest"};
		ClassContents.main(param);
	}
}
