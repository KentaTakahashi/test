package 高橋健太.JPL.ch20.ex20_07;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.Test;

public class AttrTest {

	static final String inputValue = "test Attr";
	static String outputValue;

	@BeforeClass
	public static void initialize() throws IOException {

		InputStream in = new ByteArrayInputStream(inputValue.getBytes("UTF-8"));
		DataInputStream dis = new DataInputStream(in);

		Attr attr = new Attr(dis);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		attr.writeDataOutputStream(dos);

		outputValue = new String(out.toByteArray(), "UTF-8");

		System.out.println(inputValue);
		System.out.println(outputValue);

		byte[] b = inputValue.getBytes();
		for(int i = 0; i < b.length; i ++)
			System.out.println(b[i]);

		byte[] b2 = outputValue.getBytes();
		for(int i = 0; i < b2.length; i ++)
			System.out.println(b2[i]);
	}

	@Test
	public void test() {
		assertTrue(inputValue.equals(outputValue));
	}

}
