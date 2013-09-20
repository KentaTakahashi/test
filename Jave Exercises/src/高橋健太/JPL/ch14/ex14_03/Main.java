package 高橋健太.JPL.ch14.ex14_03;

public class Main {

	public static void main(String[] args) {

		//値を保持するクラスを作成
		AddNum an = new AddNum();

		//1000msごとに1加算するスレッドを作成
		CallingAddNum call_1 = new CallingAddNum(an, 1, 1000);
		new Thread(call_1).start();

		//2000msごとに1000加算するスレッドを作成
		CallingAddNum call_2 = new CallingAddNum(an, 1000, 2000);
		new Thread(call_2).start();
	}

}
