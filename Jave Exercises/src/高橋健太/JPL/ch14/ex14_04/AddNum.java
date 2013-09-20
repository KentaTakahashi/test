package 高橋健太.JPL.ch14.ex14_04;

public class AddNum{

	static int num = 0;

	//引数を現在の値に加え表示する
	static synchronized void add(int num) {
		AddNum.num += num;
		System.out.println(AddNum.num);
	}
}