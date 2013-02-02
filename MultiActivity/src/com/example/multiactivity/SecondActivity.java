package com.example.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		Button btnNext = (Button) this.findViewById(R.id.button2);
		btnNext.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(SecondActivity.this, FinalActivity.class);
				startActivity(intent);
			}
		});
	}
}
