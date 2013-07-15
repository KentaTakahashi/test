package 高橋健太.JPL.ch02.ex02_06;


public class LinkedList {

	private Vehicle mVehicle = null;
	private LinkedList next = null;

	public LinkedList(Vehicle obj, LinkedList next) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.mVehicle = obj;
		this.next = next;
	}
	public LinkedList(Vehicle obj) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.mVehicle = obj;
	}
	public Object get() {
		// TODO 自動生成されたメソッド・スタブ
		return mVehicle;
	}
	public LinkedList getNext() {
		// TODO 自動生成されたメソッド・スタブ
		return next;
	}

	public static void main(String[] args) {

		Vehicle test_1 = new Vehicle(50, 0, "Alice");
		Vehicle test_2 = new Vehicle(90, 180, "Bob");
		Vehicle test_3 = new Vehicle(70, 270, "Carol");

		LinkedList testList_3 = new LinkedList(test_3);
		LinkedList testList_2 = new LinkedList(test_2, testList_3);
		LinkedList testList_1 = new LinkedList(test_1, testList_2);

		LinkedList testList = testList_1;

		//次の連結ノードがなくなるまでデータをWhile文で出力
		while(testList != null) {

			System.out.println("id = " + testList.mVehicle.getID() + ", speed = " + testList.mVehicle.getSpeed()
					+ ", direction = " + testList.mVehicle.getDirection() + ", owner = " + testList.mVehicle.getOwner());

			testList = testList.next;
		}
		/*実行結果
		id = 0, speed = 50, direction = 0, owner = Alice
		id = 1, speed = 90, direction = 180, owner = Bob
		id = 2, speed = 70, direction = 270, owner = Carol
		*/
	}
}

class Vehicle {


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
}