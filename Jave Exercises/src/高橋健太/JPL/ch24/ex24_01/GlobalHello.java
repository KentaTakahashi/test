package 高橋健太.JPL.ch24.ex24_01;

import java.util.Locale;
import java.util.ResourceBundle;

import 高橋健太.JPL.ch24.ex24_01.Res.GlobalRes;

public class GlobalHello {
	public static void main(String[] args) {
		//http://www.localeplanet.com/java/

		Locale.setDefault(Locale.FRENCH);
		helloGoodbye(args);

		Locale.setDefault(Locale.US);
		helloGoodbye(args);

		Locale.setDefault(new Locale("en", "AU"));
		helloGoodbye(args);

		Locale.setDefault(Locale.JAPAN);
		helloGoodbye(args);
	}

	private static void helloGoodbye(String[] args) {
		ResourceBundle res = ResourceBundle.getBundle("高橋健太.JPL.ch24.ex24_01.Res.GlobalRes");
		String msg;
		if(args.length > 0)
			msg = res.getString(GlobalRes.GOODBYE);
		else
			msg = res.getString(GlobalRes.HELLO);
		System.out.println(msg);
	}
}
