package 高橋健太.JPL.ch03.ex03_12;

public class CheatingSortObject extends SortHarness {

	private Object[] copyValues;
	private int[] copyTag;

	@Override
	protected void doSort() {
		copyValues = new Object[getDatalength()];
		copyTag = new int[getDatalength()];
		for(int i = 0; i < getDatalength(); i++) {
			copyValues[i] = probe(i);
			copyTag[i] = i;
		}
		for(int i = 0; i < getDatalength(); i++) {
			for(int j = 0; j < getDatalength(); j++) {
				if(myCompare(i, j) > 0) {
					mySwap(i, j);
				}
			}
		}
		for(int i = 0; i < getDatalength() - 1; i++) {
			if(i < copyTag[i])
				swap(i, copyTag[i]);
		}
	}
	private int myCompare(int i, int j) {
		Object d1 = copyValues[i];
		Object d2 = copyValues[j];
		if(d1 == d2)
			return 0;
		else
			return (d1.hashCode() < d2.hashCode() ? -1: 1);//ex03_12修正箇所
	}
	private void mySwap(int i, int j) {
		Object tmp1 = copyValues[i];
		copyValues[i] = copyValues[j];
		copyValues[j] = tmp1;
		int tmp2 = copyTag[i];
		copyTag[i] = copyTag[j];
		copyTag[j] = tmp2;
	}
}
