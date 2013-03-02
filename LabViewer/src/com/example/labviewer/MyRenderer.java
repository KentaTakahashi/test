package com.example.labviewer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRenderer implements Renderer {

	private float mWidth;
    private float mHeight;
    private float zNear =1.0f;
    private float zFar = 1000.0f;

    // 移動方向のベクトル成分
    private float posX = 0.0f, posY = 0.0f, posZ = 0.0f;

    // 回転の角度(degree)
    private float rotateX = 0.0f, rotateY = 1.0f, rotateZ = 0.0f;

    //回転軸のベクターの各座標
    private float coordX = 0.0f, coordY =1.0f, coordZ = 0.0f;

    // Camera 位置座標
    private float eyeX = 0.0f, eyeY = 0.0f, eyeZ = 3.0f;

	MyCube myCube = new MyCube();
	MyJpcColor myJpcColor  = new MyJpcColor();

	private int size = 0x10000;
	Triangle mTriangle  = new Triangle(0, size);

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO 自動生成されたメソッド・スタブ
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		 // カメラの設定
		// Camera Perstective Params
		float fovY = 45.0f;
		float aspect = (float) mWidth / (float) mHeight;
		GLU.gluPerspective(gl, fovY, aspect, zNear, zFar);
		// Camera loock at Params
		float centerX = 0;
		float centerY = 0;
		float centerZ = 0;
		float upX = 0;
		float upY = 1;
		float upZ = 0;
		GLU.gluLookAt(gl, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);



		//ライティングを有効化
	    //gl.glEnable(GL10.GL_LIGHTING);
	    //gl.glEnable(GL10.GL_LIGHT0);

		//Frameを回転
		//gl.glRotatef(30f, 1, 1, 0);
	    //gl.glRotatef(30f, 1, 0, 0);
		// 回転の角度と回転軸を指定する。
        // とりあえず，Y軸で回転させる
        gl.glRotatef(rotateY, coordX, coordY, coordZ);

        //myCube.draw(gl);
		myJpcColor.draw(gl);
		//mTriangle.draw(gl);

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO 自動生成されたメソッド・スタブ

		mWidth = (float)width;
        mHeight = (float)height;

		//デバイスのサイズや縦横の向きが変わったとき,それに合わせて、フラスタムとビューポートを表示が歪まないように設定
		gl.glViewport(0, 0, width, height);

	    gl.glMatrixMode(GL10.GL_PROJECTION);
	    gl.glLoadIdentity();
	    GLU.gluPerspective(gl, 45f,(float) mWidth / mHeight, 1f, 50f);

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
	    gl.glClearColor(0,0,0,1);
	    //スムースシェーディング：平面のポリゴンを曲面に見せかける処理。
	    gl.glShadeModel(GL10.GL_SMOOTH);


	}

	public void addRotateY(float paramFloat1) {
		// TODO 自動生成されたメソッド・スタブ
		this.rotateY += paramFloat1;
	}

	public void changeCameraPositionByZ(float scaleFactor) {
		// TODO 自動生成されたメソッド・スタブ
		float z = eyeZ;
		z = z / scaleFactor;
		// リニアクリップより小さくならないようにする。
		//z = Math.max(z, zNear);
		z = Math.max(z, zNear * 2);//対象が見切れないよう修正
		// ファーアクリップより大きくならないようにする。
		z = Math.min(z, zFar);
		eyeZ = z;

	}

}
