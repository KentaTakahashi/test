package 高橋健太.JPL.ch09.ex09_04;

public class PascalsTriangle {


	public static void main(String[] args) {
		int x = 0;
		int i = 3;
		x = i++ + i++ + --i;
		System.out.println(x);
	}
}
