package 高橋健太.JPL.ch14.ex14_04;

public class Main {

	public static void main(String[] args) {

		//1000msごとに1加算するスレッドを作成
		CallingAddNum call_1 = new CallingAddNum(1, 1000);
		new Thread(call_1).start();

		//2000msごとに1000加算するスレッドを作成
		CallingAddNum call_2 = new CallingAddNum(1000, 2000);
		new Thread(call_2).start();
	}

}
