package 高橋健太.JPL.test.test_01;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class HelloWorld {

	private static PrintStream _saved;
    private static ByteArrayOutputStream _actual;
	/**
	 * @param args
	 */
	public static void main(String[] args) {


		/* 標準出力先変更 */
		_saved = System.out;
		_actual = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(_actual))); // 標準出力先変更

		/* 改行コードテスト*/
		System.out.printf("%n");
		System.out.flush();//標準出力画面を更新

		/* 標準出力先を戻す */
		System.setOut(_saved); // 標準出力先を戻す


		byte[] asciiCodes = null;
		try {
			asciiCodes = _actual.toString().getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		for (int i = 0; i < asciiCodes.length; i++) {
            System.out.println("asciiCodes[" + i + "]:" + asciiCodes[i]);
        }
	}

}
