package SandGlass;

import java.awt.Color;

import javax.swing.JFrame;

public class BallReflection extends JFrame {

    public BallReflection() {
        DrawPanel dp = new DrawPanel();
        add(dp);
    }

    public static void main(String[] args) {
        JFrame jf = new BallReflection();
        jf.setTitle("砂時計");
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setBackground(Color.white);
        jf.setResizable(false);
        jf.pack();
        jf.setVisible(true);
    }
}
