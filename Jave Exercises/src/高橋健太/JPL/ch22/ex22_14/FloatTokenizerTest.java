package 高橋健太.JPL.ch22.ex22_14;

import static org.junit.Assert.*;

import org.junit.Test;

public class FloatTokenizerTest {
	@Test
	public void test_1() {
		String in = "1.0 test 2.5";
		float expected = 3.5f;
		float actual   = FloatTokenizer.calc(in);
		assertEquals(expected, actual, 0.01);
	}
	@Test
	public void test_2() {
		String in = "test 3.0 test2.5test 2.5";
		float expected = 5.5f;
		float actual   = FloatTokenizer.calc(in);
		assertEquals(expected, actual, 0.01);

	}
	@Test
	public void test_3() {
		String in = "1.0 test 2.5 2555.52";
		float expected = 2559.02f;
		float actual   = FloatTokenizer.calc(in);
		assertEquals(expected, actual, 0.01);
	}

}
