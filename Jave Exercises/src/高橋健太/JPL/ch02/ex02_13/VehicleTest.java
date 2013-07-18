package 高橋健太.JPL.ch02.ex02_13;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		//引数ありのコンストラクタ、所有者をAliceと設定
		Vehicle test = new Vehicle();

		//テスト対象関数
		test.setSpeed(30);
		test.setDirection(90);
		test.setOwner("testOwner");

		String expected = "ID:0, Speed:30, Direction:90, Owner:testOwner";

		assertEquals("setメソッドがただしく実装されてない", expected, test.toString());
	}

}
