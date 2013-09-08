package 高橋健太.JPL.ch14.ex14_06;

public class Display implements Runnable {

	private NotifiySecond timer;
	private int interval;
	private String msg;

	public Display(NotifiySecond timer, int interval, String msg) {
		this.timer = timer;
		this.interval = interval;
		this.msg = msg;
	}

	@Override
	public void run() {
		int count = 0;
		while(true) {
			synchronized (timer) {
				try {
					timer.wait();
					if(count++ % interval == 0) {
						System.out.println(msg);
						timer.setDisplay();
					}
					timer.notifyAll();
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
		}
	}

}
