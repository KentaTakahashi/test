package 高橋健太.JPL.ch13.ex13_02;

public class CountString{

	static int count(String str, String search) {

		int count = 0;
		int index = 0;

		while(true) {
			index = str.indexOf(search, index);

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
