package 高橋健太.JPL.ch05.ex05_02;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BankAccount {
	private long number;	//口座番号
	private long balance;	//現在の残高（単位はセント）
	private History latest = new History();	//最新のヒストリー

	public class Action {
		private String act;
		private long amount;
		public Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}
		public String toString() {
			return number + ": " + act + " " + amount;
		}
	}
	public class History implements Cloneable{
		static final int maxHistoryNum = 10;
		Queue<Action> actHistory = new ArrayBlockingQueue<Action>(maxHistoryNum);
		public void upDate(Action act) {
			if(actHistory.size() == maxHistoryNum) actHistory.poll();//キューが一杯の場合、一番古いデータを削除
			actHistory.add(act);
		}
		public Action next() {
			return actHistory.poll();
		}
		@Override
		public History clone(){
			History ret = new History();
			//Actionを履歴を複製
			Iterator<Action> i=actHistory.iterator();
			while(i.hasNext()){
				ret.actHistory.add(i.next());
			}
			return ret;
		}
	}
	//預金
	public void deposit(long amount) {
		balance += amount;
		latest.upDate(new Action("deposit", amount));	//Historyをアップデートする
	}
	//引き出す
	public void withdraw(long amount) {
		balance -= amount;
		latest.upDate(new Action("withdraw", amount));	//Historyをアップデートする
	}
	//残高照会
	public void inquiry() {
		System.out.println("balance : " + balance);
		latest.upDate(new Action("inquiry", 0));	//Historyをアップデートする
	}
	//最新のヒストリーを複製をしたものを返す
	public History history() {
		return latest.clone();
	}
	public static void main(String[] args) {
		BankAccount testAccount = new BankAccount();
		//Action実行
		testAccount.deposit(100);
		testAccount.withdraw(100);
		testAccount.deposit(100);
		testAccount.deposit(500);
		testAccount.withdraw(150);
		testAccount.inquiry();
		testAccount.withdraw(100);
		testAccount.deposit(100);
		testAccount.deposit(100);
		testAccount.withdraw(150);
		testAccount.withdraw(100);
		testAccount.inquiry();
		testAccount.deposit(100);
		testAccount.withdraw(150);

		History testHistory = testAccount.history();
		//testAccountのHistoryを確認、tailコマンドのようなイメージで最後の10件の処理を表示する
		System.out.println("-------------show History-------------");
		Action tmp = testHistory.next();
		while(tmp != null) {
			System.out.println(tmp.toString());
			tmp = testHistory.next();
		}
		System.out.println("-------------show History end-------------");

		//Action実行
		testAccount.deposit(100);
		testAccount.withdraw(250);
		testAccount.inquiry();

		History testHistory2 = testAccount.history();
		//testAccountのHistoryを確認、tailコマンドのようなイメージで最後の10件の処理を表示する
		System.out.println("-------------show History2-------------");
		tmp = testHistory2.next();
		while(tmp != null) {
			System.out.println(tmp.toString());
			tmp = testHistory2.next();
		}
		System.out.println("-------------show History2 end-------------");
	}
}
