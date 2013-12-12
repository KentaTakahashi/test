package 高橋健太.JPL.ch20.ex20_02;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class TranslateByte extends FilterReader{

	private byte from;
	private byte to;


	protected TranslateByte(Reader in) {
		super(in);
	}

	public TranslateByte(Reader in, byte from, byte to) {
		super(in);

		this.from = from;
		this.to   = to;
	}
	@Override
	public int read() throws IOException {
		int b  = super.read();
		return (b == from ? to: b);
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int nread = super.read(cbuf, off, len);
		int last = off + nread;
		for(int i = off; i < last; i++)
			cbuf[i] = (char) (cbuf[i] == from ? to: cbuf[i]);
		return  nread;
	}

	public static void main(String[] args) {

		Byte from = 't';
		Byte to   = 'T';

		String test = "test";
		StringReader src = new StringReader(test);
		char[] out = test.toCharArray();
		FilterReader b = new TranslateByte(src, from, to);
		try {
			b.read(out, 0, out.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(out);
	}
}
