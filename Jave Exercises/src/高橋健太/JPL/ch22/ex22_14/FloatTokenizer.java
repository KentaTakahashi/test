package 高橋健太.JPL.ch22.ex22_14;

import java.util.StringTokenizer;

public class FloatTokenizer {

	public static float calc(String in){
		StringTokenizer st = new StringTokenizer(in);

		float total = 0.0f;
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			try {
				float f = Float.parseFloat(token);
				total += f;
			} catch(NumberFormatException e) {
				;//変換できなければ読み飛ばし
			}
		}
		return total;
	}

}
