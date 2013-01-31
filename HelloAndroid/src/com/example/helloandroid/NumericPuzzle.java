package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class NumericPuzzle extends Activity {

static final int imageButtons[] = {
	R.id.Button01,
	R.id.Button02,
	R.id.Button03,
	R.id.Button04,
	R.id.Button05,
	R.id.Button06,
	R.id.Button07,
	R.id.Button08,
	R.id.Button09,
	R.id.Button10,
	R.id.Button11,
	R.id.Button12,
	R.id.Button13,
	R.id.Button14,
	R.id.Button15,
	R.id.Button16
	};

static final int numImages[] = {
	R.drawable.num01,
	R.drawable.num02,
	R.drawable.num03,
	R.drawable.num04,
	R.drawable.num05,
	R.drawable.num06,
	R.drawable.num07,
	R.drawable.num08,
	R.drawable.num09,
	R.drawable.num10,
	R.drawable.num11,
	R.drawable.num12,
	R.drawable.num13,
	R.drawable.num14,
	R.drawable.num15,
	R.drawable.blank
	};

private boolean gameStarted = false;

OrderController orders[] = new OrderController[imageButtons.length];

	//called activity is first created
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		createOrderController();
		setStartButtonListener();
	}
	private void createOrderController() {
		for(int i = 0; i < imageButtons.length; i++) {
			ImageButton imgbtn = (ImageButton) findViewById(imageButtons[i]);
			orders[i] = new OrderController(imgbtn, i, numImages[i]);
		}
	}
	private void startChronometer() {
		Chronometer chrono = (Chronometer)findViewById(R.id.chronometer1);
		chrono.setBase(SystemClock.elapsedRealtime());
		chrono.start();
	}

	private void setStartButtonListener(){
		Button btn = (Button)findViewById(R.id.start_btn);

		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				//startGame();
				startChronometer();
			}
		});


	}


	private void complete() {
		// TODO 自動生成されたメソッド・スタブ

	}

	private boolean isCompleted() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	private void searchDir(int idx2) {
		// TODO 自動生成されたメソッド・スタブ

	}

	class OrderController implements OnClickListener {

		ImageButton imgBtn;
		int idx = 0;
		int curImageID = 0;

		public OrderController(ImageButton ibtn, int i, int resid) {
			imgBtn = ibtn;
			idx = i;
			setImageRes(resid);
			imgBtn.setOnClickListener(this);
		}

		public int setImageRes(int resid) {
			int old = curImageID;
			imgBtn.setImageResource(resid);
			return old;
		}

		public int getImageRes() {
			return curImageID;
		}

		@Override
		public void onClick(View v) {
			if(curImageID == R.drawable.blank){
				return;
			}
			searchDir(idx);

			if(isCompleted()){
				complete();
			}
		}


		public void swapImage(OrderController other) {
			int Previous = other.setImageRes(curImageID);
			setImageRes(Previous);
		}
	}
}
