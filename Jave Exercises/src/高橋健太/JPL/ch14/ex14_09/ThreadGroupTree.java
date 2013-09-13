package 高橋健太.JPL.ch14.ex14_09;


public class ThreadGroupTree extends Thread{

	private static Object lockDisp = new Object();

	public static Thread show(ThreadGroup group) {
		final ThreadGroup g = group;

		//3sec周期でgroup内のスレッドとスレッドグループを周期的に表示するスレッドを開始する
		Thread th = new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						//表示処理は排他で実行する
						synchronized (lockDisp) {
							System.out.println("\n-----" + g.getName() + "-----\n");
							recursiveShow(g, 0);//再帰的に表示処理関数を呼び出す
						}
						Thread.sleep(3000);//3sec sleep
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		th.start();

		return th;//スレッドの終了等の処理は呼び出し元に任せる
	}

	private static void recursiveShow(ThreadGroup group, int deeps) {
		int active = group.activeCount();			//アクティブなスレッド+アクティブなグループの数
		int activeGroup = group.activeGroupCount();	//アクティブなグループの数

		Thread[] ThreadList = new Thread[active - activeGroup];
		group.enumerate(ThreadList, false);

		ThreadGroup[] GroupList = new ThreadGroup[activeGroup];
		group.enumerate(GroupList, false);

		for(Thread th: ThreadList) System.out.println(th.getName());
		for(ThreadGroup g: GroupList) {
			System.out.println(g.getName());		//グループ名表示
			recursiveShow(g, deeps + 1);			//階層を下げて再帰呼び出し
		}
	}
}
