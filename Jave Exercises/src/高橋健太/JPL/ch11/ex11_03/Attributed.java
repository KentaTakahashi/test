package 高橋健太.JPL.ch11.ex11_03;

public interface Attributed<V> {
	void add(Attr<V> newAttr);
	Attr<V> find(String attrName);
	Attr<V> remove(String attrName);
	java.util.Iterator<Attr<V>> attrs();
}
