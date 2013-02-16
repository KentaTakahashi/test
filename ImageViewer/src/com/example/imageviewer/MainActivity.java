package com.example.imageviewer;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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

		//ref http://ankohouse.sblo.jp/article/56905045.html
		int deg = 90;//カンパス回転角度
		Matrix mat = new Matrix();//画像に対しての行列操作可能なクラス、createBitmap時にパラメータとして与える
		mat.postRotate(deg);//deg度の回転行列に変換

		int sw = srcBitmap.getWidth();
		int sh = srcBitmap.getHeight();
		Bitmap rotatedBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, sw, sh, mat, true);

		//imgView.setImageBitmap(srcBitmap);
		imgView.setImageBitmap(rotatedBitmap);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
