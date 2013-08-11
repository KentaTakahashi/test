package 高橋健太.JPL.ch03.ex03_06;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void start() {
		Vehicle test_1 = new Vehicle(0, 0, 0, null);
		Vehicle test_2 = new Vehicle(1, 0, 0, null);
		Vehicle test_3 = new Vehicle(-1, 0, 0, null);

		assertEquals("startメソッドがただしく実装されてない", false, test_1.start());
		assertEquals("startメソッドがただしく実装されてない", true, test_2.start());
		assertEquals("startメソッドがただしく実装されてない", true, test_3.start());
	}

}
