package 高橋健太.JPL.ch02.ex02_15;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		//引数ありのコンストラクタ、所有者をAliceと設定
		Vehicle test = new Vehicle();

		//テスト対象関数
		test.changeSpeed(30);
		assertEquals("changeSpeedメソッドがただしく実装されてない", 30, test.getSpeed());

		test.stop();
		assertEquals("stopメソッドがただしく実装されてない", 0, test.getSpeed());
	}

}
