package 高橋健太.JPL.ch01.ex01_04;

public class SquareNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Title:SquareNumber Sequence");//タイトル
		for(int n = 1; n*n < 50; n++ )
			System.out.println((n*n));
	}

}
/*実行結果
Title:SquareNumber Sequence
1
4
9
16
25
36
49
*/