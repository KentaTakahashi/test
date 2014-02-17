package 高橋健太.JPL.ch23.ex23_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MyExec {
	public static void main(String[] args) {
		try {
			//コマンドを開始する
			Process child = Runtime.getRuntime().exec(args);
			InputStream childOut = child.getInputStream();
			InputStreamReader r = new InputStreamReader(childOut);
			BufferedReader in = new BufferedReader(r);

			//コマンドの出力を読み込み、標準出力に書き込む
			String line;
			int n = 0;//出力行
			while((line = in.readLine()) != null) {
				System.out.printf("%03d: %s\n", n, line);
				n++;
			}
			if(child.waitFor() != 0) //コマンド実行失敗
				throw new Exception();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}