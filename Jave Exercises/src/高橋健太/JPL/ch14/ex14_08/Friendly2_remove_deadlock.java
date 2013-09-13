package 高橋健太.JPL.ch14.ex14_08;

public class Friendly2_remove_deadlock extends Thread{
	private Friendly2_remove_deadlock partner;
	private String name;

	public Friendly2_remove_deadlock(String name) {
		this.name = name;
	}
	//自身とパートナー両方のリソースを取得してから、処理を実行する
	public void hug() {
		synchronized(this) {
			synchronized(partner) {
				System.out.println(Thread.currentThread().getName() +
						" in " + name + ".hug() trying to invoke " +
						partner.name + ".hugBack()");
				partner.hugBack();
			}
		}
	}

	private void hugBack() {
		synchronized(this) {
			System.out.println(Thread.currentThread().getName() +
					" in " + name + ".hugBack()");
		}
	}

	public void becomeFriend(Friendly2_remove_deadlock partner) {
		this.partner = partner;
	}
}
