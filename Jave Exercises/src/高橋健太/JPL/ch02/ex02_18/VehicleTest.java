package 高橋健太.JPL.ch02.ex02_18;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VehicleTest {

	private PrintStream _saved;
    private ByteArrayOutputStream _actual;

	@Before
    public void setUp() throws Exception {

		_saved = System.out;
		_actual = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(_actual))); // 標準出力先変更
    }

    @After
    public void tearDown() throws Exception {
		System.setOut(_saved); // 標準出力先を戻す
    }
	@Test
	public void test() {
		String[] str = {"Alice"};
		Vehicle.main(str);//Owner:Aliceを引数にmainメソッド呼び出し
		System.out.flush();//標準出力画面を更新

		String sOut 	= _actual.toString();
		String expected = "ID:0, Speed:0, Direction:0, Owner:Alice\r\n";//テスト環境に合わせて？復帰改行を追記した

		assertEquals("mainメソッドがただしく実装されてない", expected, sOut);
	}

}
