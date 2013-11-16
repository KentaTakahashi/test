package 高橋健太.JPL.ch16.ex16_11.client;


import 高橋健太.JPL.ch16.ex16_11.server.Player;

public class SimplePlayer extends Player {

	int index = 0;

	public static void main(String[] args) {}

	@Override
	public void thinking() {
		x = index % 3;
		y = index / 3;
		index = (index + 1) % 9;
	}

}
