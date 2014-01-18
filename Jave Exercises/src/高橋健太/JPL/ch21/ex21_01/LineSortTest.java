package 高橋健太.JPL.ch21.ex21_01;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class LineSortTest {

	@Test
	public void test() {
		String current_pass = new File("").getAbsolutePath();
		LineSort ls = new LineSort(current_pass + "\\src\\高橋健太\\JPL\\ch21\\ex21_01\\inFile.txt");
		List<String> l = ls.getList();
		Iterator<String> it = l.iterator();
		while(it.hasNext())
			System.out.print(it.next());
	}

}
