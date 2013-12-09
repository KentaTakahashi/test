package 高橋健太.JPL.ch19.ex19_02;


/**
 *
 * @author User
 *
 */
public class LinkedList {

	/** リスト内に保管するオブジェクト */
	private Object mObj = null;
	/** 次のリストを保管するリスト */
	private LinkedList next = null;

	/** 引数を初期値として設定する */
	public LinkedList(Object obj, LinkedList next) {
		this.mObj = obj;
		this.next = next;
	}
	/** 引数を初期値として設定する */
	public LinkedList(Vehicle obj) {
		this.mObj = obj;
	}
	/**
	 * リスト内に保管しているオブジェクトを返す
	 * @return リスト内に保管しているオブジェクト
	 */
	public Object get() {
		return mObj;
	}
	/**
	 * 次のリストを返す
	 * @return 次のリスト
	 */
	public LinkedList getNext() {
		return next;
	}
	/**
	 * オブジェクトを設定する
	 * @param obj 設定するオブジェクト
	 */
	public void set(Object obj) {
		mObj = obj;
	}
	/**
	 * 次のリストを設定する
	 * @param next 次のリスト
	 */
	public void setNext(LinkedList next) {
		this.next = next;
	}
	/**
	 * 保存しているオブジェクトをString型にして返す
	 * @return 保存しているオブジェクトのString型
	 */
	public String toString() {
		return mObj.toString();
	}
	/**
	 * リストに保存している要素数を返す
	 * @return リストに保存している要素数
	 */
	public int countElement() {
		int count = 0;

		LinkedList list = this;

		while(list != null) {
			count++;
			list = list.next;

			if(list == this)break;
		}

		return count;
	}
}
