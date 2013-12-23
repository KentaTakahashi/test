package 高橋健太.GUI.ex02_01;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimpleClock extends JFrame{

	public SimpleClock(String title) {
		setTitle(title);							//Titleの設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //WindowClose時にプログラム終了するよう設定
		setSize(300, 150);						//Windowサイズの設定
		setBackground(Color.white);
		setVisible(true);

		TimePanel time_panel = new TimePanel();
		add(time_panel);				//Windowを表示に設定
		new Timer(10, time_panel).start();

	}
	public static void main(String[] args) {
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		new SimpleClock("SimpleClock");
	}

	class TimePanel extends JPanel implements ActionListener{

		public TimePanel() {
	        setBackground(Color.white);
	    }
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);

			Date date = new Date();
			String text = date.toString();

			Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 36);
			g.setFont(f);
			FontMetrics fm = g.getFontMetrics();
			Rectangle rectText = fm.getStringBounds(text, g).getBounds();
			Insets insets = SimpleClock.this.getInsets();
			int frameX = rectText.width + insets.left + insets.right;
			int frameY = rectText.height + insets.top + insets.bottom;
			SimpleClock.this.setSize(frameX, frameY);

			g.drawString(text, 0, fm.getMaxAscent());
		}


	}
}