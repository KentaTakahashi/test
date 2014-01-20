package 高橋健太.JPL.ch22.ex22_06;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CheckGaussianMethod{

	static private final double sigma = 1.0;				//nextGaussian関数の仕様より
	static private final double minDisplay = sigma * (-3.0);//表示領域の下限は-3σ、理論上99.6%のサンプルが表示されるはず
	static private final double maxDisplay = sigma *   3.0 ;//表示領域の上限は 3σ、理論上99.6%のサンプルが表示されるはず
	static private final int splitNum = 15;					//表示グラフの分割数
	static private final int asteriskWeight = 1;			//表示グラフの「*」一つの重さ、単位はパーセント

	//シュミレーション関数
	static public void simulate(int count) {
		List<Double> list = new ArrayList<Double>();
		Random rnd = new Random();

		int cnt = 0;
		while(++cnt < count)
			list.add(rnd.nextGaussian());

		double segment_size = (maxDisplay-minDisplay) / splitNum;

		for(int i = 0;i < splitNum; i++) {
			double min = minDisplay + (segment_size * i);
			double max = minDisplay + (segment_size * (i + 1));
			min = round_half_up(min, 2);//少数点1位までの表記に丸める、0.8999999999999999みたいになるのを0.9に丸める
			max = round_half_up(max, 2);//同上
			int include_cnt = 0;
			for(double d:list)
				if(min <= d && d < max)
					include_cnt++;
			double ratio = (double)include_cnt / list.size() * 100;

			System.out.print(min + " - " + max + " \t: ");
			while((ratio -= asteriskWeight) > 0) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}

	//理論値算出関数
	public void calcProb() {
		//未実装
	}

	private static double round_half_up(double in, int digit) {
		BigDecimal bd = new BigDecimal(in);
		bd = bd.setScale(digit-1, BigDecimal.ROUND_HALF_UP);  //小数第digit位で四捨五入
		return bd.doubleValue();
	}
}