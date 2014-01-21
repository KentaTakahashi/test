package 高橋健太.JPL.ch22.ex22_10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class IgnoreComment {

	private static final int CELLS = 4;

	public static List<String[]> readCSVTable(Readable source, int cell_num) throws IOException{
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String exp = "^(.*),(.*),(.*),(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		Pattern COMMENT = Pattern.compile("#.*");
		while(in.hasNextLine()) {

			if(in.hasNext(COMMENT)) {
				//無視
				in.nextLine();//改行読み飛ばし
			} else {
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
					throw new IOException("input format error");
				}

			}
		}

		IOException ex = in.ioException();
		if(ex != null)
			throw ex;
		return vals;
	}

}
