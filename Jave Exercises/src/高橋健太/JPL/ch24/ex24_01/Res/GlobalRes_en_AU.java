package 高橋健太.JPL.ch24.ex24_01.Res;

import java.util.ListResourceBundle;

public class GlobalRes_en_AU extends ListResourceBundle {
	@Override
	protected Object[][] getContents() {
		return contents;
	}
	private static final Object[][] contents = {
		{ GlobalRes.HELLO, "G'day"},
	};

}
