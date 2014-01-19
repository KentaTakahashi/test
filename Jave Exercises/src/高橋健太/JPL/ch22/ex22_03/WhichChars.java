package 高橋健太.JPL.ch22.ex22_03;

import java.util.BitSet;
import java.util.HashMap;

public class WhichChars{

	private HashMap<BitSet, BitSet> used = new HashMap<BitSet, BitSet>();
	private static final BitSet filter = new BitSet(16);
	{
		filter.set(0xff00);
	}
	public WhichChars(String str) {
		for(int i = 0; i < str.length(); i++) {
			BitSet bs = new BitSet(16);
			bs.set(str.charAt(i));
			BitSet key = (BitSet) bs.clone();
			key.and(filter);
			used.put(key, bs);
		}
	}

	public String toString() {
		String desc = "[";

		for(BitSet bs: used.values()) {
			for(int i = bs.nextSetBit(0);
					i >= 0;
					i = bs.nextSetBit(i+1)) {
				desc += (char) i;
			}
		}
		return desc + "]";
	}

	public static void main(String[] args){
		WhichChars wc = new WhichChars("Testing 1 2 3");
		System.out.println(wc.toString());
	}

}