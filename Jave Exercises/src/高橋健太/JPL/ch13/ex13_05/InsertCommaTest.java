package 高橋健太.JPL.ch13.ex13_05;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsertCommaTest {

	@Test
	public void test_01() {
		String test = InsertComma.myInsert("1543729");
		System.out.println(test);
		assertEquals("1,543,729", test);
	}
	@Test
	public void test_02() {
		String test = InsertComma.myInsert("test1543729");
		System.out.println(test);
		assertEquals("test1,543,729", test);
	}
	@Test
	public void test_03() {
		String test = InsertComma.myInsert("1543729test");
		System.out.println(test);
		assertEquals("1,543,729test", test);
	}
	@Test
	public void test_04() {
		String test = InsertComma.myInsert("test154");
		System.out.println(test);
		assertEquals("test154", test);
	}
	@Test
	public void test_05() {
		String test = InsertComma.myInsert("154test");
		System.out.println(test);
		assertEquals("154test", test);
	}
}
