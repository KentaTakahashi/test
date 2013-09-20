package 高橋健太.JPL.ch09.ex09_03;

public class PascalsTriangle {

	int[][] pascalsTriangle;

	public PascalsTriangle(int deeps) {
		pascalsTriangle = new int[deeps][];
		for(int i = 0; i < deeps; i++) {
			pascalsTriangle[i] = new int[i + 1];
			for(int j = 0; j <= i; j++) {
				pascalsTriangle[i][j] = ((j == 0 || j == i) ? 1 : pascalsTriangle[i - 1][j - 1] + pascalsTriangle[i - 1][j]);
			}
		}
	}
	public String toString(){
		String str = "";

		for(int i = 0; i < pascalsTriangle.length; i++) {
			str += "{ ";
			for(int j = 0; j <= i; j++) {
				str += pascalsTriangle[i][j] + ", ";
			}
			str = str.substring(0, str.length() - 2);//見栄えが悪いので最後の", "を削除
			str += " },\r\n";
		}
		return str;
	}

	public static void main(String[] args) {
		PascalsTriangle p = new PascalsTriangle(12);
		System.out.println(p.toString());

		PascalsTriangle p2 = new PascalsTriangle(24);
		System.out.println(p2.toString());
	}
}
