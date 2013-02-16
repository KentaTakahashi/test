package com.example.imageviewer;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//ビューオブジェクト取得
		ImageView imgView = (ImageView) findViewById(R.id.imageView1);
		//表示画像取得
		Resources r = getResources();
        Bitmap srcBitmap = BitmapFactory.decodeResource(r, R.drawable.num01);
        imgView.setImageBitmap(srcBitmap);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
