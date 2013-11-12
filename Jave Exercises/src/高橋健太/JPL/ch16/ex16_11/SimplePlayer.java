package 高橋健太.JPL.ch16.ex16_11;

public class SimplePlayer extends Player {

	@Override
	public void play(Game game) {
		System.out.println("debug! always Win!");
		game.isWin = true;
	}

}
