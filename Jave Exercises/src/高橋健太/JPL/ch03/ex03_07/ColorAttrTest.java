package 高橋健太.JPL.ch03.ex03_07;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColorAttrTest {

	ScreenColor sc_1 = new ScreenColor(null);
	ScreenColor sc_2 = new ScreenColor(null);

	ColorAttr test_1 = new ColorAttr(null, sc_1);
	ColorAttr test_2 = new ColorAttr(null, sc_2);
	ColorAttr test_3 = new ColorAttr(null, sc_1);

	@Test
	public void equals_test() {
		assertEquals("equalsメソッドがただしく実装されてない", false, test_1.equals(test_2));
		assertEquals("equalsメソッドがただしく実装されてない", true, test_1.equals(test_3));
	}
	public void hashCode_test() {
		assertEquals("hashCodeメソッドがただしく実装されてない", false, test_1.hashCode()==test_2.hashCode());
		assertEquals("hashCodeメソッドがただしく実装されてない", true, test_1.hashCode()==test_3.hashCode());
	}

}
