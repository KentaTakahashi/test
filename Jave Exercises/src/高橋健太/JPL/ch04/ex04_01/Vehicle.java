package 高橋健太.JPL.ch04.ex04_01;

public class Vehicle {

	private EnergySource mEnergySource;
	private int mSpeed;
	private int mDirection;
	private String mOwner;

	public Vehicle(int energy, int mSpeed, int mDirection, String mOwner) {
		mEnergySource = new GasTank(energy);//動力源はガソリンとする
		this.mSpeed = mSpeed;
		this.mDirection = mDirection;
		this.mOwner = mOwner;
	}
	public boolean start() {
		return (!mEnergySource.empty());//EnergySourceが空で無ければスタート可能
	}
}
