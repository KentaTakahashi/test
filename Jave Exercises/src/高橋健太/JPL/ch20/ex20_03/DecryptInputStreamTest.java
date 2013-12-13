package 高橋健太.JPL.ch20.ex20_03;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.Test;

public class DecryptInputStreamTest {

	static final String plainText = "Test Caesar cipher algorithm!";
	static String cipherText;
	static String decryptText;

	@BeforeClass
	public static void initialize() throws IOException {

		System.out.println("plainText  : " + plainText);

		byte[] b = plainText.getBytes("UTF-8");
		EncryptOutputStream encrypt = new EncryptOutputStream(null);
		encrypt.write(b, 0, b.length);

		cipherText = new String(b, "UTF-8");
		System.out.println("cipherText : " + cipherText);

		InputStream in = new ByteArrayInputStream(b);
		DecryptInputStream decrypt = new DecryptInputStream(in);

		decrypt.setKey(encrypt.getKey());
		decrypt.read(b, 0, b.length);

		decryptText = new String(b, "UTF-8");
		System.out.println("decryptText: " + decryptText);
	}


	@Test
	public void isEncrypt(){
		assertFalse(plainText.equals(cipherText));
	}

	@Test
	public void isDecrypt(){
		assertTrue(plainText.equals(decryptText));
	}
}
