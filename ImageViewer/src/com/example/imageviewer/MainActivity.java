package com.example.imageviewer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private float preTouchX;
	private float preTouchY;
	private float currentDeg;
	private Bitmap currentBitmap;
	private Bitmap srcBitmap[] = new Bitmap[3];
	private int srcBitmapIndex = 0;
	private ImageView imgView;

	private List<String> dirList = new ArrayList<String>();      // ディレクトリ格納用
	private List<String> imgList = new ArrayList<String>();   // 画像PATH格納用

	private int debugCount = 0;//debug code

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//ビューオブジェクト取得
		imgView = (ImageView) findViewById(R.id.imageView1);

		//表示画像取得
		Resources r0 = getResources();
		srcBitmap[0] = BitmapFactory.decodeResource(r0, R.drawable.num01);
		Resources r1 = getResources();
		srcBitmap[1] = BitmapFactory.decodeResource(r1, R.drawable.num02);
		Resources r2 = getResources();
		srcBitmap[2] = BitmapFactory.decodeResource(r2, R.drawable.num03);

		// SDカードのFileを取得
		File file = Environment.getExternalStorageDirectory();
		dirList.add(file.getPath());

		// SDカード内のファイルを検索。
		int m = 0;
		int n = 0;
		while(dirList.size() > m){
		    File subDir = new File(dirList.get(m));
		    String subFileName[] = subDir.list();
		    n = 0;
		    while(subFileName.length > n){
		        File subFile = new File(subDir.getPath() + "/" + subFileName[n]);
		        if(subFile.isDirectory()){
		            dirList.add(subDir.getPath() + "/" + subFileName[n]);
		        }else if(subFile.getName().endsWith("jpg") || subFile.getName().endsWith("JPG")){
		            imgList.add(subDir.getPath() + "/" + subFileName[n]);
		        }
		        n++;
		    }
		    m++;
		}
		//File f = new File(imgList.get(0));
		//BitmapFactory.Options bmpOp = new BitmapFactory.Options();// オプション設定用のオブジェクト
		//bmpOp.inJustDecodeBounds = true;// 実際の画像本体は読まずにサイズ情報のみ取得するフラグをセット
		//bmpOp.inSampleSize = 10;
		//BitmapFactory.decodeFile(f.getPath(), bmpOp);
		//currentBitmap = BitmapFactory.decodeFile(f.getPath(), bmpOp);

		currentBitmap = srcBitmap[0];
		//imgView.setImageBitmap(currentBitmap);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		//return super.onTouchEvent(event);

		//ref http://techbooster.jpn.org/andriod/application/715/
		Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());

	    switch (event.getAction()) {
	    case MotionEvent.ACTION_DOWN:
	        Log.d("TouchEvent", "getAction()" + "ACTION_DOWN");
	        preTouchX = event.getX();
	        preTouchY = event.getY();
	        break;

	    case MotionEvent.ACTION_UP:
	    	Log.d("TouchEvent", "getAction()" + "ACTION_UP");
		      //タッチ時間が0.1秒(1ms*100)以下なら次の画面へ遷移
	    	long touchTime =  event.getEventTime() -event.getDownTime();
	        Log.d("nextViewEvent", "now" + touchTime);
	        if(touchTime < 1 * 100){
	        	nextView();
	        }
	        //break;

	    case MotionEvent.ACTION_MOVE:
	    	debugCount++;
	    	//if((debugCount % 10) != 0)break;


	        Log.d("TouchEvent", "getAction()" + "ACTION_MOVE");
	        float x = event.getX();
	        float y = event.getY();
	        float imgViewCenterX = imgView.getWidth() / 2;//imgViewの中心X座標を設定、レイアウト表示後でないと、うまく取得できなかった
	        float imgViewCenterY = imgView.getHeight() / 2;//imgViewの中心Y座標を設定
	        float deg = calcRotateDeg(x, y, preTouchX, preTouchY, imgViewCenterX, imgViewCenterY);
	        currentDeg += deg;

	        //ref http://ankohouse.sblo.jp/article/56905045.html
			Matrix mat = new Matrix();//画像に対しての行列操作可能なクラス、createBitmap時にパラメータとして与える
			mat.postRotate(currentDeg);//deg度の回転行列に変換

			int sw = currentBitmap.getWidth();
			int sh = currentBitmap.getHeight();
			Bitmap rotatedBitmap = Bitmap.createBitmap(currentBitmap, 0, 0, sw, sh, mat, true);

			//imgView.setImageBitmap(srcBitmap);
			imgView.setImageBitmap(rotatedBitmap);

			preTouchX = event.getX();
	        preTouchY = event.getY();
	        break;

	    case MotionEvent.ACTION_CANCEL:
	        Log.d("TouchEvent", "getAction()" + "ACTION_CANCEL");
	        break;
	    }
	    return true;
	}

	private void nextView() {
		// TODO 自動生成されたメソッド・スタブ
		//テスト用に適当に実装
		srcBitmapIndex++;
		srcBitmapIndex %= 3;
		currentBitmap = srcBitmap[srcBitmapIndex];
	}


	private float calcRotateDeg(float x, float y, float preX, float preY, float centerX, float centerY) {
		// TODO 自動生成されたメソッド・スタブ
		double deg = Math.atan2(y - centerY, x - centerX) * (180 / Math.PI);
		double preDeg = Math.atan2(preY - centerY, preX - centerX) * (180 / Math.PI);

		Log.d("DegEvent", "now" + x + ", " + y);
		Log.d("DegEvent", "pre" + preX + ", " + preY);
		Log.d("DegEvent", "center" + centerX + ", " + centerY);
		Log.d("DegEvent", "deg" + (deg - preDeg));

		return (float) (deg - preDeg);
		//return 1;//debug code
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
