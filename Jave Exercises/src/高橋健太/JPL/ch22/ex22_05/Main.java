package 高橋健太.JPL.ch22.ex22_05;

public class Main {

	public static void main(String[] args) {
		Dice d = new Dice(4);
		float[] prob = d.calcProb();
		float[] simulats = d.simulate(10000);//メモ：1万回やればかなり理論値に近づく

		for(int i = 0; i < prob.length; i++) {
			float rateOfDivergence = prob[i] / simulats[i];
			System.out.println(i + " : simulat = " + simulats[i]
					+ " : prob = " + prob[i] + " : rateOfDivergence = " + rateOfDivergence);
		}
	}

}
