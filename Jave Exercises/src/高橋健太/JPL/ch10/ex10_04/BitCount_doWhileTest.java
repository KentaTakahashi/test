package 高橋健太.JPL.ch10.ex10_04;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitCount_doWhileTest {

	@Test
	public void test_1() {
		int i = 0;
		assertEquals(Integer.bitCount(i),BitCount_doWhile.count(i));
	}
	public void test_2() {
		int i = Integer.MAX_VALUE;
		assertEquals(Integer.bitCount(i),BitCount_doWhile.count(i));
	}
	public void test_3() {
		int i = Integer.MIN_VALUE;
		assertEquals(Integer.bitCount(i),BitCount_doWhile.count(i));
	}
}
