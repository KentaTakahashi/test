package 高橋健太.JPL.ch02.ex02_02;

public class LinkedList {

	private Object mObject = null;
	private LinkedList next = null;

	public LinkedList(Object obj, LinkedList next) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.mObject = obj;
		this.next = next;
	}
	public LinkedList(Object obj) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.mObject = obj;
	}
	public Object get() {
		// TODO 自動生成されたメソッド・スタブ
		return mObject;
	}
	public LinkedList getNext() {
		// TODO 自動生成されたメソッド・スタブ
		return next;
	}
}
