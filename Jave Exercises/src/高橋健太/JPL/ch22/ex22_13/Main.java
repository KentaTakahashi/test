package 高橋健太.JPL.ch22.ex22_13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import 高橋健太.JPL.ch22.ex22_04.Attr;
import 高橋健太.JPL.ch22.ex22_04.Attributed;

public class Main {

	public static void main(String[] args) {
		try {
			String current_pass = new File("").getAbsolutePath();
			Reader source = new FileReader(current_pass + "\\src\\高橋健太\\JPL\\ch22\\ex22_13\\source.txt");
			Attributed attrs = ExtendsReadAttrs.readAttrs(source);

			Iterator<Attr>it = attrs.attrs();
			while(it.hasNext()) {
				Attr attr = it.next();
				System.out.println(attr.getName() + " = " + attr.getValue());
			}

				;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
