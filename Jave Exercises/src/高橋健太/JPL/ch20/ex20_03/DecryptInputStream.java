package 高橋健太.JPL.ch20.ex20_03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {

	private int key;

	public DecryptInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read(byte[] b,int off, int len) throws IOException {
		int nread = super.read(b, off, len);
		int last = off + nread;
		for(int i = off; i < last; i++)
			b[i] = (byte) (b[i] - key);//Caesar cipher
		return nread;
	}

	public void setKey(int key) {
		this.key = key;
	}

}
