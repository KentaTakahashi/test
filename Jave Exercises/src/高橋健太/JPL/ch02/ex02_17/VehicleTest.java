package 高橋健太.JPL.ch02.ex02_17;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		//引数ありのコンストラクタ、所有者をAliceと設定
		Vehicle test = new Vehicle();

		//テスト対象関数
		test.turn(30);
		assertEquals("turnメソッドがただしく実装されてない", 30, test.getDirection());

		test.turn(Vehicle.TURN_LEFT);
		assertEquals("turnメソッドがただしく実装されてない", 90, test.getDirection());
		test.turn(Vehicle.TURN_RIGHT);
		assertEquals("turnメソッドがただしく実装されてない", -90, test.getDirection());
	}

}
