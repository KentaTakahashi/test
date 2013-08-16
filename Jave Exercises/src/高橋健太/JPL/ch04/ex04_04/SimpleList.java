package 高橋健太.JPL.ch04.ex04_04;

import java.util.Collection;

//順序付けられたコレクション（既知のjava.util.Listインタフェースの簡略版）
//このインタフェースのユーザーはindex (リスト内の位置)によって要素にアクセスしたり、検索したりすることができます。
public interface SimpleList<E> extends Collection<E> {
	void add(int index, E e);	//リスト内の指定された位置に指定された要素を挿入します
	E remove(int index);		//リスト内の指定された位置にある要素を削除します
	E get(int index);			//リスト内の指定された位置にある要素を返します
}
