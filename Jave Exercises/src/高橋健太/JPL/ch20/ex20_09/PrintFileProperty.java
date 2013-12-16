package 高橋健太.JPL.ch20.ex20_09;

import java.io.File;


public class PrintFileProperty{
	public static void printProperty(String... pass) {
		int length = pass.length;
		int index = 0;
		while(index < length) {
			File src = new File(pass[index]);
			System.out.println("getName			= " + src.getName());
			System.out.println("getParent		= " + src.getParent());
			System.out.println("getPath			= " + src.getPath());
			System.out.println("canExecute		= " + src.canExecute());
			System.out.println("canRead			= " + src.canRead());
			System.out.println("canWrite		= " + src.canWrite());
			System.out.println("exists			= " + src.exists());
			System.out.println("getAbsolutePath	= " + src.getAbsolutePath());
			System.out.println("getFreeSpace	= " + src.getFreeSpace());
			System.out.println("getTotalSpace	= " + src.getTotalSpace());
			System.out.println("getUsableSpace	= " + src.getUsableSpace());
			System.out.println("hashCode		= " + src.hashCode());
			System.out.println("isAbsolute		= " + src.isAbsolute());
			System.out.println("isDirectory		= " + src.isDirectory());
			System.out.println("isFile			= " + src.isFile());
			System.out.println("isHidden		= " + src.isHidden());
			System.out.println("lastModified	= " + src.lastModified());
			System.out.println("length			= " + src.length());

			index++;
		}
	}

	public static void main(String[] args) {

		String current_pass = new File("").getAbsolutePath();
		String in_1 = current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_09\\inFile.txt";
		String in_2 = current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_09\\inFile2.txt";
		printProperty(in_1, in_2);
	}
}
