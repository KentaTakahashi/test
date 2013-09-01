package 高橋健太.JPL.ch10.ex10_04;

public class SquareNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 1;
		System.out.println("Title:SquareNumber Sequence");//タイトル
		while(n*n < 50) {
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