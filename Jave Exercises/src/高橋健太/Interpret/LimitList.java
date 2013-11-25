package 高橋健太.Interpret;

import java.awt.List;
import java.util.ArrayList;

public class LimitList {

	private List list = new List(15);
	private java.util.List utilList = new ArrayList();
	private java.util.List<String> utilStringList = new ArrayList<String>();

	private List limitList = new List(15);
	private java.util.List<Object> limitUtilList = new ArrayList<Object>();

	public LimitList(List l, java.util.List ul) {
		list = l;
		limitList = list;
		if(ul != null) {
			utilList = ul;
			limitUtilList = utilList;
		}

		for(int i = 0; i < list.getItemCount() ; i ++)
			utilStringList.add(list.getItem(i).toString());
	}

	//input文字列を含むlist要素のみを返す
	public List refresh(String input) {
		List ll = new List(15);
		java.util.List<Object> lul = new ArrayList<Object>();

		for(int i = 0; i < utilStringList.size(); i ++) {
			String s = utilStringList.get(i);
			if(s.indexOf(input) != -1) {
				ll.add(s);
				if(utilList.size() != 0)lul.add(utilList.get(i));
			}
		}
		limitList = ll;
		limitUtilList = lul;

		return limitList;
	}

	public Object getObject(int index) {
		return limitUtilList.get(index);
	}
}
