package 高橋健太.JPL.ch13.ex13_03;

import java.util.ArrayList;
import java.util.List;

public class MyDelimited{

	public static String[] delimitedString(String from, char start, char end) {

		List<String> list = new ArrayList<String>();

		int startPos = 0;

		while(true) {
			startPos = from.indexOf(start, startPos);

			if(startPos < 0)
				break;//開始文字が見つからなければbreak
			else {
				int endPos = from.indexOf(end, startPos + 1);

				if(endPos < 0)
					break;//終了文字が見つからなければbreak
				else {
					String subStr = from.substring(startPos, endPos + 1); //開始文字と終了文字が見つかった
					list.add(subStr);
				}

				startPos = endPos + 1;
				if(startPos >= from.length()) break;//文字列の最後まで到達していればbreak
			}
		}

		return (String[]) list.toArray(new String[]{});

	}
}
