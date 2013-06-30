package 高橋健太.JPL.ch01.ex01_08;

public class Point {

	public double x, y;

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Point p = new Point();
		System.out.println("set前 x = "+ p.x + ", y = " + p.y );

		p.set(1.0, 1.0);
		System.out.println("set後 x = "+ p.x + ", y = " + p.y );
	}

}

/*実行結果
set前 x = 0.0, y = 0.0
set後 x = 1.0, y = 1.0
*/