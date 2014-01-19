package 高橋健太.JPL.ch22.ex22_04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class AttributedImpl extends Observable implements Attributed{

	protected Map<String, Attr> attrTable = new HashMap<String, Attr>();

	public enum STATE {
		ADD, REMOVE
	}
	@Override
	public void add(Attr newAttr) {
		attrTable.put(newAttr.getName(), newAttr);
		setChanged();
		notifyObservers(new AttributedState(STATE.ADD, newAttr));
	}

	@Override
	public Attr find(String attrName) {
		return attrTable.get(attrName);
	}

	@Override
	public Attr remove(String attrName) {
		Attr attr = attrTable.remove(attrName);
		setChanged();
		notifyObservers(new AttributedState(STATE.REMOVE, attr));
		return attrTable.remove(attrName);
	}

	@Override
	public Iterator<Attr> attrs() {
		return attrTable.values().iterator();
	}

	class AttributedState {
		public STATE state;
		public Attr attr;

		public AttributedState(STATE state, Attr attr) {
			this.state = state;
			this.attr = attr;
		}
	}
}
