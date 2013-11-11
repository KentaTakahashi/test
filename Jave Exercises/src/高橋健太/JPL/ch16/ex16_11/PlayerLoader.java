package 高橋健太.JPL.ch16.ex16_11;

import java.io.IOException;

public class PlayerLoader extends ClassLoader{

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			byte[] buf = bytesForClass(name);
			return defineClass(name, buf, 0, buf.length);
		} catch (IOException e) {
			throw new ClassNotFoundException(e.toString());
		}

	}

	private byte[] bytesForClass(String name) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
