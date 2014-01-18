package 高橋健太.JPL.ch21.ex21_04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStrings implements ListIterator<String> {

	private ListIterator<String> strings;	//元の文字列
	private String nextShort;				//次が不明ならばnull
	private String previousShort;			//前が不明ならばnull
	private int currentIndex;				//現在のIndex
	private int size;						//リストのサイズ
	private int maxLen;						//この長さ以下の文字列だけを返す

	public ShortStrings(ListIterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
		this.nextShort = null;
		this.previousShort = null;
		currentIndex = -1;

		size = 0;
		ListIterator<String> it = strings;
		while(it.hasNext()) {
			if(it.next().length() <= maxLen)
				size++;
		}
	}

	@Override
	public boolean hasNext() {
		if(nextShort != null)
			//すでに見つけている
			return true;

		while(strings.hasNext()) {
			nextShort = strings.next();
			if(nextShort.length() <= maxLen)
				//見つかった
				return true;
		}

		//見つけられなかった
		nextShort = null;
		return false;
	}

	@Override
	public String next() {
		if(nextShort == null && !hasNext())
			throw new NoSuchElementException();
		String n = nextShort;
		nextShort = null;
		currentIndex++;
		return n;
	}

	@Override
	public boolean hasPrevious() {
		if(previousShort != null)
			//すでに見つけている
			return true;

		while(strings.hasPrevious()) {
			previousShort = strings.previous();
			if(previousShort.length() <= maxLen)
				//見つかった
				return true;
		}

		//見つけられなかった
		previousShort = null;
		return false;
	}

	@Override
	public String previous() {
		if(previousShort == null && !hasPrevious())
			throw new NoSuchElementException();
		String n = previousShort;
		previousShort = null;
		currentIndex--;
		return n;
	}

	/*
	 * 次に next を呼び出したときに返されることになる要素のインデックスを返します。
	 * リスト反復子がリストの末尾にある場合はリストのサイズを返します。
	 * @return 次に next を呼び出したときに返されることになる要素のインデックス。
	 *         リスト反復子がリストの最後にある場合はリストのサイズ
	 * @Override
	 * (非 Javadoc)
	 * @see java.util.ListIterator#nextIndex()
	 */
	public int nextIndex() {
		if(!hasNext())
			return size;
		else
			return currentIndex;
	}

	/*
	 * 次に previous を呼び出したときに返されることになる要素のインデックスを返します。
	 * リスト反復子がリストの先頭にある場合は -1 を返します。
	 * @return 次に previous を呼び出したときに返されることになる要素のインデックス。
	 *         リスト反復子がリストの先頭にある場合は -1
	 * @Override
	 * (非 Javadoc)
	 * @see java.util.ListIterator#previousIndex()
	 */
	public int previousIndex() {
		if(!hasPrevious())
			return -1;
		else
			return currentIndex -1;
	}

	@Override
	public void remove() {
		adjustHeadTail();
		strings.remove();
	}

	@Override
	public void set(String e) {
		adjustHeadTail();
		strings.set(e);
	}

	@Override
	public void add(String e) {
		adjustHeadTail();
		strings.add(e);
	}

	private void adjustHeadTail() {
		//最後尾だったら一個戻る
		if(!hasNext())
			hasPrevious();

		//先頭だったら一個進む
		if(!hasPrevious())
			hasNext();
	}
}