package 高橋健太.JPL.ch02.ex02_14;

/*
 * 問
 * どのフィールドをprivateにすべきか、どのフィールドが変更を許すメソッドを持つか
 *
 * 答
 * 全フィールドをprivateにすべき
 * mObjフィールド		変更を許す
 * nextフィールド		変更を許す
 */

public class LinkedList {

	private Object mObj = null;
	private LinkedList next = null;

	public LinkedList(Object obj, LinkedList next) {
		this.mObj = obj;
		this.next = next;
	}
	public LinkedList(Vehicle obj) {
		this.mObj = obj;
	}
	public Object get() {
		return mObj;
	}
	public LinkedList getNext() {
		return next;
	}
	public void set(Object obj) {
		mObj = obj;
	}
	public void setNext(LinkedList next) {
		this.next = next;
	}
	public String toString() {
		return mObj.toString();
	}
}
