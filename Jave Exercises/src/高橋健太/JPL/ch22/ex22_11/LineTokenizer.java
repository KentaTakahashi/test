package 高橋健太.JPL.ch22.ex22_11;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class LineTokenizer {

	private static final int CELLS = 4;

	public static List<String[]> readCSVTable(Readable source, int cell_num) throws IOException{
		StreamTokenizer in = new StreamTokenizer((Reader)source);

		in.whitespaceChars(',', ',');	//「,」をトークンの区切りとして登録
		in.eolIsSignificant(true);		//行の終わりをトークンとして処理する

		List<String[]> vals = new ArrayList<String[]>();
		String[] cells = new String[CELLS];
		int i = 0;
		while(in.nextToken() != StreamTokenizer.TT_EOF) {

			if(in.ttype == StreamTokenizer.TT_WORD)
				cells[i++] = in.sval;

			if(in.ttype == StreamTokenizer.TT_EOL) {
				vals.add(cells);
				i = 0;
				cells = new String[CELLS];//初期化
			}

		}
		return vals;
	}

}
