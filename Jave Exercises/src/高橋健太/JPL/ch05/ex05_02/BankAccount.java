package 高橋健太.JPL.ch05.ex05_02;

public class BankAccount {
	private long number;	//口座番号
	private long balance;	//現在の残高（単位はセント）
	private Action lastAct;	//最後におこなわれた処理

	public class Action {
		private String act;
		private long amount;
		public Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
			upDate(this);	//Historyをアップデートする
		}
		public String toString() {
			return number + ": " + act + " " + amount;
		}
	}
	public class History {
		Action actHistory;
		Action next = null;
		static final int maxHistoryNum = 10;
		public History() {

		}
	}
	public void deposit(long amount) {
		balance += amount;
		lastAct = new Action("deposit", amount);
	}
	public void withdraw(long amount) {
		balance -= amount;
		lastAct = new Action("withdraw", amount);
	}
	private void upDate(Action act) {

	}
}
