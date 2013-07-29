package 高橋健太.GUI.ex01_02;

import java.awt.Frame;
import java.awt.Graphics;
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
				Thread.sleep(1000);				//スリープ１秒;
			} catch(InterruptedException e) {
				;								//特に処理はしない
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		Date d = new Date();
		g.drawString(d.toString(), 100, 100);	//時計を表示
	}
	//
	class WindowClose extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);						//WindowClosw時にプログラムの終了処理を実行する
		}
	}
}