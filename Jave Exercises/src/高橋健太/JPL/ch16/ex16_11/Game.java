package 高橋健太.JPL.ch16.ex16_11;


public class Game {
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
		// TODO 自動生成されたメソッド・スタブ

	}
	private static void reportException(String name, Exception e) {
		// TODO 自動生成されたメソッド・スタブ

	}
	private static String getNextPlayer() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
