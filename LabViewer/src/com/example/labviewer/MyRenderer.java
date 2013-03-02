package com.example.labviewer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRenderer implements Renderer {

	MyCube myCube = new MyCube();
	MyJpcColor myJpcColor  = new MyJpcColor();

	private int size = 0x10000;
	Triangle mTriangle  = new Triangle(0, size);

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO 自動生成されたメソッド・スタブ
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -3f);

		//ライティングを有効化
	    //gl.glEnable(GL10.GL_LIGHTING);
	    //gl.glEnable(GL10.GL_LIGHT0);

		//Frameを回転
		gl.glRotatef(30f, 1, 1, 0);
	    //gl.glRotatef(30f, 1, 0, 0);

		//myCube.draw(gl);
		myJpcColor.draw(gl);
		//mTriangle.draw(gl);

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO 自動生成されたメソッド・スタブ

		//デバイスのサイズや縦横の向きが変わったとき,それに合わせて、フラスタムとビューポートを表示が歪まないように設定
		gl.glViewport(0, 0, width, height);

	    gl.glMatrixMode(GL10.GL_PROJECTION);
	    gl.glLoadIdentity();
	    GLU.gluPerspective(gl, 45f,(float) width / height, 1f, 50f);

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO 自動生成されたメソッド・スタブ
		//デプステストを有効。3D描画時に利用。デプスバッファが浅いものが上に表示される。
		gl.glEnable(GL10.GL_DEPTH_TEST);
	    gl.glDepthFunc(GL10.GL_LEQUAL);
	    /**
	     * デプスバッファの評価方は glDepthFunc を使用します。
	     * 初期値は GL_LESS であり、
		 *新たな値がバッファの値より小さければ表示される。
		 *すなわち近いものほど上に描画される設定となる。

		 *GL_NEVER:全て採用されない
		 *GL_LESS:バッファより値が小さければ採用
		 *GL_LEQUAL:バッファの値以下であれば採用
		 *GL_EQUAL:バッファの値と同じであれば採用
		 *GL_GREATER:バッファより値が大きければ採用
		 *GL_NOTEQUAL:バッファの値と同じでなければ採用
		 *GL_GEQUAL:バッファの値以上であれば採用
		 *GL_ALWAYS:常に採用される
	     */

	    /**
	    *  DITHERをオフにします。
	    *  DITHERとは量子化の誤差を最小にするべく
	    *  サンプルデータに意図的に追加される誤った信号・データのこと。
	    */
	    gl.glDisable(GL10.GL_DITHER);
	    // 背景色
	    gl.glClearColor(1,1,1,1);
	    //スムースシェーディング：平面のポリゴンを曲面に見せかける処理。
	    gl.glShadeModel(GL10.GL_SMOOTH);


	}

}
