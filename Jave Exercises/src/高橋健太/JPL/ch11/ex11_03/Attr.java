package 高橋健太.JPL.ch11.ex11_03;

public class Attr<V> {
	private final String name;
	private V value = null;

	public Attr(String name) {
		this.name = name;
	}
	public Attr(String name, V value) {
		this.name = name;
		this.value = value;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @param value セットする value
	 */
	public Object setValue(V newValue) {
		V oldVal = newValue;
		this.value = newValue;
		return oldVal;
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "='" + value + "'";
	}
}
