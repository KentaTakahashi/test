package 高橋健太.JPL.ch22.ex22_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import jp.ac.wakhok.tomoharu.csv.CSVTokenizer;

public class MyCSVTokenizer {

	private static final int CELLS = 4;

	public static List<String[]> readCSVTable(Readable source, int cell_num) throws IOException{

		BufferedReader bufferedreader = new BufferedReader((Reader) source);
		List<String[]> vals = new ArrayList<String[]>();

		String line;
		while((line = bufferedreader.readLine()) != null) {
			CSVTokenizer csvTokenizerTest = new CSVTokenizer(line);
			String[] cells = new String[CELLS];
			int i = 0;
			while(csvTokenizerTest.hasMoreTokens()) {
				cells[i++] = csvTokenizerTest.nextToken();
			}
			vals.add(cells);
		}
		return vals;
	}
}