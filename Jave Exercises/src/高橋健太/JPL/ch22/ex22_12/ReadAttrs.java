package 高橋健太.JPL.ch22.ex22_12;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import 高橋健太.JPL.ch22.ex22_04.Attr;
import 高橋健太.JPL.ch22.ex22_04.Attributed;
import 高橋健太.JPL.ch22.ex22_04.AttributedImpl;

public class ReadAttrs {

	public static Attributed readAttrs(Reader source) throws IOException{
		Scanner in = new Scanner(source);
		AttributedImpl attrs = new AttributedImpl();
		Attr attr = null;

		String exp = "^(.*)=(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);

		while(in.hasNextLine()) {
			String line = in.findInLine(pat);
			if(line != null) {
				MatchResult match = in.match();
				attr = new Attr(match.group(1), match.group(2));
				attrs.add(attr);
				in.nextLine();//改行読み飛ばし
			} else {
				throw new IOException("input format error");
			}
		}
		return attrs;

	}

}
