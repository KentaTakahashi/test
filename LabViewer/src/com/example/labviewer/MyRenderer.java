package com.example.labviewer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRenderer implements Renderer {

	MyCube myCube = new MyCube();

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO 自動生成されたメソッド・スタブ
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);



		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -3f);

		//Frameを回転
		gl.glRotatef(30f, 0, 1, 0);
	    //gl.glRotatef(30f, 1, 0, 0);

		myCube.draw(gl);


	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO 自動生成されたメソッド・スタブ

		//デバイスのサイズや縦横の向きが変わったとき,それに合わせて、フラスタムとビューポートを表示が歪まないように設定
		gl.glViewport(0, 0, width, height);

	    gl.glMatrixMode(GL10.GL_PROJECTION);
	    gl.glLoadIdentity();
	    GLU.gluPerspective(gl, 45f,(float) width / height, 1f, 50f);

	    //ライティングを有効化
	    gl.glEnable(GL10.GL_LIGHTING);
	    gl.glEnable(GL10.GL_LIGHT0);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO 自動生成されたメソッド・スタブ
		gl.glEnable(GL10.GL_DEPTH_TEST);
	    gl.glDepthFunc(GL10.GL_LEQUAL);
	}

}
