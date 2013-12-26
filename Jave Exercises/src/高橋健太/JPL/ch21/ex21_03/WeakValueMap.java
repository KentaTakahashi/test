package 高橋健太.JPL.ch21.ex21_03;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WeakValueMap<K, V> {
	private HashMap<K, V> hashMap;
	private ValuesCollection valuesCollection;
	public WeakValueMap() {
		hashMap = new HashMap<K, V>();
	}
	public WeakValueMap(int initialCapacity) {
		hashMap = new HashMap<K, V>(initialCapacity);
	}
	public WeakValueMap(Map<? extends K,? extends V> m) {
		hashMap = new HashMap<K, V>(m);
	}
	public WeakValueMap(int initialCapacity, float loadFactor) {
		hashMap = new HashMap<K, V>(initialCapacity, loadFactor);
	}


	public V get(Object key){return hashMap.get(key);}
	public V put(K key, V value){return hashMap.put(key, value);}
	public void putAll(Map<? extends K,? extends V> m) {}
	public V remove(Object key) {return hashMap.remove(key);}
	public void clear() {hashMap.clear();}

	public Collection<V> values() {return hashMap.values();}

	public int hashCode() {return hashMap.hashCode();}
	public Object clone() {return hashMap.clone();}
	public boolean isEmpty() {return hashMap.isEmpty();}
	public int size() {return hashMap.size();}
	public boolean containsKey(Object key) {return hashMap.containsKey(key);}
	public boolean containsValue(Object value){return hashMap.containsKey(value);}
	public Set<Map.Entry<K,V>> entrySet(){return hashMap.entrySet();}
	public Set<K> keySet() {return hashMap.keySet();}

	class ValuesCollection extends AbstractCollection {

		@Override
		public Iterator iterator() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public int size() {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

	}





}