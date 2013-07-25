package 高橋健太.JPL.ch02.ex02_17;

public class Vehicle {

	private static int nextID = 0;
	enum TURN {TURN_LEFT, TURN_RIGHT}
	public static final boolean TURN_LEFT 	= true;
	public static final boolean TURN_RIGHT	= false;

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
	//乗り物の現在のスピードを引数で渡された値にへんこうする
	public void changeSpeed(int speed) {
		this.mSpeed  = speed;
	}
	//乗り物のスピードをゼロにするstopメソッド
	public void stop() {
		this.mSpeed  = 0;
	}
	//引数として回転する角度を受け取り、設定する
	public void turn(int direction) {
		mDirection = direction;
	}
	//引数として左右どちらに回転するかを受け取り、設定する
	public void turn(boolean turn_left) {
		if(turn_left) {
			mDirection = 90;
		} else {
			mDirection = -90;
		}
	}
}