package 高橋健太.JPL.ch02.ex02_11;

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
	public String toString() {
		return mObj.toString();
	}
}
