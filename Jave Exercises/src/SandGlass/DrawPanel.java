package SandGlass;

import static java.awt.RenderingHints.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawPanel extends JPanel implements ActionListener {

    static int ballNum = 700;
    static int ballRadius = 5;

    static int panelHeight = 600;
    static int panelWidth  = panelHeight + ballRadius;

    static int FPS 	= 24;		//1秒あたりのフレーム数
    static Timer timer;			//timer


    static Ball balls[] = new Ball[ballNum];
    static boolean isShowedDialog = false;

    public DrawPanel() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        for(int i = 0; i < ballNum; i++)
        	balls[i] = new Ball(ballRadius);

        new Timer(1000/FPS, this).start();
    }

    public void actionPerformed(ActionEvent e) {
    	boolean is_finish = true;

    	for(int i = 0; i < ballNum; i++) {
    		//衝突判定、衝突していれば、それぞれ反射
    		//取りあえず総当りで実装、ＲＤＣアルゴリズムやセル分割法の方が高速
    		for(int j = i + 1; j < ballNum; j++) {
    			if(balls[i].hit(balls[j])) {
    				balls[i].refrect(balls[j]);
    				//balls[j].refrect(balls[i]);//反発計算は片方のインスタンスで処理する
    			}
    		}
    		//座標更新
    		balls[i].update();

    		//終了判定、ボールが一つでも画面上半分にあれば偽
    		if(balls[i].Y < panelHeight /2)is_finish = false;
    	}
    	repaint();

    	if(is_finish) finish();


    }

    private void finish() {
    	if(!isShowedDialog) {
    		new MyDialog().setVisible(true);
    		isShowedDialog = true;
    	}

	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        for(Ball b: balls)
        	 b.draw(g2D);
    }
}
class MyDialog extends JDialog implements ActionListener {
	MyDialog() {
		//super(owner);
		getContentPane().setLayout(new FlowLayout());

		JButton btn = new JButton("OK");
		btn.addActionListener(this);
		getContentPane().add(btn);

		setTitle("MyDialog");
		setSize(100, 70);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}
}