package 高橋健太.JPL.ch20.ex20_03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class EncryptOutputStream extends FilterOutputStream {

	private final int key;

	public EncryptOutputStream(OutputStream out) {
		super(out);
		//RandomなKey生成
        Random rnd = new Random();
		key = rnd.nextInt();
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		int last = off + len;
		for(int i = off; i < last; i++)
			b[i] = (byte) (b[i] + key);//Caesar cipher
	}

	public int getKey() {
		return key;
	}

}
