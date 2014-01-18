package 高橋健太.JPL.ch21.ex21_01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LineSort {

	private List<String> sortList = new LinkedList<String>();

	public LineSort(String path) {
		try {
			InputStream in = new FileInputStream(path);
			WaitLineReader wlr = new WaitLineReader(new InputStreamReader(in));
			String str;
			while((str = wlr.readLine()).length() != 0) {
				insertList(str);
			}
			wlr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getList() {
		return sortList;
	}

	private void insertList(String insert) {

		//Listが空の場合、先頭に追加する
		if(sortList.size() ==0) {
			sortList.add(insert);
			return;
		}

		//Listに要素がある場合、insert箇所を検索し挿入する
		//メモ：Sort済みListなので2分木探索の方が本当は速いはずだが、簡単のため先頭からの逐次探索を実装する
		ListIterator<String> it = sortList.listIterator();
		String preStr = "";
		String proStr = "";
		while(it.hasNext()) {
			preStr = proStr;
			proStr = it.next();
			if(0 <= insert.compareTo(preStr) && insert.compareTo(proStr) < 0) {
				it.previous();
				it.add(insert);
				//System.out.println(preStr + " <= " + insert + " < " + proStr);
				return;
			}
		}

		//insert箇所がなければ、末尾に追加する
		it.add(insert);
	}

	class WaitLineReader extends FilterReader {

		protected WaitLineReader(Reader in) {
			super(in);
		}
		public String readLine() throws IOException {

	        String new_line = System.getProperty("line.separator");
	        StringBuffer sb = new StringBuffer();

	        int r;
	        while ((r = read()) != -1) {
	            sb.append((char)r);
	            if(sb.toString().endsWith(new_line))
	            	break;
	        }
	        return sb.toString();
	    }

	}
}