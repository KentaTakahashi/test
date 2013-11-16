package 高橋健太.JPL.ch16.ex16_11.server;

import java.util.Arrays;
import java.util.List;


public class Game {

	static List<String> nameList = Arrays.asList("高橋健太.JPL.ch16.ex16_11.client.SimplePlayer", "高橋健太.JPL.ch16.ex16_11.client.RandomPlayer");
	static int index = 0;


	private int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	private Player P_1;
	private Player P_2;
	private Player currentPlayer;

	private final static int matchCount = 10;

	public boolean is_P_1_Win = false;
	public Game(Player player, Player cpu) {
		P_1 = player;
		P_2 = cpu;
		currentPlayer = P_1;
	}
	public static void main(String[] args) {
		String name;

		while((name = getNextPlayer()) != null) {
			try{
				PlayerLoader loader = new PlayerLoader();
				Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);

				//Player作成
				Player player = classOf.newInstance();
				//対戦用CPU作成
				Player cpu = new CPU();

				for(int i = 0; i < matchCount; i++) {
					Game game = new Game(player, cpu);
					game.start();
					game.reportScore(name);
				}

			} catch (Exception e) {
				reportException(name, e);
			}
		}
	}
	private void start() {
		int count = 0;

		//盤上が埋まるか勝敗が決まるまでまで実行
		while(count < 9) {
			//currentPlayerは盤上のどこかに一手指せるまで考える
			do{
				currentPlayer.thinking();
			}while(!put(currentPlayer.x, currentPlayer.y));
			currentPlayer = (currentPlayer == P_1? P_2: P_1);//currentPlayer交代

			//勝敗がついたかチェック
			switch(isFinish()) {
			case -1:
				return;
			case 1:
				is_P_1_Win = true;
				return;
			case 0:
				break;//roop継続
			}
			count++;
		}

	}

	private boolean put(int x, int y) {
		if(x < 0 || x > 2 || y < 0 || y > 2 )
			return false;//盤外を指定してきたら、falseを返す
		else if(board[x][y] != 0)
			return false;//すでにさしている場所を指定してきたら、falseを返す
		else {
			//指し手がP_1なら対象の盤目を1にP_2なら-1にする
			board[x][y] = (currentPlayer == P_1? 1: -1);
			//System.out.println("debug board[" + x + "][" + y + "] = " + board[x][y]);
			return true;
		}
	}

	private int isFinish() {
		if(
			board[0][0] + board[0][1] + board[0][2] == 3 ||
			board[1][0] + board[1][1] + board[1][2] == 3 ||
			board[2][0] + board[2][1] + board[2][2] == 3 ||
			board[0][0] + board[1][0] + board[2][0] == 3 ||
			board[0][1] + board[1][1] + board[2][1] == 3 ||
			board[0][2] + board[1][2] + board[2][2] == 3 ||
			board[0][0] + board[1][1] + board[2][2] == 3 ||
			board[0][2] + board[1][1] + board[2][0] == 3
				) return 1;
		else if (
			board[0][0] + board[0][1] + board[0][2] == -3 ||
			board[1][0] + board[1][1] + board[1][2] == -3 ||
			board[2][0] + board[2][1] + board[2][2] == -3 ||
			board[0][0] + board[1][0] + board[2][0] == -3 ||
			board[0][1] + board[1][1] + board[2][1] == -3 ||
			board[0][2] + board[1][2] + board[2][2] == -3 ||
			board[0][0] + board[1][1] + board[2][2] == -3 ||
			board[0][2] + board[1][1] + board[2][0] == -3
				) return -1;
		else return 0;
	}

	private void reportScore(String name) {
		String res = (is_P_1_Win? " Win": " Lose");
		System.out.println(name + res);

		String[] tmp = new String[3];
		System.out.println("=============");
		for(int y = 0; y < 3; y++) {
			tmp[y] = "";
			for(int x = 0; x < 3; x++) {
				if(board[x][y] == 1) tmp[y] += "|○|";
				else if(board[x][y] == -1) tmp[y] += "|×|";
				else tmp[y] += "|  |";
			}
			System.out.println(tmp[y]);
			System.out.println("=============");
		}

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
