package 高橋健太.JPL.ch14.ex14_08;


public class Main3_remove_deadlock {

	public static void main(String[] args) {
		final Friendly2_remove_deadlock jareth = new Friendly2_remove_deadlock("jareth");
		final Friendly2_remove_deadlock cory = new Friendly2_remove_deadlock("cory");

		jareth.becomeFriend(cory);
		cory.becomeFriend(jareth);

		new Thread(new Runnable() {
			public void run() {jareth.hug();}
		}, "Thread1").start();

		new Thread(new Runnable() {
			public void run() {cory.hug();}
		}, "Thread2").start();
	}
}
