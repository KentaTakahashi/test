package SandGlass;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    public float X;
    public float Y;
    public float radius;//半径
    public float v_X = (float)Math.random();
    public float v_Y = 0;
    private Color color;

    static private float gravity = 0.1f;	//重力係数
    static private float CoR = 0.8f;		//反発係数


    Ball(int radius) {
        this.radius = radius;

        X = (int)(Math.random() * (DrawPanel.panelWidth  - 2*radius));
        Y = (int)(Math.random() * (DrawPanel.panelHeight - 2*radius));

        color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
    }

    void draw(Graphics g) {

        g.setColor(color);
        g.fillOval((int)X, (int)Y, (int)(2*radius), (int)(2*radius));
    }

	public boolean hit(Ball target) {
		if((X - target.X) * (X - target.X) + (Y - target.Y) * (Y - target.Y)
				<= (radius + target.radius) * (radius + target.radius)) {
			color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
		}
		return false;
	}

	public void refrect(Ball target) {

	}

	public void update() {
		// 壁に衝突すれば反射
        if (((X <= 0) &&  (v_X < 0)) ||
        		(X >= (DrawPanel.panelWidth - 2*radius) &&  (v_X > 0))) {
        	v_X = -v_X * CoR;
        }
        if (((Y <= 0) &&  (v_Y < 0)) ||
        		(Y >= (DrawPanel.panelHeight - 2*radius) &&  (v_Y > 0))) {
        	v_Y = -v_Y * CoR;
        }
		//重力
		v_Y += gravity;

        X += v_X;
        Y += v_Y;

        if(Y > DrawPanel.panelHeight - 2*radius)
        	Y = DrawPanel.panelHeight - 2*radius;
        //System.out.println(Y);

        //Calendar now = Calendar.getInstance(); //インスタンス化
        //System.out.println(now.get(now.MILLISECOND));
	}
}
