package 高橋健太.JPL.ch04.ex04_03;

public interface LinkedList {
	Object get();					//リストに保存されているオブジェクトを取得する
	LinkedList getNext();			//次のリストを取得する
	void setNext(LinkedList next);	//次のリストを設定する
}
