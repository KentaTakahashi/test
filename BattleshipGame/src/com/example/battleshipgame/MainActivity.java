package com.example.battleshipgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final int LIFE_WARSHIP = 3;
	public static final int LIFE_DESTROYER = 2;
	public static final int LIFE_SUBMARINE = 1;

	private myShip mWarship;
	private myShip mDestroyer;
	private myShip mSubmarine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initGame();

		TextView  logText = (TextView)findViewById(R.id.logTextView);
		logText.setText("テスト\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"ee\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"qq\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"rr\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"\n" +
				"99");
	}

	private void initGame() {
		//TODO 要エラー処理、配置箇所重複時に再配置
		mWarship = new myShip(1, 1, LIFE_WARSHIP);
		mDestroyer = new myShip(3, 3, LIFE_DESTROYER);
		mSubmarine = new myShip(5, 5, LIFE_SUBMARINE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class myShip {

		int mRow;
		int mColumn;
		int mLife;

		public myShip(int row, int column, int life) {
			mRow = row;
			mColumn = column;
			mLife = life;
		}


	}


}
