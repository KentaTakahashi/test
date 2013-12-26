package 高橋健太.JPL.ch21.ex21_02;

import java.io.File;
import java.util.WeakHashMap;

public final class DataHandler {

	private File lastFile;
	private WeakHashMap<File, byte[]> hashData;

	byte[] readFile(File file) {
		byte[] data;

		if(file.equals(lastFile)) {
			data = hashData.get(lastFile);
			if(data != null) {
				return data;
			}
		}

		data = readBytesFromFile(file);
		lastFile = file;
		hashData.put(file, data);
		return data;
	}

	private byte[] readBytesFromFile(File file) {

		return null;
	}

}