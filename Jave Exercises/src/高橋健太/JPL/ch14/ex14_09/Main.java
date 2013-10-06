package 高橋健太.JPL.ch14.ex14_09;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	private static ThreadGroup World = new ThreadGroup("World");
	private static String[] Continent = {"America", "Europe", "Africa", "Asia", "Australia"};
	private static List<ThreadGroup> ContinentList = new ArrayList<ThreadGroup>();

	private static String[] Name = {"Alice", "Bob", "Carol", "Dave", "Ellen", "Frank"};
	private static int MaxLifeSpan = 20;

	public static void main(String[] args) {
		{
			for(String continent: Continent) {
				ContinentList.add(new ThreadGroup(World, continent));
			}
		}
		ThreadGroupTree.show(World);
		while(true) {
			try {
				Thread.sleep(1000);  //1 sec sleep
				createShortLifeThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private static void createShortLifeThread() {
		//ランダムに国、名前、寿命を決定
        Random rnd = new Random();
        final int continentIndex = rnd.nextInt(ContinentList.size());
		final int nameIndex      = rnd.nextInt(Name.length);
		final int lifeSpan       = rnd.nextInt(MaxLifeSpan);

		//ランダムに決まった国（スレッドグループ）内にスレッドを生成
		new Thread(ContinentList.get(continentIndex), new Runnable() {
			public void run() {
				try {
					System.out.println("------" + Name[nameIndex] + " is born(lifeSpan:" + lifeSpan + "sec)------");
					Thread.sleep(lifeSpan * 1000); //lifeSpan sec sleep
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					System.out.println("------" + Name[nameIndex] + " is dead" + "------");
				}
			}
		}, Name[nameIndex]).start();
	}

}
