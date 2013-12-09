package çÇã¥åíëæ.JPL.ch19.ex19_01;

public class Vehicle {


	static int nextID = 0;

	private int mID;
	private int mSpeed;
	private int mDirection;
	private String mOwner;

	public Vehicle() {
		mID = nextID;
		nextID++;

		mOwner = "Alice";
	}

	public Vehicle(String owner) {
		mID = nextID;
		nextID++;

		mOwner = owner;
	}
	static public int getMaxID() {
		return nextID - 1;
	}
	static public void setID(int id) {
		nextID = id;
	}
	public int getID() {
		return mID;
	}
	public int getSpeed() {
		return mSpeed;
	}
	public int getDirection() {
		return mDirection;
	}
	public String getOwner() {
		return mOwner;
	}
	public String toString() {
		String str =
				"ID:"			+ Integer.toString(mID) +
				", Speed:"		+ Integer.toString(mSpeed) +
				", Direction:"	+ Integer.toString(mDirection) +
				", Owner:"		+ mOwner;

		return str;
	}
}