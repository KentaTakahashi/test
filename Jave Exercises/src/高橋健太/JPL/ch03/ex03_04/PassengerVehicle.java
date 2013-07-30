package 高橋健太.JPL.ch03.ex03_04;

public class PassengerVehicle extends Vehicle {
	private int mSeatCapacity;
	private int mRiderShip;

	//コンストラクタ
	public PassengerVehicle(String owner, int capa) {
		super(owner);
		this.mSeatCapacity = capa;
	}
	public PassengerVehicle(String owner, int capa, int rider) {
		this(owner, capa);
		this.mRiderShip = rider;
	}

	public int getmSeatCapacity() {
		return mSeatCapacity;
	}
	public void setmSeatCapacity(int mSeatCapacity) {
		this.mSeatCapacity = mSeatCapacity;
	}
	public int getmRiderShip() {
		return mRiderShip;
	}
	public void setmRiderShip(int mRiderShip) {
		this.mRiderShip = mRiderShip;
	}



	@Override
	public String toString() {
		String str = super.toString() +
				", SeatCapacity:"	+ Integer.toString(mSeatCapacity) +
				", RiderShip:"		+ Integer.toString(mRiderShip);

		return str;
	}
	public static void main(String[] args) {
		PassengerVehicle test_1 = new PassengerVehicle("Alice", 4, 2);
		PassengerVehicle test_2 = new PassengerVehicle("Bob", 5, 5);
		PassengerVehicle test_3 = new PassengerVehicle("Carol", 6, 1);

		System.out.println(test_1.toString());
		System.out.println(test_2.toString());
		System.out.println(test_3.toString());
	}
}