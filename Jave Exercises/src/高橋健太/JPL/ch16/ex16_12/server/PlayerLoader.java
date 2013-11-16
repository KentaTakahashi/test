package 高橋健太.JPL.ch16.ex16_12.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PlayerLoader extends ClassLoader{

	static final String LocalPass = new File(".").getAbsoluteFile().getParent() + "\\";

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

	/* (非 Javadoc)
	 * @see java.lang.ClassLoader#findResource(java.lang.String)
	 */
	@Override
	protected URL findResource(String name) {
		File f = fileFor(name);
		if(!f.exists())
			return null;
		try {
			return f.toURI().toURL();
		} catch (java.net.MalformedURLException e) {
			return null; //起きるはずがない
		}
	}
	/* (非 Javadoc)
	 * @see java.lang.ClassLoader#findResources(java.lang.String)
	 */
	@Override
	protected Enumeration<URL> findResources(String name) throws IOException {
		List<URL> urlList = new ArrayList<URL>();
		String[] names = name.split(":");//:で引数を分割
		for(String n: names) {//各名前をurlリストに追加
			URL url = findResource(n);
			if(url != null) urlList.add(url);
		}
		return new EnumerationImpl<URL>(urlList);
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
	private File fileFor(String string) {
		string = LocalPass + "bin\\高橋健太\\JPL\\ch16\\ex16_12\\client\\" + string;
		return new File(string);
	}
	private FileInputStream streamFor(String string) throws IOException {
		string = string.replace(".", "\\");
		string += ".class";
		return new FileInputStream(LocalPass + string);
	}

}