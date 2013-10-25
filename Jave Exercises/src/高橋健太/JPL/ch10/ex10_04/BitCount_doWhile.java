package 高橋健太.JPL.ch10.ex10_04;

public class BitCount_doWhile {

	static public int count(int input) {
		int cnt = 0;
		int i = 0;
		do {
			if((input & 0x01) == 0x01)
				cnt++;
			input = input >> 1;
			i++;
		}while(i < Integer.SIZE);
		return cnt;
	}
}
