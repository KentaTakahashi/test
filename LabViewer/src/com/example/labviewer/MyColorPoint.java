package com.example.labviewer;

import javax.microedition.khronos.opengles.GL10;

public class MyColorPoint implements MyDrawObject{

	//座標系
	public enum ColorCoordinate{
		RGB, XYZ, Lab
	}
	//色空間
	public enum ColorSpace{
		sRGB, Adobe_RGB, CIE_RGB
	}
	//描画タイプ
	public enum DrawType{
		Cube, Triangle, Sphere
	}

	private MyDrawObject mObject;

	float mCordR, mCordG, mCordB;//RGB座標系上でのMyColorPointの座標
	float mCordX, mCordY, mCordZ;//XYZ座標系上でのMyColorPointの座標
	float mCordL, mCorda, mCordb;//L*a*b*座標系上でのMyColorPointの座標

	float mCordXn, mCordYn, mCordZn;//基準となっているホワイトポイントの CIE XYZ での三刺激値である（添え字の n は "normalized" の意）。

	private ColorSpace mColorSpace;
	private DrawType mDrawType;

	//コンストラクタ
	public MyColorPoint(ColorCoordinate colorcoordinate, float i, float j, float k, int alpha, ColorSpace colorspace){

		mColorSpace = colorspace;

		initWhitePoint(mColorSpace);//座標系変換用のXYZ空間のホワイトポイントを指定の色空間ごとに設定

		//引数をもとに、すべての座標系の座標を求め、メンバ変数に設定する
		if(colorcoordinate == ColorCoordinate.RGB){
			initRGBmode(i, j, k);
		}else if(colorcoordinate == ColorCoordinate.XYZ){
			initXYZmode(i, j, k);
		}else if(colorcoordinate == ColorCoordinate.Lab){
			initLabmode(i, j, k);
		}
		//描画タイプをデフォルトで正六面体とする
		setDrawType(DrawType.Cube);
		initDrawObject(alpha);
	}

	private void initDrawObject(int alpha) {
		// TODO 自動生成されたメソッド・スタブ
		if(mDrawType == DrawType.Cube){
			//mObject = new MyCube(mCordR, mCordG, mCordB, 0.02f, mCordR * 100, mCordG * 100, mCordB * 100);
			//mObject = new MyCube(mCordX, mCordY, mCordZ, 0.02f, mCordR * 100, mCordG * 100, mCordB * 100);
			mObject = new MyCube(mCorda/ 100, mCordL / 100, - mCordb/ 100, 0.02f, mCordR * 100, mCordG * 100, mCordB * 100, alpha);
			//mObject = new MyCube(0, 0, 0, 0.5f, 50, 50, 50);//debug
		}
	}

	//コンストラクタのオーバーライド、colorspaceはデフォルト値はsRGB
	public MyColorPoint(ColorCoordinate colorcoordinate, float i, float j, float k){
		this(colorcoordinate, i, j, k, 100, ColorSpace.sRGB);
	}

	//コンストラクタ内で呼ばれる初期化関数
	private void initRGBmode(float r, float g, float b) {
		mCordR = r;
		mCordG = g;
		mCordB = b;

		convRGBtoXYZ(mCordR, mCordG, mCordB);
		convXYZtoLab(mCordX, mCordY, mCordZ);
	}
	private void initXYZmode(float x, float y, float z) {
		mCordX = x;
		mCordY = y;
		mCordZ = z;

		invXYZtoRGB(mCordX, mCordY, mCordZ);
		convXYZtoLab(mCordX, mCordY, mCordZ);
	}
	private void initLabmode(float l, float a, float b) {
		mCordL = l;
		mCorda = a;
		mCordb = b;

		invLabtoXYZ(mCordL, mCorda, mCordb);
		invXYZtoRGB(mCordX, mCordY, mCordZ);
	}

	/**
	 * 座標系変換式は以下を参考とした
	 * http://www.enjoy.ne.jp/~k-ichikawa/CIEXYZ_RGB.html
	 * http://ja.wikipedia.org/wiki/L*a*b*%E8%A1%A8%E8%89%B2%E7%B3%BB#L.2Aa.2Ab.2A_.E5.BA.A7.E6.A8.99.E3.81.AE.E7.AF.84.E5.9B.B2
	 */
	//想定する光源によりパラメータが異なるためここで初期化する
	private void initWhitePoint(ColorSpace colorspace) {

		if(mColorSpace == ColorSpace.sRGB || mColorSpace == ColorSpace.Adobe_RGB){//D65 自然光を模擬 (昼光色, 色温度約 6500 K）
			mCordXn = 0.95046f;
			mCordYn = 1.00000f;
			mCordZn = 1.08906f;
		}else if(mColorSpace == ColorSpace.CIE_RGB){// 一様なエネルギー分布 (色温度 5454 K)
			mCordXn = 1.00000f;
			mCordYn = 1.00000f;
			mCordZn = 1.00000f;
		}
	}

