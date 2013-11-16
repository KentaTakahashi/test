package 高橋健太.JPL.ch16.ex16_12.client;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

import 高橋健太.JPL.ch16.ex16_12.server.Player;
import 高橋健太.JPL.ch16.ex16_12.server.PlayerLoader;

public class BoldPlayer extends Player {

	int index = 0;

	int[] xArray = new int[9];//bookからx方向の置き順を読み込むための配列
	int[] yArray = new int[9];//bookからy方向の置き順を読み込むための配列

	public BoldPlayer() {
		PlayerLoader loader = new PlayerLoader();
		try {
			Enumeration<URL> urls = loader.getResources("BoldPlayer_xArray.book:BoldPlayer_yArray.book");

			while(urls.hasMoreElements()){
				URL address = urls.nextElement();
				int[] outArray;

				//読み込んだURLにより出力先を選択
				if(address.toString().indexOf("BoldPlayer_xArray.book") != -1)
					outArray = xArray;
				else if(address.toString().indexOf("BoldPlayer_yArray.book") != -1)
					outArray = yArray;
				else
					continue;

				File file = new File(address.toURI());
				FileReader in = new FileReader(file);

				int ch;
				int i = 0;
				while ((ch = in.read()) != -1) {
					int ret = decode(ch);
					outArray[i++] = ret;
				}
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private int decode(int ch) {
		int ret = -1;
		try {
			ret = Integer.parseInt(String.valueOf((char)ch));
		} catch (NumberFormatException ex) {
			throw new NumberFormatException();
		}
		return ret;
	}

	public static void main(String[] args) {}

	@Override
	public void thinking() {
		x = xArray[index];
		y = yArray[index];
		index = (index + 1) % 9;
	}

}
