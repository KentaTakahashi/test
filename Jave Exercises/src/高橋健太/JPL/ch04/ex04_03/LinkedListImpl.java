package 高橋健太.JPL.ch04.ex04_03;

public class LinkedListImpl implements LinkedList{

	private Object Object = null;
	private LinkedList next = null;

	public LinkedListImpl(Object obj, LinkedList next) {
		this.Object = obj;
		this.next = next;
	}
	public LinkedListImpl(Object obj) {
		this.Object = obj;
	}
	public Object get() {
		return Object;
	}
	public LinkedList getNext() {
		return next;
	}
	public void setNext(LinkedList next) {
		this.next = next;
	}
}
