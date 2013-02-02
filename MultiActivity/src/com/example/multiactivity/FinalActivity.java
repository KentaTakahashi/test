package com.example.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FinalActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.final_activity);

		Button btnNext = (Button) this.findViewById(R.id.button3);
		btnNext.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(FinalActivity.this, FirstActivity.class);
				startActivity(intent);
			}
		});
	}
}
