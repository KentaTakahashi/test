package 高橋健太.JPL.ch24.ex24_01.Res;

import java.util.ListResourceBundle;

public class GlobalRes extends ListResourceBundle {

	public static final String HELLO = "hello";
	public static final String GOODBYE = "goodbye";

	@Override
	protected Object[][] getContents() {
		return contents;
	}
	private static final Object[][] contents = {
		{ GlobalRes.HELLO, "Ciao"},
		{ GlobalRes.GOODBYE, "Ciao"},
	};

}
