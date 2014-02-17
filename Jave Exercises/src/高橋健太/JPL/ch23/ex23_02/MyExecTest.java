package 高橋健太.JPL.ch23.ex23_02;

import java.io.File;

import org.junit.Test;

public class MyExecTest {

	@Test
	public void test() {
		String current_pass = new File("").getAbsolutePath();
		System.out.println(current_pass);

		Runtime rt = Runtime.getRuntime();
		String[] args = new String[4];
		args[0] = "java";
		args[1] = "-classpath";
		args[2] = "bin";
		args[3] = "高橋健太.JPL.ch23.ex23_02.ChildeProg";
		//args[0] = "cmd.exe";
		MyExec.main(args);
	}

}
