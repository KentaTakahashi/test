package 高橋健太.JPL.ch20.ex20_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {
	static void translate(InputStream in, OutputStream out, byte from, byte to)
			throws IOException, InterruptedException {
		int b;
		while ((b = in.read()) != -1)
			out.write(b == from ? to: b);
	}

	public static void main(String[] args) {

		String current_pass = new File("").getAbsolutePath();
		System.out.println(current_pass);
		try {
			InputStream  in  = new FileInputStream(current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_01\\inFile.txt");
			OutputStream out = new FileOutputStream(current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_01\\outFile.txt");
			Byte from = 't';
			Byte to   = 'T';

			translate(in, out, from, to);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
