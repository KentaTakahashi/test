package 高橋健太.JPL.ch02.ex02_07;

public class Vehicle {


	static int nextID = 0;

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

	public static void main(String[] args) {

		//引数なしコンストラクタ、デフォルトで所有者をAliceと設定
		Vehicle test_1 = new Vehicle();
		Vehicle test_2 = new Vehicle();
		Vehicle test_3 = new Vehicle();

		System.out.println("引数なしのコンストラクタ");
		System.out.println("id = " + test_1.getID() + ", owner = " + test_1.getOwner());
		System.out.println("id = " + test_2.getID() + ", owner = " + test_2.getOwner());
		System.out.println("id = " + test_3.getID() + ", owner = " + test_3.getOwner());

		//IDを初期化
		Vehicle.setID(0);
		//引数ありのコンストラクタ、所有者をAliceと設定
		Vehicle test_4 = new Vehicle("Alice");
		Vehicle test_5 = new Vehicle("Alice");
		Vehicle test_6 = new Vehicle("Alice");

		System.out.println("引数ありのコンストラクタ");
		System.out.println("id = " + test_4.getID() + ", owner = " + test_4.getOwner());
		System.out.println("id = " + test_5.getID() + ", owner = " + test_5.getOwner());
		System.out.println("id = " + test_6.getID() + ", owner = " + test_6.getOwner());
	}
}
/*実行結果
引数なしのコンストラクタ
id = 0, owner = Alice
id = 1, owner = Alice
id = 2, owner = Alice
引数ありのコンストラクタ
id = 0, owner = Alice
id = 1, owner = Alice
id = 2, owner = Alice
*/