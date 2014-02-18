package 高橋健太.JPL.ch23.ex23_01;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class UserProgTest {

	@Test
	public void test() {
		try {

			String current_pass = new File("").getAbsolutePath();
			System.out.println(current_pass);

			Runtime rt = Runtime.getRuntime();
			Process proc = UserProg.userProg("java -classpath bin 高橋健太.JPL.ch23.ex23_01.ChildeProg");
			current_pass = new File("").getAbsolutePath();
			System.out.println(current_pass);

			proc.waitFor();
			while(true){}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
