package 高橋健太.JPL.ch13.ex13_03;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyDelimitedTest extends MyDelimited {

	@Test
	public void test_01() {
		String[] str = MyDelimited.delimitedString("", ' ', ' ');
		show(str);
		assertEquals(0, str.length);
	}
	@Test
	public void test_02() {
		String[] str = MyDelimited.delimitedString("test", 't', 't');
		show(str);
		assertEquals(1, str.length);
	}
	@Test
	public void test_03() {
		String[] str = MyDelimited.delimitedString("testtest", 't', 't');
		show(str);
		assertEquals(2, str.length);
	}
	@Test
	public void test_04() {
		String[] str = MyDelimited.delimitedString("abababab", 'a', 'b');
		show(str);
		assertEquals(4, str.length);
	}
	@Test
	public void test_05() {
		String[] str = MyDelimited.delimitedString("abababab", 'a', 'c');
		show(str);
		assertEquals(0, str.length);
	}

	private void show(String[] str) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		System.out.println("----------" + ste.getMethodName() + "----------");
		for (String s: str)
			System.out.println(s);
	}
}
