package com.example.labviewer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class MyGLView extends GLSurfaceView {

	MyRenderer myRenderer;
	private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;
    private MainActivity mainActivity;

	public MyGLView(Context context) {
		super(context);
		myRenderer = new MyRenderer(this);
	    setRenderer(myRenderer);

	    mainActivity = (MainActivity) context;

	    //http://10ishi.blogspot.jp/2012/04/opengl-es-3.html
	    //タッチ操作による回転を処理するためのクラスのインスタンスを追加する。
        mGestureDetector = new GestureDetector(context, new MyGestureListener());

        // タッチ操作によるカメラ位置の変更ためのクラスのインスタンスを追加する。
        mScaleGestureDetector = new ScaleGestureDetector(context, new MyScaleGestureListener());
	}
	/**
	 * XMLより呼び出す際のコンストラクタ
	 * @param context, attrs
	 */
	public MyGLView(Context context, AttributeSet attrs){
		this(context);
	}

	@Override
    public boolean onTouchEvent(MotionEvent e) {
		// タッチ・イベントをGestureの処理クラスのインスタンスへ送る
		mGestureDetector.onTouchEvent(e);

		// タッチ・イベントをScallGestureの処理クラスのインスタンスへ送る
		mScaleGestureDetector.onTouchEvent(e);
		return true;
    }

	public class MyGestureListener implements OnGestureListener {

		@Override
		public boolean onDown(MotionEvent paramMotionEvent) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent paramMotionEvent) {

		}

		@Override
		public boolean onSingleTapUp(MotionEvent paramMotionEvent) {
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1,
				float paramFloat2) {
			// X方向の移動距離で回転させるため、回転角度に加算する。
	        myRenderer.addRotateY(paramFloat1);
	        return true;
		}

		@Override
		public void onLongPress(MotionEvent paramMotionEvent) {

		}

		@Override
		public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1,
				float paramFloat2) {
			return false;
		}

	}

    public class MyScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

		@Override
		public boolean onScale(ScaleGestureDetector detector) {

		    // 指の間隔の割合でカメラの遠近を変更する
		    myRenderer.changeCameraPositionByZ(detector.getScaleFactor());
		    return true;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
		    return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector detector) {
		    // TODO Auto-generated method stub

		}
	}
	public int getAlpha1() {
		return mainActivity.getAlpha1();
	}
	public int getAlpha2() {
		return mainActivity.getAlpha2();
	}
}
