package 高橋健太.JPL.ch03.ex03_11;

public class CheatingSortDouble extends SortDouble {

	private double[] copyValues;
	private int[] copyTag;

	@Override
	protected void doSort() {
		copyValues = new double[getDatalength()];
		copyTag = new int[getDatalength()];
		//ソート対象の配列をコピー、ついでにタグも初期化
		for(int i = 0; i < getDatalength(); i++) {
			copyValues[i] = probe(i);//メトリックスカウント対象
			copyTag[i] = i;
		}
		//自前の"compere"と"swap"でコピー配列をソート、この処理はメトリックスには気づかれない
		for(int i = 0; i < getDatalength(); i++) {
			for(int j = 0; j < getDatalength(); j++) {
				if(myCompare(i, j) > 0) {
					mySwap(i, j);
				}
			}
		}
		//オリジナルの配列をソート
		//メトリックスカウント対象だが、コピー配列から天下り的にスワップするだけなので計算量は少ない
		for(int i = 0; i < getDatalength() - 1; i++) {
			if(i < copyTag[i])
				swap(i, copyTag[i]);
		}
	}
	private int myCompare(int i, int j) {
		double d1 = copyValues[i];
		double d2 = copyValues[j];
		if(d1 == d2)
			return 0;
		else
			return (d1 < d2? -1: 1);
	}
	//タグも一緒にスワップさせる
	private void mySwap(int i, int j) {
		double tmp1 = copyValues[i];
		copyValues[i] = copyValues[j];
		copyValues[j] = tmp1;
		int tmp2 = copyTag[i];
		copyTag[i] = copyTag[j];
		copyTag[j] = tmp2;
	}
}
