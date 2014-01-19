package 高橋健太.JPL.ch22.ex22_01;

public class ShowFloat{

	private final static int maxColumn = 80;

	//桁数を統一しやすいよう、科学的記数法（3.142e+00など）で表示させる
	public static void show(float[] array, int column) {
		int digit = (maxColumn - column) / column;//ひとつの浮動小数点値の表示桁数を計算、表示時の値の間のスペースを考慮する

		//digitがfloatを表示するための桁数より少なくなる場合、以下の表示を強制する
		//12の根拠は整数部（1桁）+小数点（1桁）+小数部（6桁）+指数部（4桁）
		if(digit < 12) {
			digit = 12;
			column = maxColumn / (digit + 1);
		}

		int index = 0;
		while(index < array.length) {
			System.out.printf(String.format("%%0%de ",digit),array[index]);
			index++;
			if(index % column == 0)
				System.out.printf("\n");
		}
	}
	public static void main(String[] args){
		float[] f = {
				1.25612f, 2.523f, 2.523f, 0.523f, 0.0003f, 2.523f, 2.023f, 252.3f, 2552f, 2.523f,
				1.25612f, 2.523f, 2.523f, 0.523f, 0.0003f, 2.523f, 2.023f, 252.3f, 2552f, 2.523f,
				1.25612f, 2.523f, 2.523f, 0.523f, 0.0003f, 2.523f, 2.023f, 252.3f, 2552f, 2.523f,
				1.25612f, 2.523f, 2.523f, 0.523f, 0.0003f, 2.523f, 2.023f, 252.3f, 2552f, 2.523f,
				1.25612f, 2.523f, 2.523f, 0.523f, 0.0003f, 2.523f, 2.023f, 252.3f, 2552f, 2.523f,
				};

		show(f, 1);
		show(f, 5);
		show(f, 7);
		show(f, 10);
		show(f, 20);
	}
}