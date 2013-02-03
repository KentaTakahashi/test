package com.example.implicitintents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnHttp = (Button) this.findViewById(R.id.http);
		btnHttp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("http://www.google.co.jp");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});

		Button BtnMail = (Button) this.findViewById(R.id.mail);
		BtnMail.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("mailto:hoge@hoge.hoge");
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
				startActivity(intent);
			}
		});

		Button BtnAdrress = (Button) this.findViewById(R.id.address);
		BtnAdrress.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("content://contacts/people/1");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});

		Button btnGps = (Button) this.findViewById(R.id.gps);
		btnGps.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Uri uri = Uri.parse("geo:latitude,longitude");
				Uri uri = Uri.parse("geo:0,0?q=Yokohama");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});

		Button btnDial = (Button) this.findViewById(R.id.dial);
		btnDial.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:012301230123");
				Intent intent = new Intent(Intent.ACTION_DIAL, uri);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
