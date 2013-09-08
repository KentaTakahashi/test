package 高橋健太.JPL.ch14.ex14_05;

public class CallingAddNum implements Runnable {

	private int add;
	private int sleep;

	public CallingAddNum(int add, int sleep) {
		this.add = add;
		this.sleep = sleep;
	}
	@Override
	public void run() {
		while(true) {
			AddNum.add(add);

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

}
