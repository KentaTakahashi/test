package 高橋健太.JPL.ch13.ex13_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountStringTest {

	@Test
	public void test_01() {
		int count = CountString.count("", " ");
		assertEquals(0, count);
	}
	@Test
	public void test_02() {
		int count = CountString.count("", "a");
		assertEquals(0, count);
	}
	@Test
	public void test_03() {
		int count = CountString.count("a", " ");
		assertEquals(0, count);
	}
	@Test
	public void test_04() {
		int count = CountString.count("a", "a");
		assertEquals(1, count);
	}
	@Test
	public void test_05() {
		int count = CountString.count("ab", "b");
		assertEquals(1, count);
	}
	@Test
	public void test_06() {
		int count = CountString.count("ab", "c");
		assertEquals(0, count);
	}
	@Test
	public void test_07() {
		int count = CountString.count("test", "t");
		assertEquals(2, count);
	}
	@Test
	public void test_08() {
		int count = CountString.count("test", "te");
		assertEquals(1, count);
	}
	@Test
	public void test_09() {
		int count = CountString.count("test", "est");
		assertEquals(1, count);
	}
	@Test
	public void test_10() {
		int count = CountString.count("test", "test");
		assertEquals(1, count);
	}
	@Test
	public void test_11() {
		int count = CountString.count("testtest", "test");
		assertEquals(2, count);
	}
	@Test
	public void test_12() {
		int count = CountString.count("abababab", "abab");
		assertEquals(3, count);
	}
}
