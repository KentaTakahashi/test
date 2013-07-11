package 高橋健太.JPL.ch01.ex01_15;

import java.util.ArrayList;
import java.util.List;

public class SimpleLookup implements  ExtendsLookup{

	private List<String> mNames = new ArrayList<String>();
	private List<Object> mValues = new ArrayList<Object>();

	private String[] names;
	private Object[] values;

	//コンストラクタ
	SimpleLookup(String[] n, Object[] v) {
		names = n;
		values = v;
	}

	@Override
	public Object find(String name) {
		// TODO 自動生成されたメソッド・スタブ
		for(int i = 0; i <mNames.size(); i++) {
			if(mNames.get(i).equals(name)) {
				return mValues.get(i);
			}
		}
		return null;
	}

	@Override
	public void add(String name, Object value) {
		// TODO 自動生成されたメソッド・スタブ
		mNames.add(name);
	}

	@Override
	public void remove(int index) {
		// TODO 自動生成されたメソッド・スタブ

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		String[] mNames = {"Animal", "Big", "Clip", "Dependent"};
		Object[] mValues = {6, 3, 4, 9};
		SimpleLookup mLookup = new SimpleLookup(mNames, mValues);

		mLookup.add("Animal", 6);


		Object ret = mLookup.find("Big");
		System.out.println(ret);
	}

}

interface Lookup {
	/**
	 * nameと関連付けされた値を返す。
	 *  そのような値がなければnullを返す
	 */
	Object find(String name);
}

interface ExtendsLookup extends Lookup {
	/**
	 *
	 */
	void add(String name, Object value);

	/**
	 * 指定された位置から終端位置までのすべての文字をこの文字列から削除します。
	 */
	void remove(int index);
}