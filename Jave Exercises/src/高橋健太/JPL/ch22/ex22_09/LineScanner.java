package 高橋健太.JPL.ch22.ex22_09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class LineScanner{

	private static final int CELLS = 4;

	public static List<String[]> readCSVTable(Readable source, int cell_num) throws IOException{
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String exp = "^(.*),(.*),(.*),(.*)";
		String illegal = "^(.*),(.*),(.*),(.*),(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		Pattern illegal_pat = Pattern.compile(illegal, Pattern.MULTILINE);
		while(in.hasNextLine()) {

			//5個以上あったら例外処理
			if(in.findInLine(illegal_pat) != null)
				throw new IOException("input format error");

			String line = in.findInLine(pat);
			if(line != null) {
				String[] cells = new String[CELLS];
				MatchResult match = in.match();
				for(int i = 0; i < CELLS; i++) {
					cells[i] = match.group(i+1);
				}
				vals.add(cells);
				in.nextLine();//改行読み飛ばし
			} else {
				//throw new IOException("input format error");
				in.nextLine();//改行読み飛ばし
			}
		}

		IOException ex = in.ioException();
		if(ex != null)
			throw ex;
		return vals;
	}
}