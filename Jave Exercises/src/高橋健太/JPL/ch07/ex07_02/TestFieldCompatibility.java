package 高橋健太.JPL.ch07.ex07_02;

public class TestFieldCompatibility {

	static boolean	mBool;
	static char		mChar;
	static byte		mByte;
	static short	mShort;
	static int		mInt;
	static long		mLong;
	static float	mFloat;
	static double	mDouble;
	public static void main(String[] args) {

		mInt = (int) 3.5f;
		System.out.println(mInt);//"3" キャストにより代入か、小数点以下は切り下げられる

		mFloat = Integer.MAX_VALUE + 1;//キャストなしで代入可能
		mInt = (int)mFloat;
		System.out.println(mInt);//"-2147483648" intの最大値を超えた値を代入するとオーバーフローする

		mByte = (byte) 3.5D;
		System.out.println(mByte);//"3" キャストにより代入か、小数点以下は切り下げられる

		mDouble = Byte.MAX_VALUE + 1;//キャストなしで代入可能
		mByte = (byte)mDouble;
		System.out.println(mByte);//"-128" byteの最大値を超えた値を代入するとオーバーフローする

		mFloat = (float) 12.5D;//double→ floatはキャストが必要
		mDouble = 12.5f;//float→ doubleキャスト不要

		mChar = (char) mFloat;
		mFloat = mChar;

		mLong = (long) mDouble;//double→ longキャストが必要
		mDouble = mLong;//long→ doubleキャスト不要

		//mBool = (boolean)mInt; int から boolean へキャストすることはできません
	}
}
