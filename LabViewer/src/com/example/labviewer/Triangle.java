package com.example.labviewer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle {

private IntBuffer   mVertexBuffer;
private IntBuffer   mColorBuffer;
private ByteBuffer  mIndexBuffer;

public Triangle(int p , int size){
int one = 0x10000;
//座標リスト
int vertices[] = {
p - size , p        , p + size,  // 左下
p + size , p        , p + size,  // 右下
p        , p + size , p       ,  // 上
};

//頂点色
int colors[] = {
		one ,   one,    1000,  one,
0 ,    255,    0,  one,
0 ,    90,    0,  one,
};

//頂点の描画順インデックス
byte indices[] = {
0, 1, 2,
};

//バイトバッファにそれぞれのリストを登録
ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
vbb.order(ByteOrder.nativeOrder());
mVertexBuffer = vbb.asIntBuffer();
mVertexBuffer.put(vertices);
mVertexBuffer.position(0);

ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
cbb.order(ByteOrder.nativeOrder());
mColorBuffer = cbb.asIntBuffer();
mColorBuffer.put(colors);
mColorBuffer.position(0);

mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
mIndexBuffer.put(indices);
mIndexBuffer.position(0);
}

//描画
public void draw(GL10 gl){
	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	gl.glEnableClientState(GL10.GL_COLOR_ARRAY );
gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
gl.glDrawElements(GL10.GL_TRIANGLES, 3, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
}
}