package 高橋健太.JPL.ch13.ex13_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountCharTest {

	@Test
	public void test_01() {
		int count = CountChar.count("", ' ');
		assertEquals(0, count);
	}
	@Test
	public void test_02() {
		int count = CountChar.count("", 'a');
		assertEquals(0, count);
	}
	@Test
	public void test_03() {
		int count = CountChar.count("a", ' ');
		assertEquals(0, count);
	}
	@Test
	public void test_04() {
		int count = CountChar.count("a", 'a');
		assertEquals(1, count);
	}
	@Test
	public void test_05() {
		int count = CountChar.count("ab", 'b');
		assertEquals(1, count);
	}
	@Test
	public void test_06() {
		int count = CountChar.count("ab", 'c');
		assertEquals(0, count);
	}
	@Test
	public void test_07() {
		int count = CountChar.count("test", 't');
		assertEquals(2, count);
	}

}
