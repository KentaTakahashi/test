package 高橋健太.JPL.ch07.ex07_03;

public class PascalsTriangle {

	int[][] pascalsTriangle;

	public PascalsTriangle(int deeps) {
		pascalsTriangle = new int[deeps][];
		for(int i = 0; i < deeps; i++) {
			pascalsTriangle[i] = new int[i + 1];
			for(int j = 0; j <= i; j++) {
				if(i == 0) {//1列目の初項を設定
					pascalsTriangle[i][j] = 1;
				}else if(j == 0) {//2列目以降の初項を設定
					pascalsTriangle[i][j] = pascalsTriangle[i - 1][j];
				}else if(j == i) {//2列目以降の末項を設定
					pascalsTriangle[i][j] = pascalsTriangle[i - 1][j - 1];
				}else {//一般項を設定
					pascalsTriangle[i][j] = pascalsTriangle[i - 1][j - 1] + pascalsTriangle[i - 1][j];
				}
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
