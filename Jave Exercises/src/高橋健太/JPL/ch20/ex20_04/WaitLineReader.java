package 高橋健太.JPL.ch20.ex20_04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;


public class WaitLineReader extends FilterReader {

	protected WaitLineReader(Reader in) {
		super(in);
	}

	@Override
	public int read(char[] cbuf) throws IOException {

		CharBuffer cb = CharBuffer.allocate(1000);
		int r;
		while((r = read()) != -1) {
			if(r == '\n') break;//改行時は読み込み終了
			//cb.append((char)r);
			cb.append('1');
		}

		char[] ret = new char[cb.length()];
		cb.get(cbuf);
		//cbuf = ret;
		
		return cbuf.length;
	}

	public static void main(String[] args) {

		Byte from = 't';
		Byte to   = 'T';

		String test = "test\ntest";
		StringReader src = new StringReader(test);
		char[] out = new char[10000];
		FilterReader b = new WaitLineReader(src);
		try {
			b.read(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(out);
	}
}
