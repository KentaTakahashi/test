package 高橋健太.JPL.ch01.ex01_15;

import java.util.ArrayList;
import java.util.List;

public class SimpleLookup implements  ExtendsLookup{

	private List<String> mNames = new ArrayList<String>();
	private List<Object> mValues = new ArrayList<Object>();

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
		mValues.add(value);
	}

	@Override
	public void remove(int index) {
		// TODO 自動生成されたメソッド・スタブ
		mNames.remove(index);
		mValues.remove(index);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Object ret;

		SimpleLookup mLookup = new SimpleLookup();

		//nameと関連付けされた値を追加
		mLookup.add("Animal", 6);
		mLookup.add("Big", 3);
		mLookup.add("Clip", 4);
		mLookup.add("Dependent", 9);

		//nameと関連付けされた値を探して表示
		ret = mLookup.find("Dependent");
		System.out.println(ret);

		//指定された位置のnameと関連付けされた値をこの文字列から削除、削除した結果nullがかえることを確認
		mLookup.remove(3);
		ret = mLookup.find("Dependent");
		System.out.println(ret);

		/*実行結果
		 * 9
		 * null
		*/

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
	 * nameと関連付けされた値をこの文字列に追加する
	 */
	void add(String name, Object value);

	/**
	 * 指定された位置のnameと関連付けされた値をこの文字列から削除します。
	 */
	void remove(int index);
}