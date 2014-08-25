package SandGlass;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
    // パネルサイズ
    private static final int WIDTH = 240;
    private static final int HEIGHT = 240;

    public MainPanel() {
        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // 変数などの初期化
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 盤面を描いたり、フィールドを描いたりする
        g.drawString("Hello World", 20, 50);
    }
}