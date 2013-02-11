package com.example.servercheck;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

	private static final String DEF_HTTP = "http://";

	private EditText edit01;
	private EditText edit02;
	private EditText edit03;
	private TextView resultText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edit01 = (EditText) findViewById(R.id.editText1);
		edit02 = (EditText) findViewById(R.id.editText2);
		edit03 = (EditText) findViewById(R.id.editText3);
		resultText = (TextView) findViewById(R.id.TextView1);

		Button btnConnect = (Button) findViewById(R.id.button1);
		btnConnect.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		String retStr = "";
		if(!(DEF_HTTP.equals(edit01.getText().toString()))){
			retStr =doGet(edit01.getText().toString());
			resultText.setText(edit01.getText().toString() + " " + retStr);
		}

	}

	public String doGet(String url) {
		// TODO 自動生成されたメソッド・スタブ
		try
		{
			DefaultHttpClient client = new DefaultHttpClient();
			String urlTest = "http://www.google.co.jp/";
			HttpGet method = new HttpGet(urlTest);

			HttpResponse response = client.execute(method);
			int status = response.getStatusLine().getStatusCode();
			return "Status:" + status;
		}
		catch(Exception e)
		{
			return "Error:" + e.getMessage();
		}
	}

}
