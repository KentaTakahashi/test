package 高橋健太.JPL.ch16.ex16_11;

import java.io.FileInputStream;
import java.io.IOException;

public class PlayerLoader extends ClassLoader{

	static final String LocalPass = "C:\\Users\\z00s000704\\git\\test\\Jave Exercises\\bin\\高橋健太\\JPL\\ch16\\ex16_11";

	@Override
	protected Class<?> findClass(String name)
			throws ClassNotFoundException {
		try {
			byte[] buf = bytesForClass(name);
			return defineClass(name, buf, 0, buf.length);
		} catch (IOException e) {
			throw new ClassNotFoundException(e.toString());
		}
	}

	private byte[] bytesForClass(String name)
			throws IOException, ClassNotFoundException {
		FileInputStream in = null;
		try {
			in = streamFor(name + ".class");
			int length = in.available();
			if(length == 0)
				throw new ClassNotFoundException(name);
			byte[] buf = new byte[length];
			in.read(buf);
			return buf;
		} finally {
			if(in != null)
				in.close();
		}
	}

	private FileInputStream streamFor(String string) throws IOException {
		System.out.println(LocalPass + string);
		return new FileInputStream(LocalPass + "\\" + string);
	}

}
