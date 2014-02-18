package 高橋健太.JPL.ch23.ex23_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ChildeProg {
	public static void main(String[] args) {

		final BufferedReader bin  = new BufferedReader(new InputStreamReader(System.in));
		final BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(System.out));

		System.out.println("Start ChildeProg Thred");
		while(true) {
			System.out.println("Run ChildeProg Thred");
			try {
				String str;
				if((str = bin.readLine()) != null)
					bout.write("" + str);
				bout.write("ChildeProg");
				bout.write("Start ChildeProg Thred");
				Thread.sleep(10);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}