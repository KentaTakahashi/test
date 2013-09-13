package 高橋健太.JPL.ch14.ex14_09;


public class Main {

	public static void main(String[] args) {

		ThreadGroup group_1 = new ThreadGroup("group_1");
		ThreadGroup group_2 = new ThreadGroup("group_2");
		ThreadGroup group_3 = new ThreadGroup("group_3");

		new Thread(group_1, new Runnable() {
			public void run() {while(true){}}
		}, "Thread1").start();

		new Thread(group_1, new Runnable() {
			public void run() {while(true){}}
		}, "Thread2").start();

		ThreadGroupTree.show(group_1);
		ThreadGroupTree.show(group_2);
		ThreadGroupTree.show(group_3);
	}


}
