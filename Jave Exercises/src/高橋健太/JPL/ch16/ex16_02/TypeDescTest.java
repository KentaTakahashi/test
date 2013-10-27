package 高橋健太.JPL.ch16.ex16_02;

import org.junit.Test;

public class TypeDescTest {

	Inner inner = new Inner();
	public class Inner extends java.util.HashMap{}


	@Test
	public void test() {
		TypeDesc.printType(inner.getClass(), 0, TypeDesc.basic);

		//テスト未実装、上記の関数呼び出しで以下の出力が出ることを確認できればよい
		/*
			class 高橋健太.JPL.ch16.ex16_02.TypeDescTest.Inner
			 extends java.util.HashMap<K, V>
			  implements java.util.Map<K, V>
			  implements java.lang.Cloneable
			  implements java.io.Serializable
			  extends java.util.AbstractMap<K, V>
			   implements java.util.Map<K, V>
			 enclosing 高橋健太.JPL.ch16.ex16_02.TypeDescTest
		 */
	}

}
