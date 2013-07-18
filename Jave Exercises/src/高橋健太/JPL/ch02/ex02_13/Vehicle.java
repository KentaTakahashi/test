package 高橋健太.JPL.ch02.ex02_13;

/*
 * 問
 * どのフィールドをprivateにすべきか、どのフィールドが変更を許すメソッドを持つか
 *
 * 答
 * 全フィールドをprivateにすべき
 * nextIDフィールド		一意に設定するため変更は許可しないべき
 * mSpeedフィールド		変更を許す
 * mDirectionフィールド	変更を許す
 * mOwnerフィールド		変更を許す
 */

public class Vehicle {


	private static int nextID = 0;

	private int mID;
	private int mSpeed;
	private int mDirection;
	private String mOwner;

	public Vehicle() {
		mID = nextID;
		nextID++;

		mOwner = "Alice";//デフォルトのオーナー
	}

	public Vehicle(String owner) {
		mID = nextID;
		nextID++;

		mOwner = owner;
	}
	static public int getMaxID() {
		return nextID - 1;
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
	public void setSpeed(int speed) {
		mSpeed = speed;
	}
	public void setDirection(int direction) {
		mDirection = direction;
	}
	public void setOwner(String owner) {
		mOwner = owner;
	}
	//ID,Speed,Direction,Ownerを連結した文字列を返す
	public String toString() {
		String str =
				"ID:"			+ Integer.toString(mID) +
				", Speed:"		+ Integer.toString(mSpeed) +
				", Direction:"	+ Integer.toString(mDirection) +
				", Owner:"		+ mOwner;

		return str;
	}
}