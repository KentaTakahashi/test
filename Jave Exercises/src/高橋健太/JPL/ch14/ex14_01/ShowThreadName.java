package 高橋健太.JPL.ch14.ex14_01;

public class ShowThreadName {

	public static void main(String[] args) {
		Thread th = Thread.currentThread();
		System.out.println(th.getName());//mainと表示された
	}

}