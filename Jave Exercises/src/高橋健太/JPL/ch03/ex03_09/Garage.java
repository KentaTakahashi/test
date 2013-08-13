package 高橋健太.JPL.ch03.ex03_09;



public class Garage implements Cloneable {

	private Vehicle[] mVehicles;
	private int mCapacity;

	public Garage(int capa) {
		mCapacity = capa;
		mVehicles = new Vehicle[mCapacity];
	}
	@Override
	public Garage clone() {
		Garage ret = new Garage(getCapacity());
		for(int i = 0; i < mCapacity; i++) {
			ret.addVehicle(this.getVehicle(i).clone(), i);
		}
		return ret;
	}
	public void addVehicle(Vehicle v, int index) {
		mVehicles[index] = v;
	}
	public Vehicle getVehicle(int index) {
		return mVehicles[index];
	}
	public int getCapacity() {
		return mCapacity;
	}
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < mCapacity; i++) {
			if(mVehicles[i] != null)
				str += mVehicles[i].toString() + "\n";
		}
		return str;
	}
	public static void main(String[] args) {
		System.out.println("--------original--------");
		Garage g = new Garage(3);
		g.addVehicle(new Vehicle("Alice"), 0);
		g.addVehicle(new Vehicle("Bob"), 1);
		g.addVehicle(new Vehicle("Carol"), 2);
		System.out.println(g.toString());

		System.out.println("--------clone--------");
		Garage gClone = g.clone();
		System.out.println(gClone.toString());
	}
}
/* 出力結果
--------original--------
ID:0, Speed:0, Direction:0, Owner:Alice
ID:1, Speed:0, Direction:0, Owner:Bob
ID:2, Speed:0, Direction:0, Owner:Carol

--------clone--------
ID:3, Speed:0, Direction:0, Owner:Alice
ID:4, Speed:0, Direction:0, Owner:Bob
ID:5, Speed:0, Direction:0, Owner:Carol
*/