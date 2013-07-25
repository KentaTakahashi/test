package 高橋健太.JPL.ch02.ex02_09;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		//引数ありのコンストラクタ、所有者をAliceと設定
		Vehicle test_1 = new Vehicle("Alice");//ID=0
		Vehicle test_2 = new Vehicle("Bob");//ID=1
		Vehicle test_3 = new Vehicle("Carol)");//ID=2

		int ret = Vehicle.getMaxID();
		assertEquals("最大のIDが2でない", 2, ret);
	}

}
