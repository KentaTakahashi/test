package SandGlass;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

public class BallReflection extends JFrame {
    final static int FPS 	= 30;		//1秒あたりのフレーム数
    final static int invFPS = 1000/FPS;	//FPSの逆数*1000、フレーム間隔(ms)

    public BallReflection() {
        DrawPanel dp = new DrawPanel();
        add(dp);

        new Timer(invFPS, dp).start();
    }

    public static void main(String[] args) {
        JFrame jf = new BallReflection();
        jf.setTitle("反射するボール");
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setBackground(Color.white);
        jf.setResizable(false);
        jf.pack();
        jf.setVisible(true);
    }
}
