package 高橋健太.JPL.ch04.ex04_01;

interface EnergySource {
	boolean empty();
}
class GasTank implements EnergySource {

	private int LitersOfGasoline;//タンクに入っているガソリンの量

	public GasTank(int litersOfGasoline) {
		LitersOfGasoline = litersOfGasoline;
	}
	@Override
	public boolean empty() {
		return (LitersOfGasoline == 0);
	}

}
class Battery implements EnergySource {

	private int AhOfBattery;//タンクに入っているバッテリー量

	public Battery(int ahofbattery) {
		AhOfBattery = ahofbattery;
	}
	@Override
	public boolean empty() {
		return (AhOfBattery == 0);
	}

}