package com.example.labviewer;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLView extends GLSurfaceView {

	MyRenderer myRenderer;

	public MyGLView(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
		myRenderer = new MyRenderer();
	    setRenderer(myRenderer);
	}

}
