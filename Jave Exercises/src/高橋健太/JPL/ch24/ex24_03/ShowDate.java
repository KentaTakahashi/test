package 高橋健太.JPL.ch24.ex24_03;

import java.text.DateFormat;
import java.text.ParseException;

public class ShowDate {
	public static void main(String[] args) throws ParseException {
		//http://www.localeplanet.com/java/
		if(args.length == 0)
			throw new IllegalArgumentException("表示したい日付を引数に設定して下さい");
		String input = "";

		//引数が複数あれば、連結した文字列を解析することとする
		for(int i = 0; i < args.length; i++)
			input += args[i];

			System.out.println(DateFormat.getDateInstance(DateFormat.SHORT ).parse(input));
			System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM).parse(input));
			System.out.println(DateFormat.getDateInstance(DateFormat.LONG  ).parse(input));
			System.out.println(DateFormat.getDateInstance(DateFormat.FULL  ).parse(input));
	}
}
