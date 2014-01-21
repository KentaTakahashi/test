package 高橋健太.JPL.ch22.ex22_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		try {
			String current_pass = new File("").getAbsolutePath();
			Readable source = new FileReader(current_pass + "\\src\\高橋健太\\JPL\\ch22\\ex22_11\\source.csv");
			List<String[]> list = LineTokenizer.readCSVTable(source, 4);
			for(String[] string_array:list) {
				for(String s:string_array) {
					System.out.print(s);
				}
				System.out.print("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
