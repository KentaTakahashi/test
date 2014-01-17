package 高橋健太.JPL.ch21.ex21_05;

import java.util.AbstractList;

public class ArrayBunchList<E> extends AbstractList<E> {

	private final E[][] arrays;
	private final int size;

	public ArrayBunchList(E[][] arrays) {
		this.arrays = arrays;
		int s = 0;
		for(E[] array: arrays)
			s += array.length;
		size = s;
	}

	@Override
	public E get(int index) {
		int off = 0;
		for(int i = 0; i < arrays.length; i++)


		return null;
	}

	@Override
	public int size() {
		return size;
	}

}