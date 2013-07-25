package 高橋健太.JPL.ch01.ex01_06;

public class Fibonacci {

	static final String TITLE = "Title:Fibonacci sequence";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int lo = 1;
		int hi = 1;
		System.out.println(TITLE);//タイトル
		System.out.println(hi);
		while(hi < 50) {
			System.out.println(hi);
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	//新しいlo
		}
	}

}
/*実行結果
Title:Fibonacci sequence
1
1
2
3
5
8
13
21
34
*/