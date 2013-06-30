package 高橋健太.JPL.ch01.ex01_09;

public class Fibonacci {

	static final int MAX_INDEX = 9;
	static int[] seq = new int[MAX_INDEX];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int lo = 1;
		int hi = 1;
		System.out.println("Title:Fibonacci Sequence");//タイトル
		seq[0] = lo;
		for(int i = 1; i < MAX_INDEX; i++ ){
			seq[i] = hi;
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	//新しいlo
		}
		for(int i = 0; i < seq.length; i++ ){
			System.out.println(seq[i]);
		}
	}

}
/*実行結果
Title:Fibonacci Sequence
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