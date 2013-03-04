package com.example.photoalbum;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private float preTouchX;
	private float preTouchY;
	private float currentDeg;
	private Bitmap currentBitmap;
	private int srcBitmapIndex = 0;
	private ImageView imgView;
	private TextView textView;
	private Gallery imgGallery;

	private List<Bitmap> imgList = new ArrayList<Bitmap>();   // 画像格納用
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//ビューオブジェクト取得
		imgView = (ImageView) findViewById(R.id.imageView1);

		Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		//Uri uri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
		getImageData(uri);

		currentBitmap = imgList.get(1);
		imgView.setImageBitmap(currentBitmap);

		//ギャラリーオブジェクト取得
		imgGallery = (Gallery) findViewById(R.id.gallery1);
		//imgGallery.setSpacing(3);
		imgGallery.setAdapter(new GalleryAdpter(this));
		imgGallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO 自動生成されたメソッド・スタブ
			}
		});

		textView = (TextView) findViewById(R.id.textView1);
	}

	private void getImageData(Uri uri) {
		// TODO 自動生成されたメソッド・スタブ

		Cursor cur = this.managedQuery(uri, null, null, null, null);

		cur.moveToFirst();
		Log.d("GetImageEvent", "start");
		for(int i = 0; i < cur.getCount(); i++){
			String path = cur.getString(cur.getColumnIndexOrThrow("_data"));
			imgList.add(file2bmp(path, 360, 360));
			Log.d("GetImageEvent", "i = :" + Integer.toString(i));

			cur.moveToNext();//インクリメント
		}

	}

	private Bitmap file2bmp(String path, int maxW, int maxH) {
		// TODO 自動生成されたメソッド・スタブ
		BitmapFactory.Options opt;
		try{
			//画像サイズの取得
			opt = new BitmapFactory.Options();
			opt.inJustDecodeBounds = true;//デコードせずに、ファイルサイズを取得させるためのフラグ

			BitmapFactory.decodeFile(path, opt);
			int scaleW = opt.outWidth  / maxW + 1;
			int scaleH = opt.outHeight / maxH + 1;
			int scale  = Math.max(scaleW, scaleH);

			//画像の読み込み
			opt = new BitmapFactory.Options();
			opt.inJustDecodeBounds = false;
			opt.inSampleSize = scale;//サンプリングサイズを設定
			Bitmap bmp = BitmapFactory.decodeFile(path, opt);
			return bmp;
		}catch(Exception e){
			return null;
		}
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		//return super.onTouchEvent(event);

		long offsetY = imgGallery.getHeight() + textView.getHeight();
		if(event.getY() < offsetY)return false;

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
	    	Log.d("TouchEvent", "getAction()" + "ACTION_MOVE");
	        float x = event.getX();
	        float y = event.getY();
	        float imgViewCenterX = imgView.getWidth()  / 2;//imgViewの中心X座標を設定、レイアウト表示後でないと、うまく取得できなかった
	        float imgViewCenterY = imgView.getHeight() / 2 + offsetY;//imgViewの中心Y座標を設定
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

			preTouchX = x;
	        preTouchY = y;
	        break;

	    case MotionEvent.ACTION_CANCEL:
	        Log.d("TouchEvent", "getAction()" + "ACTION_CANCEL");
	        break;
	    }
	    return true;
	}

	private void nextView() {
		// TODO 自動生成されたメソッド・スタブ

		srcBitmapIndex++;
		srcBitmapIndex %= imgList.size();
		currentBitmap = imgList.get(srcBitmapIndex);
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
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public class GalleryAdpter extends BaseAdapter{

		private Context context;
		public GalleryAdpter(Context c){
			Log.d("convertView cheak", "init");
		    context = c;
		}
		@Override
		public int getCount() {
			// TODO 自動生成されたメソッド・スタブ
			return imgList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 自動生成されたメソッド・スタブ
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO 自動生成されたメソッド・スタブ
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自動生成されたメソッド・スタブ
			ImageView i;

			if(convertView == null){
				Log.d("convertView cheak", "convertView == null");
				i = new ImageView(context);
				i.setLayoutParams(new Gallery.LayoutParams(200, 200));
				i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

				BitmapDrawable bd = new BitmapDrawable(imgList.get(position));
				i.setImageDrawable(bd);
			}else{
				i = (ImageView) convertView;
				Log.d("convertView cheak", "convertView != null");
			}
			return i;
		}

	}
}
