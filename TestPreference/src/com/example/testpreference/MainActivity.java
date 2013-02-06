package com.example.testpreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener{

	private EditText edit01;
	private Button btnPut;
	private Button btnGet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		edit01 = new EditText(this);
		edit01.setText("");
		edit01.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(edit01);

		btnPut = new Button(this);
		btnPut.setText("保存");
		btnPut.setOnClickListener(this);
		btnPut.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btnPut);

		btnGet = new Button(this);
		btnGet.setText("読み込み");
		btnGet.setOnClickListener(this);
		btnGet.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(btnGet);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v == btnPut){
			SharedPreferences prefer = getSharedPreferences("TestPreference", MODE_PRIVATE);
			SharedPreferences.Editor editor =prefer.edit();
			editor.putString("settings1", edit01.getText().toString());
			editor.commit();
		} else if(v == btnGet) {
			SharedPreferences prefer = getSharedPreferences("TestPreference", MODE_PRIVATE);
			edit01.setText(prefer.getString("settings1",  ""));
		}

	}

}
