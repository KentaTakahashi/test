package 高橋健太.JPL.ch20.ex20_10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class WordCounter{

	StreamTokenizer st;
	HashMap<String,Integer> map = new HashMap<String,Integer>();

	public WordCounter(Reader in) {
		st = new StreamTokenizer(in);
	}


	public void wordCount() throws IOException {
		for (int tokenType = st.nextToken();tokenType != StreamTokenizer.TT_EOF; tokenType = st.nextToken()) {
			if(st.ttype == StreamTokenizer.TT_WORD) {
				String key = st.sval;
				if(map.get(key) == null) {
					map.put(key, 1);
				}else {
					int count = map.get(key);
					map.put(key, count + 1);
				}
			}
		}


		Set<String> keySet = map.keySet();  //すべてのキー値を取得
		Iterator<String> keyIte = keySet.iterator();
		while(keyIte.hasNext()) {    //ループ。反復子iteratorによる　キー　取得
			String key = keyIte.next();
			int value = map.get(key);        //キーよりvalueを取得

			System.out.println(key + " = " + value);
		}
		//System.out.println("APPLE = " + result[0]);
	}

	public static void main(String[] args) {

		String current_pass = new File("").getAbsolutePath();
		//System.out.println(current_pass);
		try {
			InputStream  in  = new FileInputStream(current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_10\\inFile.txt");
			WordCounter wc = new WordCounter(new InputStreamReader(in));
			wc.wordCount();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
