package 高橋健太.JPL.ch10.ex10_04;

public class BitCount_doWhile {

	static public int count(int input) {
		int cnt = 0;
		int i = 0;
		do {
			cnt += (input%2);
			input = input >> 1;
			i++;
		}while(i < Integer.SIZE - 1);
		return cnt;
	}
}
