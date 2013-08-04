package 高橋健太.JPL.ch03.ex03_02;

public class ConstructorLearning {

	public static void main(String[] args) {
		Y y = new Y();
	}
}

class X {
	protected int xMask = 0x00ff;
	protected int fullMask;
	{
		System.out.printf("Step 4: xMaxk = %04x, yMaxk = %04x, fullMask = %04x%n", xMask, 0, fullMask);
	}
	public X() {
		fullMask = xMask;
		System.out.printf("Step 5: xMaxk = %04x, yMaxk = %04x, fullMask = %04x%n", xMask, 0, fullMask);
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}
}

class Y extends X {
	protected int yMask = 0xff00;
	{
		System.out.printf("Step 6: xMaxk = %04x, yMaxk = %04x, fullMask = %04x%n", xMask, yMask, fullMask);
	}
	public Y() {
		fullMask |= yMask;
		System.out.printf("Step 7: xMaxk = %04x, yMaxk = %04x, fullMask = %04x%n", xMask, yMask, fullMask);
	}
}