package 高橋健太.JPL.ch14.ex14_03;

public class CallingAddNum implements Runnable {

	private AddNum an;
	private int add;
	private int sleep;

	public CallingAddNum(AddNum an, int add, int sleep) {
		this.an = an;
		this.add = add;
		this.sleep = sleep;
	}
	@Override
	public void run() {
		while(true) {
			an.add(add);

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

}
