package 高橋健太.JPL.ch02.ex02_16;

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
	//リスト内の要素の数を返すメソッド、自身を含め以降の要素の数を返す
	public int countElement() {
		int count = 0;

		LinkedList list = this;

		while(list != null) {
			count++;
			list = list.next;

			//ループ対策
			if(list == this)break;
		}

		return count;
	}
}
