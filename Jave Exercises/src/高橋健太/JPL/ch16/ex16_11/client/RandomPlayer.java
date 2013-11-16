package 高橋健太.JPL.ch16.ex16_11.client;

import java.util.Random;

import 高橋健太.JPL.ch16.ex16_11.server.Player;

public class RandomPlayer extends Player {

	Random rnd = new Random();

	public static void main(String[] args) {}

	@Override
	public void thinking() {
		x = rnd.nextInt(3);
		y = rnd.nextInt(3);
	}

}
