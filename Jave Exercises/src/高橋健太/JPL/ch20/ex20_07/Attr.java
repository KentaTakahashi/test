package 高橋健太.JPL.ch20.ex20_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Attr {
	private final String name;
	private Object value = null;
	private DataInputStream in = null;

	public Attr(String name) {
		this.name = name;
	}
	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	public Attr(DataInputStream in) throws IOException {
		this.in = in;
		this.name = in.toString();
	}
	public String getName() {
		return name;
	}
	public Object getValue() {
		return value;
	}
	public Object setValue(Object newValue) {
		Object oldVal = newValue;
		this.value = newValue;
		return oldVal;
	}
	@Override
	public String toString() {
		return name + "='" + value + "'";
	}
	public void writeDataOutputStream(DataOutputStream out) throws NumberFormatException, IOException {
		if(in == null) return;

		byte[] b = new byte[1000];
		in.read(b);
		out.write(b);
	}
}
