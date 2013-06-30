package 高橋健太.JPL.ch01.ex01_07;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int lo = 1;
		int hi = 1;
		String mark;

		System.out.println(MAX_INDEX + ": " + lo);//初項

		for(int i = MAX_INDEX - 1; i >= 1; i--) {
			if(hi % 2 == 0) {
				mark = " *";
			} else {
				mark = "";
			}
			System.out.println(i + ": " + hi + mark);
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	//新しいlo
		}
	}

}
/*実行結果
9: 1
8: 1
7: 2 *
6: 3
5: 5
4: 8 *
3: 13
2: 21
1: 34 *
*/