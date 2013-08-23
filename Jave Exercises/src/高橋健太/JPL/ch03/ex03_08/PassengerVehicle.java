package 高橋健太.JPL.ch03.ex03_08;

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
	@Override
	public PassengerVehicle clone() {

		PassengerVehicle ret = null;
		ret = (PassengerVehicle) super.clone();
		ret.setDirection(this.getDirection());
		ret.setSpeed(this.getSpeed());

		return ret;
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
		PassengerVehicle clone_1 = test_1.clone();
		Vehicle test_2 = new Vehicle("Alice");
		Vehicle clone_2 = test_2.clone();

		System.out.println("test_1:" + test_1.toString());
		System.out.println("clone_1:" + clone_1.toString());
		System.out.println("test_2:" + test_2.toString());
		System.out.println("clone_2:" + clone_2.toString());
	}
}