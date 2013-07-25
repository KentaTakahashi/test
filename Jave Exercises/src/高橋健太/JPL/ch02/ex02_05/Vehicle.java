package 高橋健太.JPL.ch02.ex02_05;

public class Vehicle {


	static int nextID = 0;

	private final int mID;
	private int mSpeed;
	private int mDirection;
	private String mOwner;

	public Vehicle(int speed, int direction, String owner) {
		mID = nextID;
		nextID++;

		mSpeed = speed;
		mDirection = direction;
		mOwner = owner;

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

	public static void main(String[] args) {

		Vehicle test_1 = new Vehicle(50, 0, "Alice");
		Vehicle test_2 = new Vehicle(90, 180, "Bob");
		Vehicle test_3 = new Vehicle(70, 270, "Carol");

		System.out.println("test_1: id = " + test_1.getID() + ", speed = " + test_1.getSpeed()
				+ ", direction = " + test_1.getDirection() + ", owner = " + test_1.getOwner());

		System.out.println("test_2: id = " + test_2.getID() + ", speed = " + test_2.getSpeed()
				+ ", direction = " + test_2.getDirection() + ", owner = " + test_2.getOwner());

		System.out.println("test_3: id = " + test_3.getID() + ", speed = " + test_3.getSpeed()
				+ ", direction = " + test_3.getDirection() + ", owner = " + test_3.getOwner());
	}
}
/*実行結果
test_1: id = 0, speed = 50, direction = 0, owner = Alice
test_2: id = 1, speed = 90, direction = 180, owner = Bob
test_3: id = 2, speed = 70, direction = 270, owner = Carol

*/