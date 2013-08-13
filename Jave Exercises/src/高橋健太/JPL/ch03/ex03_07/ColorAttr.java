package 高橋健太.JPL.ch03.ex03_07;

class ColorAttr extends Attr {
	private ScreenColor myColor; //変換された色

	public ColorAttr(String name, Object value) {
		super(name, value);
		decodeColor();
	}
	public ColorAttr(String name) {
		this(name, "transparent");
	}
	public ColorAttr(String name, ScreenColor value) {
		super(name, value.toString());
		myColor = value;
	}
	/* (非 Javadoc)
	 * @see 高橋健太.JPL.ch03.ex03_07.Attr#setValue(java.lang.Object)
	 */
	@Override
	public Object setValue(Object newValue) {
		Object retval =  super.setValue(newValue);
		decodeColor();
		return retval;
	}
	public ScreenColor setValue(ScreenColor newValue) {
		super.setValue(newValue.toString());
		ScreenColor oldvalue = myColor;
		myColor = newValue;
		return oldvalue;
	}
	public ScreenColor getColor() {
		return myColor;
	}
	protected void decodeColor() {
		if(getValue() == null)
			myColor = null;
		else
			myColor = new ScreenColor(getValue());
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		    return true;
		if (obj == null)
		    return false;
		//型が等しく、myColorが等しければtrueを返す
		if (getClass() != obj.getClass())
		    return false;
		ColorAttr other = (ColorAttr) obj;
		return myColor.equals(other.getColor());
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#hashCode()
	 * myColorのハッシュコードを返すこととする
	 */
	@Override
	public int hashCode() {
		return myColor.hashCode();
	}
}
