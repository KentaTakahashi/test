package SandGlass;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    public double X;
    public double Y;
    public double v_X = 0;
    public double v_Y = 0;
    public double radius;//半径
    public double m;//重量

    private Color color;

    static double gravity;//重力係数
    static double e;		//ボール間での反発係数
    static double e2;		//砂時計/ボール間での反発係数
    static double e3;		//底面/ボール間での反発係数

    Ball(double radius) {
        this.radius = radius;
        this.m = radius * radius;//取りあえず、質量は面積に比例

        X = Math.random() * (DrawPanel.panelWidth - 2 * radius);
        //Y = Math.random() * (DrawPanel.panelHeight - 2 * radius);
        Y = 0;

        color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
    }
	public boolean hit(Ball target) {
		return(X - target.X) * (X - target.X) + (Y - target.Y) * (Y - target.Y)
				<= (radius + target.radius) * (radius + target.radius);
	}

	public void refrect(Ball target) {

		//参考URL　http://hakuhin.jp/as/collide.html

		//めり込み補正、重複した座標を補正する
		double vx = (X - target.X);
		double vy = (Y - target.Y);
		double len = Math.sqrt(vx * vx + vy * vy);
		double distance = (radius + target.radius) - len;

		if(len > 0)	len = 1 / len;
		vx *= len;
		vy *= len;

		distance /= 2.0;
		X += vx * distance;
		Y += vy * distance;
		target.X -= vx * distance;
		target.Y -= vy * distance;

		//速度ベクトルを重心方向と垂直な方向に分離する
		double t;
		vx = (target.X - X);
		vy = (target.Y - Y);

		t = -(vx * v_X + vy * v_Y) / (vx * vx + vy * vy);
		double arx = v_X + vx * t;
		double ary = v_Y + vy * t;

		t = -(-vy * v_X + vx * v_Y) / (vy * vy + vx * vx);
		double amx = v_X - vy * t;
		double amy = v_Y + vx * t;

		t = -(vx * target.v_X + vy * target.v_Y) / (vx * vx + vy * vy);
		double brx = target.v_X + vx * t;
		double bry = target.v_Y + vy * t;

		t = -(-vy * target.v_X + vx * target.v_Y) / (vy * vy + vx * vx);
		double bmx = target.v_X - vy * t;
		double bmy = target.v_Y + vx * t;

		//x 方向と y 方向それぞれの衝突後の速度を計算する
		double av_X = (m * amx + target.m * bmx + bmx * e * target.m - amx * e * target.m) / (m + target.m);
		double bv_X = - e * (bmx - amx) + av_X;
		double av_Y = (m * amy + target.m * bmy + bmy * e * target.m - amy * e * target.m) / (m + target.m);
		double bv_Y = - e * (bmy - amy) + av_Y;

		//回転運動を発生させるベクトルを加算して衝突後の速度を計算
		v_X = av_X + arx;
		v_Y = av_Y + ary;
		target.v_X = bv_X + brx;
		target.v_Y = bv_Y + bry;
	}

	public void update() {
		double width  = DrawPanel.panelWidth;
		double height = DrawPanel.panelHeight;
		double wheel  = DrawPanel.ballRadius;

		//重力加算
		v_Y += gravity;

        // 壁に衝突すれば反射
        if (((X < 0) && (v_X < 0)) || (X >= (width  - 2 * radius) &&  (v_X > 0)))
            v_X = -v_X * e2;

        //蓋より上の座標になったら0にリセット
        if (Y < 0) {
            Y = 0;
            //速度ベクトルが上方だったら反発係数をかけて下方に変更
            if(v_Y < 0) v_Y = -v_Y * e2;
        }

		//砂時計左上
        if(X <= (width - wheel)/2 && Y <= height/2 && X < Y) {
        	X = Y;
        	if(v_X <  v_Y) {
            	v_X = v_Y * e2;
            	v_Y = v_X * e2;
        	}
        }
        //砂時計右上
        if(X >= (width + wheel)/2 && Y <= height/2 && width - X < Y) {
        	X = width - Y;
        	if(v_X > -v_Y) {
            	v_X = -v_Y * e2;
            	v_Y = -v_X * e2;
        	}
        }
        //砂時計左下
        if(X <= (width - wheel)/2 && Y >  height/2 && X < height - Y) {
        	X = height - Y;
        	if((v_X < -v_Y)) {
            	v_X = -v_Y * e2;
            	v_Y = -v_X * e2;
        	}
        }
        //砂時計右下
        if(X >= (width + wheel)/2 && Y >  height/2 && width - X <  height - Y) {
        	X = width - height + Y;
        	if(v_X >  v_Y) {
            	v_X = v_Y * e2;
            	v_Y = v_X * e2;
        	}
        }

        //底より下だった場合、座標を底にリセット
        if (Y >= (height - 2 * radius)) {
        	Y = height - 2*radius;
        	//速度ベクトルが下方だったら反発係数をかけて上方に変更
        	if(v_Y > 0) v_Y = -v_Y * e3;
        }

		//座標更新
        X += v_X;
        Y += v_Y;
	}


    void draw(Graphics g) {

        g.setColor(color);
        g.fillOval((int)(X), (int)(Y), (int)(2*radius), (int)(2*radius));
    }

}
