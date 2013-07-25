package 高橋健太.JPL.ch02.ex02_03;

public class Vehicle {


	static int nextID = 0;

	private final int mID;
	private int mSpeed;
	private int mDirection;
	private String mOwner;

	public Vehicle() {
		mID = nextID;
		nextID++;
	}

	public int getID() {
		return mID;
	}
}
