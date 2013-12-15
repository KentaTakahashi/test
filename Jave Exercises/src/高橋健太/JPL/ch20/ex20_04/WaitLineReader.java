package 高橋健太.JPL.ch20.ex20_04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class WaitLineReader extends FilterReader {

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

	public static void main(String[] args) {

		String current_pass = new File("").getAbsolutePath();
		//System.out.println(current_pass);
		try {
			InputStream  in  = new FileInputStream(current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_04\\inFile.txt");
			WaitLineReader wlr = new WaitLineReader(new InputStreamReader(in));

			String str;
			while(true) {
				str = wlr.readLine();
				System.out.print(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
