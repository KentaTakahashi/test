package 高橋健太.JPL.ch22.ex22_05;

import java.util.Random;


public class Dice{

	static final int surface = 6;

	private int diceNum;
	private float[] probs;
	private float[] simulats;


	public Dice(int diceNum) {
		this.diceNum = diceNum;
	}

	//シュミレーション関数
	public float[] simulate(int count) {
		simulats = new float[diceNum * surface + 1];
		int[] counts = new int[diceNum * surface + 1];
		Random rnd = new Random();
		int cnt = 0;
		while(cnt++ < count) {
			int num = 0;
			int i = 1;
			while(i++ <= diceNum)
				num += rnd.nextInt(surface) + 1; //1～surfaceの値をランダムにたす
			counts[num]++;
		}

		for(int i = 0; i < counts.length;i++) {
			simulats[i] = (float) ((float)counts[i] / count);
			System.out.println(i + ": " + simulats[i]);//debug
		}

		return simulats;
	}

	//理論値算出関数
	public float[] calcProb() {
		probs = new float[diceNum * surface + 1];
		int[] counts = new int[diceNum * surface + 1];
		RecursiveCalc rc = new RecursiveCalc(diceNum);

		do{
			counts[rc.calc()]++;
		}while(rc.next());

		for(int i = 0; i < counts.length;i++) {
			probs[i] = (float) ((float)counts[i] / Math.pow(surface, diceNum));
			System.out.println(i + ": " + probs[i]);//debug
		}
		return probs;
	}

	class RecursiveCalc {
		private int pip = 1;//賽の目
		private RecursiveCalc child;

		public RecursiveCalc(int deeps) {
			if(deeps > 1)
				child = new RecursiveCalc(deeps-1);
			else
				child = null;
		}
		public int calc(){
			if(child == null)
				return pip;
			else
				return pip + child.calc();
		}

		public boolean next() {
			//再帰処理の末端の処理
			if(child == null) {
				pip++;
				return pip <= surface;
			}
			//末端以外
			if(child.next()) {
				//子が次に進めれば無条件でtrue
				return true;
			} else {
				//子が次に進めない
				child.reflesh();
				pip++;
				return pip <= surface;
			}
		}
		private void reflesh() {
			pip = 1;
		}
	}
}