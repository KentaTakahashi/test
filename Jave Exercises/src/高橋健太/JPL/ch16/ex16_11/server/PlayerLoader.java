package 高橋健太.JPL.ch16.ex16_11.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PlayerLoader extends ClassLoader{

	PlayerLoader() {
		super();
	}
	static final String LocalPass = new File(".").getAbsoluteFile().getParent() + "\\";

	@Override
	protected Class<?> findClass(String name)
			throws ClassNotFoundException {
		System.out.println("Call my findClass");
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
		byte[] buf = null;
		try {
			in = streamFor(name);
			int length = in.available();
			if(length == 0)
				throw new ClassNotFoundException(name);
			buf = new byte[length];
			in.read(buf);
		} catch(IOException e) {
			System.out.println(e.toString());
			throw new IOException(e);
		}finally {
			if(in != null)
				in.close();
		}
		return buf;
	}

	private FileInputStream streamFor(String string) throws IOException {
		string = string.replace(".", "\\");
		string += ".class";
		return new FileInputStream(LocalPass + string);
	}

}