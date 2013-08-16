package 高橋健太.JPL.ch03.ex03_11;

abstract class SortDouble{
	private double[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	//全ソートする為に呼び出される
	public final SortMetrics sort(double[] data) {
		values = data;
		curMetrics.init();
		doSort();
		return getMetrics();
	}
	public final SortMetrics getMetrics() {
		return curMetrics.clone();
	}
	//拡張したクラスが要素の数を知るため
	protected final int getDatalength() {
		return values.length;
	}
	//拡張したクラスが要素を調べる為
	protected final double probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}
	//拡張したクラスを比較するため
	protected final int compare(int i, int j) {
		curMetrics.compareCnt++;
		double d1 = values[i];
		double d2 = values[j];
		if(d1 == d2)
			return 0;
		else
			return (d1 < d2? -1: 1);
	}
	//拡張したクラスが要素を交換するため
	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		double tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}
	//拡張したクラスが実装する ソートするのに使用される
	protected abstract void doSort();
}

final class SortMetrics implements Cloneable {

	public long probeCnt;	//単純なデータの値調査
	public long compareCnt;	//2つの要素の比較
	public long swapCnt;	//2つの要素の交換

	public void init() {
		probeCnt 	= 0;
		compareCnt 	= 0;
		swapCnt 	= 0;
	}
	public String toString() {
		return 	probeCnt + " probeCnt " +
				compareCnt + " compareCnt " +
				swapCnt + " swapCnt ";
	}
	//このクラスは、cloneをサポートしている
	public SortMetrics clone() {
		try {
			//デフォルトの仕組みで十分
			return (SortMetrics) super.clone();
		} catch(CloneNotSupportedException e) {
			//起こりえない、このクラスをObjectは複製できる
			throw new InternalError(e.toString());
		}
	}
}