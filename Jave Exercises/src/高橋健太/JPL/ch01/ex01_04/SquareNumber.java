package 高橋健太.JPL.ch01.ex01_04;

public class SquareNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int n = 1;
		System.out.println("Title:SquareNumber Sequence");//タイトル
		while(Math.pow(n, 2) < 50) {
			System.out.println((int)Math.pow(n, 2));
			n++;
		}
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