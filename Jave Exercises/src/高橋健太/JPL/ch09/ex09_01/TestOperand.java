package 高橋健太.JPL.ch09.ex09_01;

public class TestOperand {

	static final float pInf = Float.POSITIVE_INFINITY;
	static final float nInf = Float.NEGATIVE_INFINITY;

	public static void main(String[] args) {
		System.out.println("-----POSITIVE vs POSITIVE----------------------");
		System.out.println("POSITIVE_INFINITY + POSITIVE_INFINITY = " + (pInf + pInf));
		System.out.println("POSITIVE_INFINITY - POSITIVE_INFINITY = " + (pInf - pInf));
		System.out.println("POSITIVE_INFINITY * POSITIVE_INFINITY = " + (pInf * pInf));
		System.out.println("POSITIVE_INFINITY / POSITIVE_INFINITY = " + (pInf / pInf));

		System.out.println("-----NEGATIVE vs NEGATIVE----------------------");
		System.out.println("NEGATIVE_INFINITY + NEGATIVE_INFINITY = " + (nInf + nInf));
		System.out.println("NEGATIVE_INFINITY - NEGATIVE_INFINITY = " + (nInf - nInf));
		System.out.println("NEGATIVE_INFINITY * NEGATIVE_INFINITY = " + (nInf * nInf));
		System.out.println("NEGATIVE_INFINITY / NEGATIVE_INFINITY = " + (nInf / nInf));

		System.out.println("-----POSITIVE vs NEGATIVE----------------------");
		System.out.println("POSITIVE_INFINITY + NEGATIVE_INFINITY = " + (pInf + nInf));
		System.out.println("POSITIVE_INFINITY - NEGATIVE_INFINITY = " + (pInf - nInf));
		System.out.println("POSITIVE_INFINITY * NEGATIVE_INFINITY = " + (pInf * nInf));
		System.out.println("POSITIVE_INFINITY / NEGATIVE_INFINITY = " + (pInf / nInf));
	}
}
