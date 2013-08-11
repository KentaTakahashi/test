package 高橋健太.JPL.ch03.ex03_07;

public class Attr {
	private final String name;
	private Object value = null;

	public Attr(String name) {
		this.name = name;
	}
	public Attr(String name, Object value) {
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
	public Object setValue(Object newValue) {
		Object oldVal = newValue;
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
