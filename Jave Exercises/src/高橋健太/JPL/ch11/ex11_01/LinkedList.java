package 高橋健太.JPL.ch11.ex11_01;

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
}
