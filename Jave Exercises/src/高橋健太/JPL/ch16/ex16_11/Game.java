package 高橋健太.JPL.ch16.ex16_11;

import java.util.Arrays;
import java.util.List;


public class Game {

	static List<String> nameList = Arrays.asList("SimplePlayer", "RandomPlayer", "CenterPlayer");
	static int index = 0;

	public boolean isWin = false;

	public static void main(String[] args) {
		String name;

		while((name = getNextPlayer()) != null) {
			try{
				PlayerLoader loader = new PlayerLoader();
				Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
				Player player = classOf.newInstance();
				Game game = new Game();
				player.play(game);
				game.reportScore(name);
			} catch (Exception e) {
				reportException(name, e);
			}
		}
	}
	private void reportScore(String name) {
		String res = (isWin? "Win": "Lose");
		System.out.println(name + res);
	}
	private static void reportException(String name, Exception e) {
		System.out.println(name + " cant Load");
	}
	private static String getNextPlayer() {
		if(index < nameList.size())
			return nameList.get(index++);
		else
			return null;
	}
}
