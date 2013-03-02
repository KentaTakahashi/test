package com.example.labviewer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	MyGLView myGLView;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    myGLView = new MyGLView(this);
	    setContentView(myGLView);
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
