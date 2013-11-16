package 高橋健太.JPL.ch16.ex16_12.server;

import java.util.Random;

public class CPU extends Player {

	Random rnd = new Random();

	@Override
	public void thinking() {
		x = rnd.nextInt(3);
		y = rnd.nextInt(3);
	}
}
