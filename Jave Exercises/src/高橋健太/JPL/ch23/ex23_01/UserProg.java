package 高橋健太.JPL.ch23.ex23_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;


public class UserProg {
	public static Process userProg(String cmd) throws IOException {
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
	}
	private static void plugTogether(final InputStream in, final OutputStream out) {
		final BufferedReader bin  = new BufferedReader(new InputStreamReader(in));
		final BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(out));

		new Thread () {
			public void run () {
				System.out.println("Start plugTogether Thred");
				while(true) {
					try {
						String str;
						if((str = bin.readLine()) != null)
							bout.write(str);
						//bout.flush();
						//System.out.println("debug UserProg: " + str);
						Thread.sleep(10);
					} catch (IOException e) {
						System.out.println("IOException: " + e.getMessage());
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start ();
	}
	private static void plugTogether(final PrintStream out, final InputStream in) {
		final BufferedReader bin  = new BufferedReader(new InputStreamReader(in));
		final BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(out));
		new Thread () {
			public void run () {
				while(true) {
					try {
						String str;
						if((str = bin.readLine()) != null) {
							bout.write(str);
							//System.out.println("debug UserProg 2: " + str);
						}
						//bout.flush();
						Thread.sleep(10);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start ();
	}
}