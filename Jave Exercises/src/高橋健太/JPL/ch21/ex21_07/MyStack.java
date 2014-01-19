package 高橋健太.JPL.ch21.ex21_07;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyStack<E>{

	private ArrayList<E> list;

	public MyStack() {
		list = new ArrayList<E>();
	}

	public E push(E item) {
		list.add(item);
		return item;
	}
	public synchronized E pop() {
		if(list.isEmpty())
			throw new EmptyStackException();

		int lastIndex = list.size() -1;
		E ret = list.get(lastIndex);
		list.remove(lastIndex);
		return ret;
	}
	public synchronized E peek() {
		if(list.isEmpty())
			throw new EmptyStackException();

		int lastIndex = list.size() -1;
		E ret = list.get(lastIndex);
		//list.remove(lastIndex);
		return ret;
	}
	public boolean empty() {
		return list.isEmpty();
	}

	/**
	 * このスタックにあるオブジェクトの位置を 1 から始まるインデックスで返します。
	 * オブジェクト o がこのスタック内の項目にある場合、このメソッドはスタックの先頭からもっとも近いオブジェクト位置までの距離を返します。
	 * スタックの 1 番上の項目は距離 1 にあると見なされます。equals メソッドを使うと、o をこのスタック内の項目と比較できます。
	 * @return オブジェクト位置を表す、1 から始まるスタックの先頭からのインデックス。オブジェクトが見つからない場合は -1
	 */
	public synchronized int search(Object o) {
		if(!list.contains(o))
			return -1;
		else
			return list.size() - list.lastIndexOf(o);//一番最後の要素が該当すれば1を返す
	}

}