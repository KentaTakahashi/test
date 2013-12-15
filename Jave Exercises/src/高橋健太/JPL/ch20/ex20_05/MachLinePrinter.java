package 高橋健太.JPL.ch20.ex20_05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;


public class MachLinePrinter{

	LineNumberReader lnr;

	public MachLinePrinter(Reader in) {
		lnr = new LineNumberReader(in);
		lnr.setLineNumber(1);
	}


	public void printMachLine(String match) throws IOException {
		while (lnr.ready()) {
			String readLine = lnr.readLine();
			if (readLine.indexOf(match) != -1) {
				System.out.println((lnr.getLineNumber() -1) +  " : " +readLine);
			}
		}
	}

	public static void main(String[] args) {

		String current_pass = new File("").getAbsolutePath();
		//System.out.println(current_pass);
		try {
			InputStream  in  = new FileInputStream(current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_05\\inFile.txt");
			MachLinePrinter mlp = new MachLinePrinter(new InputStreamReader(in));
			mlp.printMachLine("test");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
