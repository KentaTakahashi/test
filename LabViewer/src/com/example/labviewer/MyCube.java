package com.example.labviewer;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MyCube implements  MyDrawObject{

  private FloatBuffer mVertexBuffer;
  private IntBuffer mColorBuffer;
  private int mAlpha;
  private float mR, mG, mB;

  public MyCube(float x, float y, float z, float size, float r, float g, float b, int alpha){

	  this(x, y, z, size);
	  mR = r;
	  mG = g;
	  mB = b;
	  mAlpha = alpha;
	  initColor(mR, mG, mB);

  }

  private void initColor(float r, float g, float b) {

	  int one = 0x10000;

	  //明るさを4段階に振り分け、グラデーションをつける
	  int h1_r = (int)(one / 100 * Math.min(100, r + 10));
	  int h1_g = (int)(one / 100 * Math.min(100, g + 10));
	  int h1_b = (int)(one / 100 * Math.min(100, b + 10));
	  int h2_r = (int)(one / 100 * Math.min(100, r + 2));
	  int h2_g = (int)(one / 100 * Math.min(100, g + 2));
	  int h2_b = (int)(one / 100 * Math.min(100, b + 2));
	  int h3_r = (int)(one / 100 * Math.max(0, r - 2));
	  int h3_g = (int)(one / 100 * Math.max(0, g - 2));
	  int h3_b = (int)(one / 100 * Math.max(0, b - 2));
	  int h4_r = (int)(one / 100 * Math.max(0, r - 10));
	  int h4_g = (int)(one / 100 * Math.max(0, g - 10));
	  int h4_b = (int)(one / 100 * Math.max(0, b - 10));

	  int colors[] = {
			  // Front
			  h4_r,		h4_g,		h4_b,		(one / 100 * mAlpha),
			  h3_r,		h3_g,		h3_b,		(one / 100 * mAlpha),
			  h3_r,		h3_g,		h3_b,		(one / 100 * mAlpha),
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),

			  // Back
			  h3_r,		h3_g,		h3_b,		(one / 100 * mAlpha),
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),

			  // Left
			  h4_r,		h4_g,		h4_b,		(one / 100 * mAlpha),
			  h3_r,		h3_g,		h3_b,		(one / 100 * mAlpha),
			  h3_r,		h3_g,		h3_b,		(one / 100 * mAlpha),
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),

			  // Right
			  h3_r,		h3_g,		h3_b,		(one / 100 * mAlpha),
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),

			  // Top
			  h3_r,		h3_g,		h3_b,		(one / 100 * mAlpha),
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
			  h1_r,		h1_g,		h1_b,		(one / 100 * mAlpha),

			  // Bottom
			  h4_r,		h4_g,		h4_b,		(one / 100 * mAlpha),
			  h3_r,		h3_g,		h3_b,		(one / 100 * mAlpha),
			  h3_r,		h3_g,		h3_b,		(one / 100 * mAlpha),
			  h2_r,		h2_g,		h2_b,		(one / 100 * mAlpha),
		};
		ByteBuffer vbb2 = ByteBuffer.allocateDirect(colors.length * 4);
		vbb2.order(ByteOrder.nativeOrder());
		mColorBuffer = vbb2.asIntBuffer();
	  	mColorBuffer.clear();
		mColorBuffer.put(colors);
		mColorBuffer.position(0);
}

public MyCube(float x, float y, float z, float size){

	  float vertices[] = {
			  // Front
		      x - size, y - size, z + size,
		      x + size, y - size, z + size,
		      x - size, y + size, z + size,
		      x + size, y + size, z + size,

		      // Back
		      x - size, y - size, z - size,
		      x + size, y - size, z - size,
		      x - size, y + size, z - size,
		      x + size, y + size, z - size,

		      // Left
		      x - size, y - size, z + size,
		      x - size, y - size, z - size,
		      x - size, y + size, z + size,
		      x - size, y + size, z - size,

		      // Right
		      x + size, y - size, z + size,
		      x + size, y - size, z - size,
		      x + size, y + size, z + size,
		      x + size, y + size, z - size,

		      // Top
		      x - size, y + size, z + size,
		      x + size, y + size, z + size,
		      x - size, y + size, z - size,
		      x + size, y + size, z - size,

		      // Bottom
		      x - size, y - size, z + size,
		      x + size, y - size, z + size,
		      x - size, y - size, z - size,
		      x + size, y - size, z - size
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
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

    // Back
    gl.glNormal3f(0, 0, -1.0f);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

    // Left
    gl.glNormal3f(-1.0f, 0, 0);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);

    // Right
    gl.glNormal3f(1.0f, 0, 0);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

    // Top
    gl.glNormal3f(0, 1.0f, 0);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);

    // Bottom
    gl.glNormal3f(0, -1.0f, 0);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);

  }

  public void setAlpha(int alpha){
	  //Log.v("setAlpha called", "setAlpha1 called at MyCube");
	  mAlpha = alpha;
	  initColor(mR, mG, mB);
  }
}
