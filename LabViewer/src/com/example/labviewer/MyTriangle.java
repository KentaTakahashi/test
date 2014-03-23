package com.example.labviewer;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MyTriangle implements  MyDrawObject{

  private FloatBuffer mVertexBuffer;
  private IntBuffer mColorBuffer;
  private int mAlpha;
  private float mR, mG, mB;

  public MyTriangle(float x, float y, float z, float size, float r, float g, float b, int alpha){

	  this(x, y, z, size);
	  mR = r;
	  mG = g;
	  mB = b;
	  mAlpha = alpha;
	  initColor(mR, mG, mB);

  }

  private void initColor(float r, float g, float b) {

	  int one = 0x10000;

	  //明るさを2段階に振り分け、グラデーションをつける
	  int h1_r = (int)(one / 100 * Math.min(100, r + 10));
	  int h1_g = (int)(one / 100 * Math.min(100, g + 10));
	  int h1_b = (int)(one / 100 * Math.min(100, b + 10));
	  int h2_r = (int)(one / 100 * Math.min(100, r - 10));
	  int h2_g = (int)(one / 100 * Math.min(100, g - 10));
	  int h2_b = (int)(one / 100 * Math.min(100, b - 10));

	  int colors[] = {
			  // Front
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),

			  // Left_Back
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),

			  // Right_Back
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),

			  // Bottom
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),
		};
		ByteBuffer vbb2 = ByteBuffer.allocateDirect(colors.length * 4);
		vbb2.order(ByteOrder.nativeOrder());
		mColorBuffer = vbb2.asIntBuffer();
	  	mColorBuffer.clear();
		mColorBuffer.put(colors);
		mColorBuffer.position(0);
}

public MyTriangle(float x, float y, float z, float size){

	float vertex_1[] = {x + size, y + size, z + size};
	float vertex_2[] = {x + size, y - size, z - size};
	float vertex_3[] = {x - size, y + size, z - size};
	float vertex_4[] = {x - size, y - size, z + size};

	  float vertices[] = {
			  // Front
			  vertex_1[0], vertex_1[1], vertex_1[2],
			  vertex_2[0], vertex_2[1], vertex_2[2],
			  vertex_3[0], vertex_3[1], vertex_3[2],

		      // Left_Back
			  vertex_1[0], vertex_1[1], vertex_1[2],
			  vertex_2[0], vertex_2[1], vertex_2[2],
			  vertex_4[0], vertex_4[1], vertex_4[2],

		      // Right_Back
			  vertex_1[0], vertex_1[1], vertex_1[2],
			  vertex_3[0], vertex_3[1], vertex_3[2],
			  vertex_4[0], vertex_4[1], vertex_4[2],

		      // Bottom
			  vertex_2[0], vertex_2[1], vertex_2[2],
			  vertex_3[0], vertex_3[1], vertex_3[2],
			  vertex_4[0], vertex_4[1], vertex_4[2],
    };

    ByteBuffer vbb =
      ByteBuffer.allocateDirect(vertices.length * 4);
    vbb.order(ByteOrder.nativeOrder());
    mVertexBuffer = vbb.asFloatBuffer();
    mVertexBuffer.put(vertices);
    mVertexBuffer.position(0);


  }

  public void draw(GL10 gl){

    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

	gl.glEnableClientState(GL10.GL_COLOR_ARRAY );
	gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);

	// Front
    gl.glNormal3f(0, 0, 1.0f);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);

    // Left_Back
    gl.glNormal3f(0, 0, -1.0f);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 3, 3);

    // Right_Back
    gl.glNormal3f(-1.0f, 0, 0);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 6, 3);

    // Bottom
    gl.glNormal3f(1.0f, 0, 0);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 9, 3);
  }

  public void setAlpha(int alpha){
	  //Log.v("setAlpha called", "setAlpha1 called at MyCube");
	  mAlpha = alpha;
	  initColor(mR, mG, mB);
  }
}
