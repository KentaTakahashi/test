package 高橋健太.JPL.ch09.ex09_02;

public class BitCount {

	static public int count(int input) {
		int cnt = 0;
		for(int i = 0; i < Integer.SIZE; i++) {
			if((input & 0x01) == 0x01)
				cnt++;
			input = input >> 1;
		}
		return cnt;
	}
}
