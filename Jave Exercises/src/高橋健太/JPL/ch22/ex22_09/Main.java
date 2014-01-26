package 高橋健太.JPL.ch22.ex22_09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	private static final int REPEAT = 10000;

	public static void main(String[] args) {
		try {
			String current_pass = new File("").getAbsolutePath();
			Readable source;

			source = new FileReader(current_pass + "\\src\\高橋健太\\JPL\\ch22\\ex22_09\\source_LongString.csv");
			System.out.println("Show Long String CSV BenchMark!");
			showBenchMark(source);

			source = new FileReader(current_pass + "\\src\\高橋健太\\JPL\\ch22\\ex22_09\\source_ShortString.csv");
			System.out.println("\nShow Short String CSV BenchMark!");
			showBenchMark(source);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void showBenchMark(Readable source) throws IOException {
		//System.out.println("MyCSVTokenizer         : " + benchmarkMyCSVTokenizer(source)          + "ms");
		System.out.println("MySimpleReaderTokenizer: " + benchmarkMySimpleReaderTokenizer(source) + "ms");
		System.out.println("MySimpleSplitTokenizer : " + benchmarkMySimpleSplitTokenizer(source)  + "ms");
		System.out.println("MyStringTokenizer      : " + benchmarkMyStringTokenizer(source)       + "ms");
	}


/*
	private static long benchmarkMyCSVTokenizer(Readable source) throws IOException {
		long start = System.currentTimeMillis();
		int i = 0;
		while(i++ < REPEAT) MyCSVTokenizer.readCSVTable(source, 4);
		long stop = System.currentTimeMillis();
		return stop - start;
	}
*/
	private static long benchmarkMySimpleReaderTokenizer(Readable source) throws IOException {
		long start = System.currentTimeMillis();
		int i = 0;
		while(i++ < REPEAT) MySimpleReaderTokenizer.readCSVTable(source, 4);
		long stop = System.currentTimeMillis();
		return stop - start;
	}
	private static long benchmarkMySimpleSplitTokenizer(Readable source) throws IOException {
		long start = System.currentTimeMillis();
		int i = 0;
		while(i++ < REPEAT) MySimpleSplitTokenizer.readCSVTable(source, 4);
		long stop = System.currentTimeMillis();
		return stop - start;
	}
	private static long benchmarkMyStringTokenizer(Readable source) throws IOException {
		long start = System.currentTimeMillis();
		int i = 0;
		while(i++ < REPEAT) MyStringTokenizer.readCSVTable(source, 4);
		long stop = System.currentTimeMillis();
		return stop - start;
	}
}
