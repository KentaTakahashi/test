package com.example.battleshipgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	public static final int LIFE_WARSHIP = 3;
	public static final int LIFE_DESTROYER = 2;
	public static final int LIFE_SUBMARINE = 1;

	private Button mButton_1_A;
	private Button mButton_1_B;
	private Button mButton_1_C;
	private Button mButton_1_D;
	private Button mButton_1_E;
	private Button mButton_2_A;
	private Button mButton_2_B;
	private Button mButton_2_C;
	private Button mButton_2_D;
	private Button mButton_2_E;
	private Button mButton_3_A;
	private Button mButton_3_B;
	private Button mButton_3_C;
	private Button mButton_3_D;
	private Button mButton_3_E;
	private Button mButton_4_A;
	private Button mButton_4_B;
	private Button mButton_4_C;
	private Button mButton_4_D;
	private Button mButton_4_E;
	private Button mButton_5_A;
	private Button mButton_5_B;
	private Button mButton_5_C;
	private Button mButton_5_D;
	private Button mButton_5_E;

	private myShip mWarship;
	private myShip mDestroyer;
	private myShip mSubmarine;
	private TextView logText;

	private StringBuilder mLogBuffer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//それぞれのボタンにリスナーを設定する
		setButtonListener();
		//ゲーム初期化
		initGame();

		logText = (TextView)findViewById(R.id.logTextView);
		mLogBuffer = new StringBuilder("ゲームスタート!\n");
		logText.setText(mLogBuffer);

		//test buttonに色と文字を設定
		mButton_1_A.setText("W");
		mButton_1_A.setTextColor(0xffff0000);
		mButton_3_C.setText("D");
		mButton_3_C.setTextColor(0xff00ff00);
		mButton_5_E.setText("S");
		mButton_5_E.setTextColor(0xff0000ff);
	}

	//それぞれのボタンにリスナーを設定する
	private void setButtonListener() {
		// TODO 自動生成されたメソッド・スタブ

		mButton_1_A = (Button)findViewById(R.id.Button_1_A);
		mButton_1_B = (Button)findViewById(R.id.Button_1_B);
		mButton_1_C = (Button)findViewById(R.id.Button_1_C);
		mButton_1_D = (Button)findViewById(R.id.Button_1_D);
		mButton_1_E = (Button)findViewById(R.id.Button_1_E);
		mButton_2_A = (Button)findViewById(R.id.Button_2_A);
		mButton_2_B = (Button)findViewById(R.id.Button_2_B);
		mButton_2_C = (Button)findViewById(R.id.Button_2_C);
		mButton_2_D = (Button)findViewById(R.id.Button_2_D);
		mButton_2_E = (Button)findViewById(R.id.Button_2_E);
		mButton_3_A = (Button)findViewById(R.id.Button_3_A);
		mButton_3_B = (Button)findViewById(R.id.Button_3_B);
		mButton_3_C = (Button)findViewById(R.id.Button_3_C);
		mButton_3_D = (Button)findViewById(R.id.Button_3_D);
		mButton_3_E = (Button)findViewById(R.id.Button_3_E);
		mButton_4_A = (Button)findViewById(R.id.Button_4_A);
		mButton_4_B = (Button)findViewById(R.id.Button_4_B);
		mButton_4_C = (Button)findViewById(R.id.Button_4_C);
		mButton_4_D = (Button)findViewById(R.id.Button_4_D);
		mButton_4_E = (Button)findViewById(R.id.Button_4_E);
		mButton_5_A = (Button)findViewById(R.id.Button_5_A);
		mButton_5_B = (Button)findViewById(R.id.Button_5_B);
		mButton_5_C = (Button)findViewById(R.id.Button_5_C);
		mButton_5_D = (Button)findViewById(R.id.Button_5_D);
		mButton_5_E = (Button)findViewById(R.id.Button_5_E);

		mButton_1_A.setOnClickListener(this);
		mButton_1_B.setOnClickListener(this);
		mButton_1_C.setOnClickListener(this);
		mButton_1_D.setOnClickListener(this);
		mButton_1_E.setOnClickListener(this);
		mButton_2_A.setOnClickListener(this);
		mButton_2_B.setOnClickListener(this);
		mButton_2_C.setOnClickListener(this);
		mButton_2_D.setOnClickListener(this);
		mButton_2_E.setOnClickListener(this);
		mButton_3_A.setOnClickListener(this);
		mButton_3_B.setOnClickListener(this);
		mButton_3_C.setOnClickListener(this);
		mButton_3_D.setOnClickListener(this);
		mButton_3_E.setOnClickListener(this);
		mButton_4_A.setOnClickListener(this);
		mButton_4_B.setOnClickListener(this);
		mButton_4_C.setOnClickListener(this);
		mButton_4_D.setOnClickListener(this);
		mButton_4_E.setOnClickListener(this);
		mButton_5_A.setOnClickListener(this);
		mButton_5_B.setOnClickListener(this);
		mButton_5_C.setOnClickListener(this);
		mButton_5_D.setOnClickListener(this);
		mButton_5_E.setOnClickListener(this);


	}

	private void initGame() {
		// TODO 要エラー処理、配置箇所重複時に再配置
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

		public  boolean polling(int row, int column) {
			if((row == mRow) && (column == mColumn) && (mLife > 0)) {
				return true;
			} else {
				return false;
			}
		}

		public void attack() {
			mLife--;
		}

		// 艦を移動させる、衝突判定等のエラー処理は呼び出し側が実施する
		public void move(int row, int column){
			mRow = row;
			mColumn = column;
		}
		public boolean isAlive() {
			// TODO 自動生成されたメソッド・スタブ
			if(mLife > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.Button_1_A:
			torpedo(1, 1);
			break;
		case R.id.Button_1_B:
			torpedo(1, 2);
			break;
		case R.id.Button_1_C:
			torpedo(1, 3);
			break;
		case R.id.Button_1_D:
			torpedo(1, 4);
			break;
		case R.id.Button_1_E:
			torpedo(1, 5);
			break;
		case R.id.Button_2_A:
			torpedo(2, 1);
			break;
		case R.id.Button_2_B:
			torpedo(2, 2);
			break;
		case R.id.Button_2_C:
			torpedo(2, 3);
			break;
		case R.id.Button_2_D:
			torpedo(2, 4);
			break;
		case R.id.Button_2_E:
			torpedo(2, 5);
			break;
		case R.id.Button_3_A:
			torpedo(3, 1);
			break;
		case R.id.Button_3_B:
			torpedo(3, 2);
			break;
		case R.id.Button_3_C:
			torpedo(3, 3);
			break;
		case R.id.Button_3_D:
			torpedo(3, 4);
			break;
		case R.id.Button_3_E:
			torpedo(3, 5);
			break;
		case R.id.Button_4_A:
			torpedo(4, 1);
			break;
		case R.id.Button_4_B:
			torpedo(4, 2);
			break;
		case R.id.Button_4_C:
			torpedo(4, 3);
			break;
		case R.id.Button_4_D:
			torpedo(4, 4);
			break;
		case R.id.Button_4_E:
			torpedo(4, 5);
			break;
		case R.id.Button_5_A:
			torpedo(5, 1);
			break;
		case R.id.Button_5_B:
			torpedo(5, 2);
			break;
		case R.id.Button_5_C:
			torpedo(5, 3);
			break;
		case R.id.Button_5_D:
			torpedo(5, 4);
			break;
		case R.id.Button_5_E:
			torpedo(5, 5);
			break;
		}
		//終了判定
		isFinish();
		//ログ出力;
		logText.setText(mLogBuffer);
	}

	private void isFinish() {
		// 全艦撃沈してたら終了
		if((!mWarship.isAlive()) && (!mDestroyer.isAlive()) && (!mSubmarine.isAlive())){
			mLogBuffer.insert(0, "全艦撃沈!\n");
		}
	}

	private void torpedo(int row, int column) {
		// TODO 自動生成されたメソッド・スタブ

		// 直撃判定
		if(mWarship.polling(row, column)) {
			mWarship.attack();
			mLogBuffer.insert(0, "戦艦直撃!!!\n");
		}
		else if(mDestroyer.polling(row, column)) {
			mDestroyer.attack();
			mLogBuffer.insert(0, "駆逐艦直撃!!\n");
		}
		else if(mSubmarine.polling(row, column)) {
			mSubmarine.attack();
			mLogBuffer.insert(0, "潜水艦直撃!\n");
		}
		// 隣接判定
		else if(	(mWarship.polling(row - 1, column - 1)) || (mDestroyer.polling(row - 1, column - 1)) || (mSubmarine.polling(row - 1, column - 1)) ||
					(mWarship.polling(row - 1, column + 0)) || (mDestroyer.polling(row - 1, column + 0)) || (mSubmarine.polling(row - 1, column + 0)) ||
					(mWarship.polling(row - 1, column + 1)) || (mDestroyer.polling(row - 1, column + 1)) || (mSubmarine.polling(row - 1, column + 1)) ||
					(mWarship.polling(row + 0, column - 1)) || (mDestroyer.polling(row + 0, column - 1)) || (mSubmarine.polling(row + 0, column - 1)) ||
					(mWarship.polling(row + 0, column + 0)) || (mDestroyer.polling(row + 0, column + 0)) || (mSubmarine.polling(row + 0, column + 0)) ||
					(mWarship.polling(row + 0, column + 1)) || (mDestroyer.polling(row + 0, column + 1)) || (mSubmarine.polling(row + 0, column + 1)) ||
					(mWarship.polling(row + 1, column - 1)) || (mDestroyer.polling(row + 1, column - 1)) || (mSubmarine.polling(row + 1, column - 1)) ||
					(mWarship.polling(row + 1, column + 0)) || (mDestroyer.polling(row + 1, column + 0)) || (mSubmarine.polling(row + 1, column + 0)) ||
					(mWarship.polling(row + 1, column + 1)) || (mDestroyer.polling(row + 1, column + 1)) || (mSubmarine.polling(row + 1, column + 1))
			) {
			mLogBuffer.insert(0, "水しぶき\n");
		}
		//魚雷はずれ
		else {
			mLogBuffer.insert(0, "はずれ\n");
		}
		//logに魚雷発射座標挿入
		mLogBuffer.insert(0, pointToString(row, column) + ":");
	}

	private String pointToString(int row, int column) {
		// TODO 自動生成されたメソッド・スタブ
		if(column == 1)			return (row + "-A");
		else if(column == 2)	return (row + "-B");
		else if(column == 3)	return (row + "-C");
		else if(column == 4)	return (row + "-D");
		else if(column == 5)	return (row + "-E");
		else return "座標Error";
	}
}
