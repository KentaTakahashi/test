package 高橋健太.JPL.ch12.ex12_01;

public class LinkedList<E>{

	private E element = null;
	private LinkedList<E> next = null;

	public LinkedList(E element, LinkedList<E> next) {
		this.element = element;
		this.next = next;
	}
	public LinkedList(E element) {
		this.element = element;
	}
	public E get() {
		return element;
	}
	public LinkedList<E> getNext() {
		return next;
	}
	//関連付いているListを検索し、引数のNameと一致するListオブジェクトを返す
	public LinkedList<E> find(E name) throws ObjectNotFoundException {

		LinkedList<E> index = this;

		do {
			if(name.equals(index.element))
				return index;
			index = index.next;
		} while (index != null);

		//見つからなかったら、例外をスローする
		throw new ObjectNotFoundException(name.toString());
	}
}
