package 高橋健太.JPL.ch01.ex01_16;

import java.io.FileInputStream;
import java.io.IOException;

public class BadException extends Exception {

	IOException e;
	String eStr;

	public BadException(IOException e) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.e = e;
		this.eStr = e.toString();
		System.out.println("eStr: " + eStr);
	}

	/**
	 * @param args
	 * @throws BadException
	 */
	public static void main(String[] args) throws BadException {
		// TODO 自動生成されたメソッド・スタブ
		MyUtilitie mUtilitie = new MyUtilitie();
		mUtilitie.getDataSet("test");

		/*実行結果
		eStr: java.io.FileNotFoundException: test.dset (指定されたファイルが見つかりません。)
		*/
	}
}

class MyUtilitie {

	public double[] getDataSet(String setName)
			throws BadException {
		String file = setName + ".dset";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch(IOException e) {
			throw new BadException(e);
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				;
			}
		}
	}


	private double[] readDataSet(FileInputStream in) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}