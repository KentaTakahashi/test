package 高橋健太.JPL.ch03.ex03_06;

public abstract class EnergySource {
	public abstract boolean empty();
}
class GasTank extends EnergySource {

	private int LitersOfGasoline;//タンクに入っているガソリンの量

	public GasTank(int litersOfGasoline) {
		LitersOfGasoline = litersOfGasoline;
	}
	@Override
	public boolean empty() {
		return (LitersOfGasoline == 0);
	}

}
class Battery extends EnergySource {

	private int AhOfBattery;//タンクに入っているバッテリー量

	public Battery(int ahofbattery) {
		AhOfBattery = ahofbattery;
	}
	@Override
	public boolean empty() {
		return (AhOfBattery == 0);
	}

}