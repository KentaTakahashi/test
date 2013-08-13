package 高橋健太.JPL.ch03.ex03_10;



public class LinkedList implements Cloneable {

	private Object Object = null;
	private LinkedList next = null;

	public LinkedList(Object obj, LinkedList next) {
		this.Object = obj;
		this.next = next;
	}
	public LinkedList(Object obj) {
		this.Object = obj;
	}
	@Override
	public LinkedList clone() {
		//再帰的にクローン、LinkedListは複製し、Objectは共有する（リンクのループは考慮してない）
		if(this.next == null)
			return new LinkedList(this.get());
		else
			return new LinkedList(this.get(), this.next.clone());
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
	@Override
	public String toString() {
		String str = "";
		LinkedList next = this;
		while(next != null) {
			str += next.get().toString() + ", ";
			next = next.getNext();
		}
		return str;
	}


	public static void main(String[] args) {
		subObject obj_1 = new subObject(1);
		subObject obj_2 = new subObject(2);
		subObject obj_3 = new subObject(3);
		LinkedList list_3 = new LinkedList(obj_3);
		LinkedList list_2 = new LinkedList(obj_2, list_3);
		LinkedList list_1 = new LinkedList(obj_1, list_2);


		System.out.println("クローン作成");
		LinkedList clone_list = list_1.clone();//listクローン作成
		System.out.println("originリスト : " + list_1.toString());
		System.out.println("cloneリスト  : " + clone_list.toString());

		System.out.println("参照しているオブジェクトの変更→両方のリストに反映される");
		obj_3.n = 4;//オブジェクト変更
		System.out.println("originリスト : " + list_1.toString());
		System.out.println("cloneリスト  : " + clone_list.toString());

		System.out.println("originのリストに新しいリストを追加→cloneのリストには反映されない");
		LinkedList list_4 = new LinkedList(obj_1);
		list_3.setNext(list_4);//"originのリストに新しいリストを追加
		System.out.println("originリスト : " + list_1.toString());
		System.out.println("cloneリスト  : " + clone_list.toString());
	}
}
class subObject {
	public int n;
	public subObject (int n){
		this.n = n;
	}
	@Override
	public String toString() {
		return " " + n;
	}
}