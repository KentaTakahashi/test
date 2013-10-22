package 高橋健太.JPL.ch13.ex13_05;

import java.util.regex.Pattern;

public class InsertComma{

	public static String myInsert(String input) {

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

			//検索対象が4回連続したら、適切な箇所にカンマをinsertし、numCountを減らす
			if(numCount == 4) {
				str.insert(index + 1, ",");
				numCount -= 3;
			}
		}
		return str.toString();
	}

	public static void main(String[] args) {
		String test = "a1234567890agajwo124054";

		System.out.println(myInsert(test));
	}
}
