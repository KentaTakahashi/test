package 高橋健太.JPL.ch02.ex02_03;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		Vehicle test_1 = new Vehicle();
		Vehicle test_2 = new Vehicle();

		assertEquals("test_1のIDが0でない", 0, test_1.getID());
		assertEquals("test_1のIDが1でない", 1, test_2.getID());
	}

}
