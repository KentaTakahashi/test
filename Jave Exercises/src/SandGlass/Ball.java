package SandGlass;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    float X;
    float Y;
    float radius;
    float v_X = (float)Math.random();
    float v_Y = 0;

    float gravity = 0.1f;	//重力係数
    float CoR = 0.9f;		//反発係数

    Ball(int A00) {
        this.radius = A00;

        X = (int)(Math.random() * (DrawPanel.panelWidth  - radius));
        Y = (int)(Math.random() * (DrawPanel.panelHeight - radius));
    }

    void M00() {
    	v_Y += gravity;

        X += v_X;
        Y += v_Y;

        // 壁に衝突すれば反射
        if (X >= (DrawPanel.panelWidth  - radius) || X <= 0) {
        	v_X = -v_X * CoR;
        }
        if (Y >= (DrawPanel.panelHeight - radius) || Y <= 0) {
        	v_Y = -v_Y * CoR;
        }
        System.out.println(v_X + " , " + v_Y);
    }

    void M01(Graphics A00) {
        A00.setColor(Color.orange);
        A00.fillOval((int)X, (int)Y, (int)radius, (int)radius);
    }
}
