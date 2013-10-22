package 高橋健太.JPL.ch13.ex13_06;

import java.util.regex.Pattern;

public class InsertAny{

	public static String myInsert(String input, char insertChar, int digit) {

		if(digit <= 0) return input;//区切り桁数が正でない場合、inputをそのまま返す

		StringBuilder str = new StringBuilder(input);//insert処理を行いたいのでStringBuilderに変換
		int numCount = 0;//連続した数字をカウントする
		int index = str.length();//insertポイント検索用のインデックス、後方から検索する
		String _0to9 = "[0-9]";//検索対象の正規表現

		//インデックスが先頭文字に到達するまでデクリメントして検索する
		while(index-- > 0) {

			//index文字が正規表現に一致した場合、numCountをインクリメント、そうでない場合リセットする
			if(Pattern.matches(_0to9, str.subSequence(index, index + 1)))
				numCount++;
			else
				numCount = 0;

			//検索対象がdigit+1回連続したら、適切な箇所にinsertCharをinsertし、numCountを減らす
			if(numCount == digit + 1) {
				str.insert(index + 1, insertChar);
				numCount -= digit;
			}
		}
		return str.toString();
	}
}
