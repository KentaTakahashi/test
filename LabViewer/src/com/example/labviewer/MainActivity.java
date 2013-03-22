package com.example.labviewer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	// LayoutParamsにセットするパラメータを準備
    private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

	MyGLView myGLView;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    myGLView = new MyGLView(this);
	    //setContentView(myGLView);

	    //FrameLayoutを準備
        FrameLayout fl = new FrameLayout(this);
        setContentView(fl);

        //FrameLayoutにSurfaceViewをセットする
        fl.addView(myGLView,new ViewGroup.LayoutParams(WC, WC));

        //SurfaceViewと重ねるTextViewを準備
        TextView tv = new TextView(this);
        tv.setText("SurfaceViewとTextViewを重ねる");
        tv.setHeight(30);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.WHITE);

        //FrameLayoutにTextViewをセットする
        fl.addView(tv, new ViewGroup.LayoutParams(FP, WC));
	  }

	  @Override
	  protected void onResume(){
	    super.onResume();
	    myGLView.onResume();
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
