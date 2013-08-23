package 高橋健太.JPL.ch03.ex03_02;

public class ConstructorLearning {

	public static void main(String[] args) {
		Y y = new Y();
	}
}

abstract class X {
	{
		log();
	}
	protected int xMask = 0x00ff;
	protected int fullMask;
	{
		//System.out.printf("Step 4: xMaxk = %04x, yMaxk = %04x, fullMask = %04x%n", xMask, 0, fullMask);
		log();
	}
	public X() {
		fullMask = xMask;
		//System.out.printf("Step 5: xMaxk = %04x, yMaxk = %04x, fullMask = %04x%n", xMask, 0, fullMask);
		log();
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}

	abstract void log();
}

class Y extends X {

	protected int yMask = 0xff00;
	private int step;
	{
		//System.out.printf("Step 6: xMaxk = %04x, yMaxk = %04x, fullMask = %04x%n", xMask, yMask, fullMask);
		log();
	}
	public Y() {
		fullMask |= yMask;
		//System.out.printf("Step 7: xMaxk = %04x, yMaxk = %04x, fullMask = %04x%n", xMask, yMask, fullMask);
		log();
	}
	@Override
	void log() {
		if(step == 0)step = 3;
		System.out.printf("Step %d - %d: xMaxk = %04x, yMaxk = %04x, fullMask = %04x%n"
				, step, step + 1, xMask, yMask, fullMask);
		step++;
	}
}