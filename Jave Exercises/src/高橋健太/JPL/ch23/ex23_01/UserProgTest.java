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
			//rt.exec(new String[]{"cmd.exe","/c","start"});

			//Process proc = UserProg.userProg("java ChildeProg.class");
			//Process proc = UserProg.userProg("cd " + "\\bin\\高橋健太\\JPL\\ch20\\ex20_04\\ChildeProg");
			//Process proc = UserProg.userProg("cmd.exe \\k");
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
