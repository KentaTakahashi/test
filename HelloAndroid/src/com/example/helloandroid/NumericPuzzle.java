package com.example.helloandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	private long stopChronometer() {
		Chronometer chrono = (Chronometer) findViewById(R.id.chronometer1);
		chrono.stop();
		return SystemClock.elapsedRealtime() - chrono.getBase();
	}

	private void setStartButtonListener(){
		Button btn = (Button)findViewById(R.id.start_btn);

		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				startGame();
				startChronometer();
			}
		});
	}

	private void startGame() {
		int size = numImages.length;
		for(int i = 0; i < size - 2; i++)
		{
			int swap = (int) (Math.random() * (size - (i +1))) ;
			orders[i].swapImage(orders[i + swap]);
			gameStarted = true;
		}
	}

	private void complete() {
		long msec = stopChronometer();
		AlertDialog.Builder alertDlgBld = new AlertDialog.Builder(this);
		alertDlgBld.setTitle(R.string.complete_title);
		alertDlgBld.setMessage(msec / 1000 + "秒");
		alertDlgBld.setPositiveButton(
				R.string.complete_button,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		alertDlgBld.show();
	}

	private boolean isCompleted() {
		if(!gameStarted)return false;
		for(int i = 0; i <numImages.length; i++)
			if(numImages[i] != orders[i].getImageRes())return false;
		return true;
	}

	private void searchDir(int idx) {
		boolean searchUp 		= true;
		boolean searchDown 		= true;
		boolean searchRight 	= true;
		boolean searchLeft 		= true;


		if(idx < 4)	      searchUp	 	= false;
		if(idx > 11)      searchDown 	= false;
		if((idx % 4) == 0)searchRight 	= false;
		if((idx % 4) == 3)searchLeft 	= false;

		if(searchUp 	&& searchUp(idx))	return;
		if(searchDown 	&& searchDown(idx))	return;
		if(searchRight	&& searchRight(idx))return;
		if(searchLeft 	&& searchLeft(idx))	return;
	}

	private boolean searchUp(int idx) {
		int distance = 0;
		for(int i = idx - 4; i > -1; i -= 4)
		{
			distance--;
			if(orders[i].getImageRes() == R.drawable.blank)
			{
				swapUp(idx, distance);
				return true;
			}
		}
		return false;
	}
	private void swapUp(int idx, int distance) {
		for(int i = idx + (distance * 4); i < idx; i +=4)
		{
			orders[i].swapImage(orders[i + 4]);
		}
	}
	private boolean searchDown(int idx) {
		int distance = 0;
		for(int i = idx + 4; i < 16; i += 4)
		{
			distance++;
			if(orders[i].getImageRes() == R.drawable.blank)
			{
				swapDown(idx, distance);
				return true;
			}
		}
		return false;
	}
	private void swapDown(int idx, int distance) {
		for(int i = idx + (distance * 4); i > idx; i -=4)
		{
			orders[i].swapImage(orders[i - 4]);
		}
	}
	private boolean searchRight(int idx) {
		int distance = 0;
		int min = 0;
		min = idx - (idx % 4);
		for(int i = idx - 1; i >= min; i--)
		{
			distance--;
			if(orders[i].getImageRes() == R.drawable.blank)
			{
				swapLeft(idx, distance);
				return true;
			}
		}
		return false;
	}

	private void swapLeft(int idx, int distance) {
		for(int i = idx + distance; i < idx; i++)
		{
			orders[i].swapImage(orders[i + 1]);
		}
	}
	private boolean searchLeft(int idx) {
		int distance = 0;
		int max = 15;
		max = (idx + 4) - (idx + 4) % 4;
		for(int i = idx + 1; i < max; i++)
		{
			distance++;
			if(orders[i].getImageRes() == R.drawable.blank)
			{
				swapRight(idx, distance);
				return true;
			}
		}
		return false;
	}

	private void swapRight(int idx, int distance) {
		for(int i = idx + distance; i > idx; i--)
		{
			orders[i].swapImage(orders[i - 1]);
		}
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
			curImageID = resid;
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
			//for debug 何かクリックされたら、完成画面を表示する
			//if(true)complete();
		}

		public void swapImage(OrderController other) {
			int Previous = other.setImageRes(curImageID);
			setImageRes(Previous);
		}
	}
}
