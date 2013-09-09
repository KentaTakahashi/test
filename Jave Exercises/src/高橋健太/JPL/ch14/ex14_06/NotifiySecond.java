package 高橋健太.JPL.ch14.ex14_06;


public class NotifiySecond implements Runnable {

	private long second = 0;
	private long start = System.currentTimeMillis();
	private boolean isDisplayTime =  false;

	@Override
	public void run() {
		synchronized (this) {
			try {
				while(true) {
					if(isOneSecondElapsed()) {
						second++;
						notifyAll();
					}
					if(isDisplayTime) DisplayTime();
					wait(1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void setDisplay() {
		isDisplayTime = true;
	}
	private void DisplayTime() {
		System.out.println("実行開始からの経過時間:" + (System.currentTimeMillis() - start) + "msec\n");
		isDisplayTime = false;
	}
	private boolean isOneSecondElapsed() {
		long now = System.currentTimeMillis() - start;
		return (second + 1) * 1000 <= now;
	}
}