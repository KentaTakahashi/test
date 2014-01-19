package 高橋健太.JPL.ch21.ex21_06;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Concat{
	public static void main(String[] args) throws IOException {
		Enumeration<InputStream> flies;
		InputStream in;
		if(args.length == 0) {
			in = System.in;
			int ch;
			while ((ch = in.read()) != -1)
				System.out.write(ch);
		}else {
			InputStream fileIn, bufIn;
			List<InputStream> inputs = new ArrayList<InputStream>(args.length);
			for(String arg: args) {
				fileIn = new FileInputStream(arg);
				bufIn = new BufferedInputStream(fileIn);
				inputs.add(bufIn);
			}
			flies = Collections.enumeration(inputs);

			int ch;
			while(flies.hasMoreElements()) {
				in = flies.nextElement();
				while ((ch = in.read()) != -1)
					System.out.write(ch);
			}
		}
	}
	//.....
}