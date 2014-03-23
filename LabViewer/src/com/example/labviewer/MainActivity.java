package com.example.labviewer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	MyGLView myGLView;
	private int mAlpha1  = 100;
	private int mAlpha2  = 100;
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    myGLView = new MyGLView(this);

        setContentView(R.layout.activity_main);

        SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar1.setMax(100);
        seekBar1.setProgress(100);
        seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            // トラッキング開始時に呼び出されます
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.v("onStartTrackingTouch()", String.valueOf(seekBar.getProgress()));
            }
            // トラッキング中に呼び出されます
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                Log.v("onProgressChanged() setAlpha", String.valueOf(progress) + ", " + String.valueOf(fromTouch));
                mAlpha1 = seekBar.getProgress();
            }
            // トラッキング終了時に呼び出されます
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.v("onStopTrackingTouch()", String.valueOf(seekBar.getProgress()));
                mAlpha1 = seekBar.getProgress();
            }
        });
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar2.setMax(100);
        seekBar2.setProgress(100);
        seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            // トラッキング開始時に呼び出されます
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.v("onStartTrackingTouch()", String.valueOf(seekBar.getProgress()));
            }
            // トラッキング中に呼び出されます
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                Log.v("onProgressChanged() setAlpha", String.valueOf(progress) + ", " + String.valueOf(fromTouch));
                mAlpha2 = seekBar.getProgress();
            }
            // トラッキング終了時に呼び出されます
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.v("onStopTrackingTouch()", String.valueOf(seekBar.getProgress()));
                mAlpha2 = seekBar.getProgress();
            }
        });
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
	public int getAlpha1() {
		return mAlpha1;
	}
	public int getAlpha2() {
		return mAlpha2;
	}
}
