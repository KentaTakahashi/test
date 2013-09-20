package 高橋健太.JPL.ch10.ex10_05;

public class Interpolation {

	static public void interpolation(char a, char b) {
		if (a < b) while(a <= b) System.out.println(a++);
		else       while(a >= b) System.out.println(a--);
	}
	public static void main(String[] args) {
		interpolation('a', 'z');
	}
}
