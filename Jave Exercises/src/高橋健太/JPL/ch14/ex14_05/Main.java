package 高橋健太.JPL.ch14.ex14_05;

public class Main {

	public static void main(String[] args) {

		//値を保持するクラスを作成
		AddNum an = new AddNum();

		//1000msごとに1加算するスレッドを作成
		CallingAddNum call_1 = new CallingAddNum(1, 1000);
		new Thread(call_1).start();

		//2000msごとに1000加算するスレッドを作成
		CallingAddNum call_2 = new CallingAddNum(1000, 2000);
		new Thread(call_2).start();

		//1500msごとに100減算するスレッドを作成
		CallingSubtractNum call_3 = new CallingSubtractNum(an, 100, 1500);
		new Thread(call_3).start();

		//10000msごとに4000減算するスレッドを作成
		CallingSubtractNum call_4 = new CallingSubtractNum(an, 4000, 10000);
		new Thread(call_4).start();
	}

}
