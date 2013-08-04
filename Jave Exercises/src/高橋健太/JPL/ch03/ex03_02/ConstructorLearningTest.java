package 高橋健太.JPL.ch03.ex03_02;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConstructorLearningTest {

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
		ConstructorLearning.main(null);//mainメソッド呼び出し
		System.out.flush();//標準出力画面を更新

		String sOut 	= _actual.toString();
		String expected = "" +
				"Step 4: xMaxk = 00ff, yMaxk = 0000, fullMask = 0000\r\n" +
				"Step 5: xMaxk = 00ff, yMaxk = 0000, fullMask = 00ff\r\n" +
				"Step 6: xMaxk = 00ff, yMaxk = ff00, fullMask = 00ff\r\n" +
				"Step 7: xMaxk = 00ff, yMaxk = ff00, fullMask = ffff\r\n";

		assertEquals("mainメソッドがただしく実装されてない", expected, sOut);
	}

}
