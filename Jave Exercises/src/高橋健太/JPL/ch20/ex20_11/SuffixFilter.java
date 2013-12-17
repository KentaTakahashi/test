package 高橋健太.JPL.ch20.ex20_11;

import java.io.File;
import java.io.FilenameFilter;

public class SuffixFilter implements FilenameFilter{

	private final String suffix;

	public SuffixFilter(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(suffix);
	}

	public void printFiles() {

	}

	public static void main(String[] args) {

		String current_pass = new File("").getAbsolutePath();
		//System.out.println(current_pass);
		File dir = new File(current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_11");
		String suffix = "txt";
		String[] files = dir.list(new SuffixFilter(suffix));
		for(String file: files)
			System.out.println(file);
	}
}
