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

    Ball ball = new Ball(50);

    public DrawPanel() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    public void actionPerformed(ActionEvent event) {
    	ball.M00();
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2D = (Graphics2D)graphics;
        g2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        ball.M01(g2D);
    }
}