package 高橋健太.JPL.ch24.ex24_02;

import java.util.Currency;
import java.util.Locale;

public class ShowSymbol {
	private static final Locale locales[] = {
		Locale.JAPAN,
		Locale.US,
		Locale.CANADA,
		Locale.CHINA,
		Locale.FRANCE,
		Locale.ITALY
		};


	public static void main(String[] args) {
		//http://www.localeplanet.com/java/

		System.out.print("locale" + "\t|");
		for(int locale = 0; locale < locales.length; locale++)
			System.out.print(locales[locale] + "\t");
		System.out.print("\n");
		System.out.print("===================================================\n");

		for(int locale = 0; locale < locales.length; locale++) {
			System.out.print(locales[locale] + "\t|");
			Currency currency = Currency.getInstance(locales[locale]);
			for(int symbol = 0; symbol < locales.length; symbol++)
				System.out.print(currency.getSymbol(locales[symbol]) + "\t");
			System.out.print("\n");

		}
	}
}
