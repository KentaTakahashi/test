package 高橋健太.JPL.ch02.ex02_10;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		//引数ありのコンストラクタ、所有者をAliceと設定
		Vehicle test = new Vehicle("Alice");

		String expected = "ID:0, Speed:0, Direction:0, Owner:Alice";

		assertEquals("toStringメソッドがただしく実装されてない", expected, test.toString());
	}

}
