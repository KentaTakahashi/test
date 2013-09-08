package 高橋健太.JPL.ch14.ex14_06;


public class NotifiySecond implements Runnable {

	static private long second = 0;
	static private long start = System.currentTimeMillis();
	static private boolean isDisplayTime;

	@Override
	public void run() {
		while(true) {
			synchronized (this) {
				try {
					wait(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(isOneSecondElapsed()) {
					second++;
					notifyAll();
				}
				if(isDisplayTime) DisplayTime();
			}
		}
	}
	public void setDisplay() {
		isDisplayTime = true;
	}
	private void DisplayTime() {
		System.out.println("実行開始からの経過時間:" + (System.currentTimeMillis() - start));
		isDisplayTime = false;
	}
	private boolean isOneSecondElapsed() {
		long now = (System.currentTimeMillis() - start) / 1000;
		return second + 1 < now;
	}
}