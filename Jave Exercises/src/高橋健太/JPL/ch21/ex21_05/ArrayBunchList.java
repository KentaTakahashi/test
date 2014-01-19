package 高橋健太.JPL.ch21.ex21_05;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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
	public int size() {
		return size;
	}

	@Override
	public E get(int index) {
		int off = 0;
		for(int i = 0; i < arrays.length; i++) {
			if(index < off + arrays[i].length)
				return arrays[i][index - off];
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	public E set(int index, E value) {
		int off = 0;
		for(int i = 0; i < arrays.length; i++) {
			if(index < off + arrays[i].length) {
				E ret = arrays[i][index - off];
				arrays[i][index - off] = value;
				return ret;
			}
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

    public ListIterator<E> listIterator() {
        return new ABLListIterator();
    }

	private class ABLListIterator implements ListIterator<E> {
		private int off;
		private int array;
		private int pos;
		private int index;
		private E ret;

		ABLListIterator() {
			off = 0;
			array = 0;
			pos = 0;
			index = 0;
			ret = null;
			for(array = 0; array < arrays.length; array++)
				if(arrays[array].length > 0)
				break;
		}

		@Override
		public boolean hasNext() {
			return off + pos < size();
		}

		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			ret = arrays[array][pos++];
			++index;

			while(pos >= arrays[array].length) {
				off += arrays[array++].length;
				pos = 0;
				if(array >= arrays.length)
					break;
			}
			return ret;
		}

		@Override
		public boolean hasPrevious() {
			return off + pos > 0;
		}

		@Override
		public E previous() {
			if(!hasPrevious())
				throw new NoSuchElementException();
			ret = arrays[array][pos--];
			--index;

			while(pos >= 0) {
				off -= arrays[array--].length;
				pos = 0;
				if(array < 0)
					break;
			}
			return ret;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

		@Override
		public void set(E e) {
			if(ret ==null)
				throw new IllegalStateException();
			arrays[array][pos] = e;
		}

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}

	}
}