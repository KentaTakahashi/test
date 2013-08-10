package 高橋健太.GUI.ex01_01;

import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class SimpleClock extends Frame implements Runnable {

	public SimpleClock(String str) {
		setTitle(str);							//Titleの設定
		setSize(400, 200);						//Windowサイズの設定
		setVisible(true);						//Windowを表示に設定
		addWindowListener(new WindowClose());	//WindowClose時にプログラム終了するよう設定
	}
	public static void main(String[] args) {
		SimpleClock clock	= new SimpleClock("SimpleClock");
		Thread th			= new Thread(clock);
		th.start();
	}
	@Override
	public void run() {
		while(true) {
			repaint();							//再表示
			try {
				Thread.sleep(10);				//スリープ10msec;
			} catch(InterruptedException e) {
				;								//特に処理はしない
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		Date date = new Date();
		String text = date.toString();

		FontMetrics fm = g.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(text, g).getBounds();
		Insets insets = this.getInsets();
		int frameX = rectText.width + insets.left + insets.right;
		int frameY = rectText.height + insets.top + insets.bottom;
		this.setSize(frameX, frameY);
		g.drawString(text, insets.left, insets.top +fm.getMaxAscent());
	}
	class WindowClose extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);						//WindowClosw時にプログラムの終了処理を実行する
		}
	}
}