package 高橋健太.JPL.ch01.ex01_14;

public class Walkman {

	private Tape mTape;

	public void lesten(int terminal) {
		System.out.println("1個の端子でテープが聞ける");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Walkman mWalkman = new Walkman();
		Walkman_Ver2 mWalkman_v2 = new Walkman_Ver2();
		Walkman_Ver3 mWalkman_v3 = new Walkman_Ver3();

		System.out.println("----------Walkman_Ver1----------");
		mWalkman.lesten(1);
		System.out.println("----------Walkman_Ver2----------");
		mWalkman_v2.lesten(1);
		mWalkman_v2.lesten(1, 1);
		System.out.println("----------Walkman_Ver3----------");
		mWalkman_v3.lesten(1);
		mWalkman_v3.lesten(1, 1);
	}
}
class Walkman_Ver2 extends Walkman{
	public void lesten(int terminal_1, int terminal_2) {
		//super.lesten(terminal_1);
		System.out.println("2個の端子でテープが聞ける");
	}
}
class Walkman_Ver3 extends Walkman_Ver2{
	public void lesten(int terminal_1, int terminal_2) {
		super.lesten(terminal_1, terminal_2);
		System.out.println("双方向コミュニケーションもできる");
	}
}

/* 実行結果
 ----------Walkman_Ver1----------
1個の端子でテープが聞ける
----------Walkman_Ver2----------
1個の端子でテープが聞ける
2個の端子でテープが聞ける
----------Walkman_Ver3----------
1個の端子でテープが聞ける
2個の端子でテープが聞ける
双方向コミュニケーションもできる
*/
