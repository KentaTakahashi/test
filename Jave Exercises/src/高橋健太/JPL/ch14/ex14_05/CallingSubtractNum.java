package 高橋健太.JPL.ch14.ex14_05;

public class CallingSubtractNum implements Runnable {

	private AddNum an;
	private int subtract;
	private int sleep;

	public CallingSubtractNum(AddNum an,int subtract, int sleep) {
		this.an = an;
		this.subtract = subtract;
		this.sleep = sleep;
	}
	@Override
	public void run() {
		while(true) {
			an.subtract(subtract);

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

}
