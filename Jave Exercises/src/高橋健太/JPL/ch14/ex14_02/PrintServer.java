package 高橋健太.JPL.ch14.ex14_02;

public class PrintServer implements Runnable{

	private final PrintQueue requests = new PrintQueue();
	private Thread PrintServerThread;

	public PrintServer() {
		PrintServerThread = new Thread(this);
		PrintServerThread.start();
	}
	public void print(Printjob job) {
		requests.add(job);
	}
	@Override
	public void run() {

		if(Thread.currentThread() != PrintServerThread) {
			System.out.println("異なるスレッドからの呼び出しは受け付けない");
			return;
		}

		for(;;) {
			realPrint(requests.remove());//プリンタ処理

			try {
				Thread.sleep(1000);				//スリープ1000msec;
			} catch(InterruptedException e) {
				;								//特に処理はしない
			}
		}
	}
	private void realPrint(Printjob remove) {
		System.out.println("印刷の実際の処理を行う");
	}
}