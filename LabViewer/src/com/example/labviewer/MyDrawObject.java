package com.example.labviewer;

import javax.microedition.khronos.opengles.GL10;


abstract class MyDrawObject {

	protected float centerX;
	protected float centerY;
	protected float centerZ;
	protected float drowSize;

	//コンストラクタ
	public MyDrawObject(float x, float y, float z, float size){
		centerX = x;
		centerY = y;
		centerZ = z;
		drowSize = size;
	}

	//描画する抽象メソッド
	abstract void draw(GL10 gl);
}
