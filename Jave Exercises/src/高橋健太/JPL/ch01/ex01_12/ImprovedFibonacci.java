package 高橋健太.JPL.ch01.ex01_12;

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
		String mark;

		String[] str = new String[MAX_INDEX];
		str[0] = "1: " + Integer.toString(lo);

		for(int i = 1; i < MAX_INDEX; i++) {
			if(hi % 2 == 0) {
				mark = " *";
			} else {
				mark = "";
			}
			str[i] =  Integer.toString(i + 1)
					+ ": "
					+ Integer.toString(hi)
					+ mark;
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	//新しいlo
		}

		for(int i = 0; i < MAX_INDEX; i++) {
			System.out.println(str[i]);
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