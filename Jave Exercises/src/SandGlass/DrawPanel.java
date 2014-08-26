package SandGlass;

import static java.awt.RenderingHints.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements ActionListener {
    static final int panelWidth  = 300;
    static final int panelHeight = 200;

    static final int ballNum = 2;
    Ball balls[] = new Ball[ballNum];

    public DrawPanel() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        for(int i = 0; i < ballNum; i++)
        	balls[i] = new Ball(15);
    }

    public void actionPerformed(ActionEvent e) {
    	for(int i = 0; i < ballNum; i++) {

    		//衝突判定、衝突していれば、それぞれ反射
    		//取りあえず総当りで実装、ＲＤＣアルゴリズムやセル分割法の方が高速
    		for(int j = i + 1; j < ballNum; j++) {
    			if(balls[i].hit(balls[j])) {
    				balls[i].refrect(balls[j]);
    				balls[j].refrect(balls[i]);
    			}
    		}

    		//座標更新
    		balls[i].update();
    	}
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        for(Ball b: balls)
        	 b.draw(g2D);
    }
}