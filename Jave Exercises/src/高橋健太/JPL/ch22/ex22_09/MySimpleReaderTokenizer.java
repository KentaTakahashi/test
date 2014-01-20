package 高橋健太.JPL.ch22.ex22_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class MySimpleReaderTokenizer {

	private static final int CELLS = 4;

	public static List<String[]> readCSVTable(Readable source, int cell_num) throws IOException{

		BufferedReader bufferedreader = new BufferedReader((Reader) source);
		List<String[]> vals = new ArrayList<String[]>();

		String line;
		while((line = bufferedreader.readLine()) != null) {
			String[] cells = new String[CELLS];
			int begin = 0;
			int end = 0;
			for(int i = 0; i < CELLS; i++) {
				end = line.indexOf(",", begin);
				if(end == -1)
					end = line.length();
				if(begin >= end)
					begin = end;
				cells[i] = line.substring(begin, end);
				begin = end + 1;
			}
			vals.add(cells);
		}
		return vals;
	}
}