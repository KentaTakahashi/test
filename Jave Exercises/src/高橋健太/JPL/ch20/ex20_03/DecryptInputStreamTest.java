package 高橋健太.JPL.ch20.ex20_03;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class DecryptInputStreamTest {

	final String plainText = "Test Caesar cipher algorithm!";
	String cipherText;
	String decryptText;


	@Test
	public void test() throws IOException {

		byte[] b = plainText.getBytes("UTF-8");
		EncryptOutputStream encrypt = new EncryptOutputStream(null);
		encrypt.write(b, 0, b.length);

		cipherText = new String(b, "UTF-8");
		System.out.println(cipherText);

		InputStream in = new ByteArrayInputStream(b);
		DecryptInputStream decrypt = new DecryptInputStream(in);

		decrypt.setKey(encrypt.getKey());
		decrypt.read(b, 0, b.length);

		decryptText = new String(b, "UTF-8");
		System.out.println(decryptText);


	}

}
