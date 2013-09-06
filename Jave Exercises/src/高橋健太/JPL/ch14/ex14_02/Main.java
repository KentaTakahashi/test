package 高橋健太.JPL.ch14.ex14_02;

public class Main {

	public static void main(String[] args) {
		PrintServer ps = new PrintServer();//プリントサーバ起動、1秒ごとに処理を実行
		ps.run();//mainスレッドからrun呼び出しは出来ない
	}

}
