package 高橋健太.JPL.ch13.ex13_01;

public class CountChar{

	static int count(String str, int ch) {

		int count = 0;
		int index = 0;

		while(true) {
			index = str.indexOf(ch, index);

			if(index < 0)
				break;//文字列が見つからなければbreak
			else {
				count++;
				index++;
				if(index == str.length()) break;//文字列の最後まで到達していればbreak
			}
		}

		return count;

	}
}
