package 高橋健太.JPL.ch01.ex01_10;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	public int index;
	public int n;
	public boolean isEven;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int lo = 1;
		int hi = 1;

		//配列の宣言及び初期化
		ImprovedFibonacci[] fib = new ImprovedFibonacci[MAX_INDEX];
		//最初↓を忘れてエラーが出た → Exception in thread "main" java.lang.NullPointerException
		for(int i = 0; i < fib.length; i++) {
			fib[i] = new ImprovedFibonacci();
		}

		//初項
		fib[0].index = lo;
		fib[0].n = 1;
		fib[0].isEven = false;

		//2項目から末項まで
		for(int i = 1; i < fib.length; i++) {
			fib[i].index = i + 1;
			fib[i].n = hi;
			if(hi % 2 == 0) {
				fib[i].isEven = true;
			} else {
				fib[i].isEven = false;
			}
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	//新しいlo
		}

		//出力
		for(int i = 0; i < fib.length; i++) {
			if(fib[i].isEven) {
				System.out.println(fib[i].index + ": " + fib[i].n +  " *");
			} else {
				System.out.println(fib[i].index + ": " + fib[i].n);
			}
		}
	}

}
/*実行結果
1: 1
2: 1
3: 2 *
4: 3
5: 5
6: 8 *
7: 13
8: 21
9: 34 *
*/