package 高橋健太.JPL.ch22.ex22_02;

import java.util.HashSet;
import java.util.Iterator;

public class WhichChars{

	private HashSet<Character> used = new HashSet<Character>();

	public WhichChars(String str) {
		for(int i = 0; i < str.length(); i++)
			used.add(str.charAt(i));
	}

	public String toString() {
		String desc = "[";

		Iterator<Character> it = used.iterator();
		while(it.hasNext())
			desc += it.next();

		return desc + "]";
	}

	public static void main(String[] args){
		WhichChars wc = new WhichChars("Testing 1 2 3");
		System.out.println(wc.toString());
	}

}