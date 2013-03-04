package com.example.labviewer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MyJpcColor {

	private final FloatBuffer mVertexBuffer;
	private final IntBuffer mColorBuffer;

	public MyJpcColor(){

		float vertices[] = {

				//a→X, L→Y, b→Z の対応となるので要注意!
				//ref doc/JAPAN COLOR.pdf

				// ハイライト側の6角推
				0.005f, 0.93f, 	0.004f,	// W 白のみJPC2007に記載が無かったため決めうち
				0.69f,	0.46f,	0.42f,	// R
				-0.06f,	0.88f,	0.92f,	// Y
				-0.71f,	0.50f,	0.25f,	// G
				-0.39f,	0.55f,	-0.49f,	// C
				0.18f,	0.23f,	-0.47f,	// B
				0.75f,	0.46f,	-0.06f,	// M
				0.69f,	0.46f,	0.42f,	// R

				// シャドー側の6角推
				0.01f,	0.14f,	0.01f,	// Bk
				0.69f,	0.46f,	0.42f,	// R
				-0.06f,	0.88f,	0.92f,	// Y
				-0.71f,	0.50f,	0.25f,	// G
				-0.39f,	0.55f,	-0.49f,	// C
				0.18f,	0.23f,	-0.47f,	// B
				0.75f,	0.46f,	-0.06f,	// M
				0.69f,	0.46f,	0.42f,	// R
		};

		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer = vbb.asFloatBuffer();
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);

		int one = 0x10000;
		int colors[] = {

				// ハイライト側の6角推
				one,	one,	one,	one,	// W
				one,	0,		0,		one,	// R
				one,	one,	0,		one,	// Y
				0,		one,	0,		one,	// G
				0,		one,	one,	one,	// C
				0,		0,		one,	one,	// B
				one,	0,		one,	one,	// M
				one,	0,		0,		one,	// R

				// シャドー側の6角推
				0,		0,		0,		one,	// Bk
				one,	0,		0,		one,	// R
				one,	one,	0,		one,	// Y
				0,		one,	0,		one,	// G
				0,		one,	one,	one,	// C
				0,		0,		one,	one,	// B
				one,	0,		one,	one,	// M
				one,	0,		0,		one,	// R
		};

		ByteBuffer vbb2 = ByteBuffer.allocateDirect(colors.length * 4);
		vbb2.order(ByteOrder.nativeOrder());
		mColorBuffer = vbb2.asIntBuffer();
		mColorBuffer.put(colors);
		mColorBuffer.position(0);
	}
	public void draw(GL10 gl){

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY );

		// Front
	    gl.glNormal3f(0, 0, 1.0f);
	    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);


		// ハイライト側の6角推
		gl.glNormal3f(0, 0, 1.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 8);//連続した三角形で扇形を描きます。http://www.komoto.org/opengl/sample01.html

		// シャドー側の6角推
		gl.glNormal3f(0, 0, 1.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 8, 8);


	}
}
