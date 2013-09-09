package 高橋健太.JPL.ch14.ex14_06;

public class Main {

	public static void main(String[] args) {

		//値を保持するクラスを作成
		NotifiySecond ns = new NotifiySecond();
		new Thread(ns, "NotifiySecond").start();

		Display d15 = new Display(ns, 15, "msg:interval 15sec!");
		new Thread(d15).start();

		Display d07 = new Display(ns, 7, "msg:interval 7sec!");
		new Thread(d07).start();
	}
}
