package 高橋健太.JPL.ch21.ex21_03;

import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WeakValueMap<K, V>
	extends AbstractMap<K,V>
	implements Map<K,V> {

	private HashMap<K, WeakReference<V>> hashMap;
	private Values values;

	//コンストラクター
	public WeakValueMap() {
		hashMap = new HashMap<K, WeakReference<V>>();
	}
	public WeakValueMap(int initialCapacity) {
		hashMap = new HashMap<K, WeakReference<V>>(initialCapacity);
	}
	public WeakValueMap(int initialCapacity, float loadFactor) {
		hashMap = new HashMap<K, WeakReference<V>>(initialCapacity, loadFactor);
	}
	public WeakValueMap(Map<? extends K,? extends V> m) {
		hashMap = new HashMap<K, WeakReference<V>>();
		putAll(m);
	}

    /**
     * Expunges stale entries from the hashMap.
     */
	private void expungeStaleEntries() {
		for(K key:  hashMap.keySet())
			if(hashMap.get(key).get() == null)
				//参照オブジェクトがクリアされたら、hashmapから削除
				hashMap.remove(key);
	}
	/**
     * WeakValue対応関数
     */
	//valueを弱い参照で保持し、古い値を返す
	public V put(K key, V value) {
		expungeStaleEntries();
		WeakReference<V> vr = new WeakReference<V>(value);
		return hashMap.put(key, vr).get();
	}
	//valueを弱い参照に変換し格納する
	public void putAll(Map<? extends K,? extends V> m) {
		expungeStaleEntries();
		for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
			put(e.getKey(), e.getValue());//この関数内valueを弱い参照に変換
	}
	public V get(Object key) {
		expungeStaleEntries();
		return hashMap.get(key).get();
	}
	public V remove(Object key) {
		expungeStaleEntries();
		return hashMap.remove(key).get();
	}
	public boolean containsValue(Object value){
		expungeStaleEntries();
		for (V v: values())
			if(v == value)
				return true;
		return false;
	}
    /**
     * Iterator
     */
    private abstract class HashIterator<E> implements Iterator<E> {
        private HashMap<K, V> strongHashMap;
        private Iterator<Map.Entry<K,V>> iterator;
        private Entry<K, V> current;

        /**
         * Strong reference needed to avoid disappearance of value
         * between iterator object arive
         */
        public HashIterator() {
    		expungeStaleEntries();
        	for(Entry<K, WeakReference<V>> e: hashMap.entrySet())
        		strongHashMap.put(e.getKey(), e.getValue().get());
        	iterator = strongHashMap.entrySet().iterator();

        }
        public boolean hasNext() {
            return iterator.hasNext();
        }
        final Entry<K,V> nextEntry() {
        	current = iterator.next();
        	return current;
        }
        public void remove() {
        	WeakValueMap.this.remove(current.getKey());
        	iterator.remove();
        }
    }
    private final class ValueIterator extends HashIterator<V> {
        public V next() {
            return nextEntry().getValue();
        }
    }
    private final class KeyIterator extends HashIterator<K> {
        public K next() {
            return nextEntry().getKey();
        }
    }
    private final class EntryIterator extends HashIterator<Map.Entry<K,V>> {
        public Map.Entry<K,V> next() {
            return nextEntry();
        }
    }
    /**
     * Collection/Set
     */
    public Collection<V> values() {
		expungeStaleEntries();
        Collection<V> vs = values;
        return (vs != null) ? vs : (values = new Values());
    }
    private final class Values extends AbstractCollection<V> {
    	@Override
        public Iterator<V> iterator() {
        	return new ValueIterator();
        }
    	@Override
        public int size() {
            return WeakValueMap.this.size();
        }
    	@Override
        public boolean contains(Object o) {
            return containsValue(o);
        }
    	@Override
        public void clear() {
        	WeakValueMap.this.clear();
        }
    }

    public Set<K> keySet() {
		expungeStaleEntries();
        return new KeySet();
    }
    private final class KeySet extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
        public int size() {
            return WeakValueMap.this.size();
        }
        public boolean contains(Object o) {
            return containsKey(o);
        }
        public boolean remove(Object o) {
            return WeakValueMap.this.remove(o) != null;
        }
        public void clear() {
        	WeakValueMap.this.clear();
        }
    }

    public Set<Map.Entry<K,V>> entrySet() {
		expungeStaleEntries();
        return new EntrySet();
    }
	private final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
		public Iterator<java.util.Map.Entry<K, V>> iterator() {
			return new EntryIterator();
		}
		public int size() {
			return WeakValueMap.this.size();
		}
		public boolean remove(Object o) {
            return WeakValueMap.this.remove(o) != null;
        }
        public void clear() {
			WeakValueMap.this.clear();
        }
	}
    /**
     * hashMapにすべて委譲
     */
	public boolean isEmpty() {
		expungeStaleEntries();
		return hashMap.isEmpty();
	}
	public int size() {
		expungeStaleEntries();
		return hashMap.size();
	}
	public boolean containsKey(Object key) {
		expungeStaleEntries();
		return hashMap.containsKey(key);
	}
	public int hashCode() {return hashMap.hashCode();}
	public void clear() {hashMap.clear();}
}