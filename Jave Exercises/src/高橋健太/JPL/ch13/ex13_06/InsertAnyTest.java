package 高橋健太.JPL.ch13.ex13_06;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsertAnyTest {

	@Test
	public void test_01() {
		String test = InsertAny.myInsert("1543729", ',', 3);
		System.out.println(test);
		assertEquals("1,543,729", test);
	}
	@Test
	public void test_02() {
		String test = InsertAny.myInsert("test1543729", ',', 3);
		System.out.println(test);
		assertEquals("test1,543,729", test);
	}
	@Test
	public void test_03() {
		String test = InsertAny.myInsert("1543729test", ',', 3);
		System.out.println(test);
		assertEquals("1,543,729test", test);
	}
	@Test
	public void test_04() {
		String test = InsertAny.myInsert("test154", ',', 3);
		System.out.println(test);
		assertEquals("test154", test);
	}
	@Test
	public void test_05() {
		String test = InsertAny.myInsert("154test", ',', 3);
		System.out.println(test);
		assertEquals("154test", test);
	}
	@Test
	public void test_06() {
		String test = InsertAny.myInsert("154test", '*', 1);
		System.out.println(test);
		assertEquals("1*5*4test", test);
	}
	@Test
	public void test_07() {
		String test = InsertAny.myInsert("15437291543729", '\t', 4);
		System.out.println(test);
		assertEquals("15	4372	9154	3729", test);
	}
}