	private void convRGBtoXYZ(float r, float g, float b) {
		if(mColorSpace == ColorSpace.sRGB){//sRGB(D65)
			mCordX = (0.4124f) * r + (0.3576f * g) + (0.1805f * b);
			mCordY = (0.2126f) * r + (0.7152f * g) + (0.0722f * b);
			mCordZ = (0.0193f) * r + (0.1192f * g) + (0.9505f * b);
		}else if(mColorSpace == ColorSpace.Adobe_RGB){
			mCordX = (0.5778f) * r + (0.1825f * g) + (0.1902f * b);
			mCordY = (0.3070f) * r + (0.6170f * g) + (0.0761f * b);
			mCordZ = (0.0181f) * r + (0.0695f * g) + (1.0015f * b);
		}else if(mColorSpace == ColorSpace.CIE_RGB){
			mCordX = (0.4898f) * r + (0.3101f * g) + (0.2001f * b);
			mCordY = (0.1769f) * r + (0.8124f * g) + (0.0107f * b);
			mCordZ = (0.0000f) * r + (0.0100f * g) + (0.9903f * b);
		}
	}

	private void invXYZtoRGB(float x, float y, float z) {

		if(mColorSpace == ColorSpace.sRGB){//sRGB(D65)
			mCordR = (3.2410f) * x - (1.5374f * y) - (0.4986f * z);
			mCordG =-(0.9692f) * x + (1.8760f * y) + (0.0416f * z);
			mCordB = (0.0556f) * x - (0.2040f * y) + (1.5070f * z);
		}else if(mColorSpace == ColorSpace.Adobe_RGB){
			mCordR = (2.0416f) * x - (0.5650f * y) - (0.3447f * z);
			mCordG =-(1.0199f) * x + (1.9171f * y) + (0.0481f * z);
			mCordB = (0.0340f) * x - (0.1229f * y) + (1.0014f * z);
		}else if(mColorSpace == ColorSpace.CIE_RGB){
			mCordR = (2.3655f) * x - (0.8971f * y) - (0.4683f * z);
			mCordG =-(0.5151f) * x + (1.4264f * y) + (0.0887f * z);
			mCordB = (0.0052f) * x - (0.0144f * y) + (1.0089f * z);
		}
	}

	private void convXYZtoLab(float x, float y, float z) {

		mCordL = 116f * func_convXYZtoLab(y / mCordYn) -16f;
		mCorda = 500f * (func_convXYZtoLab(x / mCordXn) - func_convXYZtoLab(y / mCordYn));
		mCordb = 200f * (func_convXYZtoLab(y / mCordYn) - func_convXYZtoLab(z / mCordZn));
	}

	//convXYZtoLab用tが0に近似する際無限大になるのを防ぐためケースわけする
	private float func_convXYZtoLab(float t) {

		if(t > Math.pow(6 / 29f, 3f)){
			return (float) Math.pow(t, 1 / 3f);
		}else{
			return (float)((1 / 3f) * Math.pow(29 / 6f, 2f) * t + (4 / 29f));
		}
	}

	private void invLabtoXYZ(float l, float a, float b) {
		float fy = (l + 16f) / 116f;
		float fx = fy + (a / 500f);
		float fz = fy - (b / 200f);

		float delta = 6 / 29f;

		if(fy > delta){
			mCordY = (float) (mCordYn * Math.pow(fy, 3));
		}else{
			mCordY = (float) ((fy - (16 / 116f)) * 3 * Math.pow(delta, 2) * mCordYn);
		}

		if(fx > delta){
			mCordY = (float) (mCordXn * Math.pow(fx, 3));
		}else{
			mCordY = (float) ((fx - (16 / 116f)) * 3 * Math.pow(delta, 2) * mCordXn);
		}

		if(fz > delta){
			mCordY = (float) (mCordZn * Math.pow(fz, 3));
		}else{
			mCordY = (float) ((fz - (16 / 116f)) * 3 * Math.pow(delta, 2) * mCordZn);
		}

	}

	public void setDrawType(DrawType Drawtype){
		mDrawType = Drawtype;
	}

	public void draw(GL10 gl){
		mObject.draw(gl);
	}

	@Override
	public void setAlpha(int alpha) {
		mObject.setAlpha(alpha);
	}

}
