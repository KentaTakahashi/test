package 高橋健太.JPL.ch14.ex14_03;

public class AddNum{

	int num = 0;

	//引数を現在の値に加え表示する
	public synchronized void add(int num) {
		this.num += num;
		System.out.println(this.num);
	}
}