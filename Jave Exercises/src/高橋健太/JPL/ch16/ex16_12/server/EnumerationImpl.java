package 高橋健太.JPL.ch16.ex16_12.server;

import java.util.Enumeration;
import java.util.List;

public class EnumerationImpl<E> implements Enumeration<E> {

	private List<E> list;
	private int count = 0;

	public EnumerationImpl(List<E> list) {
		this.list = list;
	}
	@Override
	public boolean hasMoreElements() {
		return (count < list.size()? true : false);
	}
	@Override
	public E nextElement() {
		return list.get(count++);
	}
}
