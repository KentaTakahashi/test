package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class NumericPuzzle extends Activity {

	//called activity is first created
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		setStartButtonListener();
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
}
