package 高橋健太.GUI.ex02_03;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;

import javax.swing.JWindow;

import 高橋健太.GUI.ex01_02.ExtendsClock;

public class WindowClock extends JWindow implements Runnable, MouseListener, MouseMotionListener {

	private PopupMenu pop = new PopupMenu("メニュー");
	private PopupMenu font_list = new PopupMenu("フォント");
	private PopupMenu fontsize_list = new PopupMenu("サイズ");
	private PopupMenu color_list = new PopupMenu("カラー");
	private PopupMenu back_list = new PopupMenu("背景色");

	private boolean  flagUpdate = true;			/* UpDateされたか */
	private Image    imgBuffer;					/* バッファ用のイメージ */
	private Graphics gBuffer;					/* バッファ用のGraphicsクラス */

	private Font myFont = new Font("Myfont", Font.PLAIN, 48);
	private float myFontSize = 48;
	private Color myFontColor = Color.BLACK;
	private Color myBackColor = Color.WHITE;

	private int frameX;
	private int frameY;
	private int MouseX;
	private int MouseY;
	private int OffsetX;
	private int OffsetY;

	public WindowClock(Frame owner) {
		super(owner);
		setSize(100, 100);						//Windowサイズの設定
		setVisible(true);						//Windowを表示に設定
		setAlwaysOnTop(true);					//常に最前面に表示

		//fontリスト設定
		for (String fn : ExtendsClock.FONT_STR) font_list.add(fn);
		//フォントサイズリスト設定
		for (String fs : ExtendsClock.FONTSIZE_STR) fontsize_list.add(fs);
		//フォントカラーリスト設定
		for (String fc : ExtendsClock.COLOR_STR) color_list.add(fc);
		//フォント背景カラーリスト設定
		for (String bl : ExtendsClock.COLOR_STR) back_list.add(bl);

		pop.add(font_list);
		pop.add(fontsize_list);
		pop.add(color_list);
		pop.add(back_list);
		pop.add("終了");

		font_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select_font = e.getActionCommand();
				if(select_font != null) myFont = Font.decode(select_font);
				flagUpdate =true;
			}
		});

		fontsize_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select_size = e.getActionCommand();
				if(select_size != null) myFontSize = Float.valueOf(select_size);
				flagUpdate =true;
			}
		});

		color_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select_color = e.getActionCommand();
				for(int i = 0; i < ExtendsClock.COLOR_STR.length; i++) {
					if(ExtendsClock.COLOR_STR[i].equals(select_color)) myFontColor = ExtendsClock.COLOR[i];
				}
				flagUpdate =true;
			}
		});
		back_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select_back = e.getActionCommand();
				for(int i = 0; i < ExtendsClock.COLOR_STR.length; i++) {
					if(ExtendsClock.COLOR_STR[i].equals(select_back)) myBackColor = ExtendsClock.COLOR[i];
				}
				flagUpdate =true;
			}
		});
		pop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String menu = e.getActionCommand();
				if(menu.equals("終了")) System.exit(0);
			}
		});
		add(pop);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public static void main(String[] args) {
		WindowClock clock	= new WindowClock(null);
		Thread th			= new Thread(clock);
		th.start();
	}
	public void run() {
		while(true) {
			paint();							//表示
			try {
				Thread.sleep(10);				//スリープ10msec;
			} catch(InterruptedException e) {
				;								//特に処理はしない
			}
		}
	}
	public void paint() {
		if(imgBuffer == null) {
			imgBuffer = createImage(100, 100);
			gBuffer = imgBuffer.getGraphics();
		}

		Date date = new Date();
		String text = date.toString();

		/* fontを設定 */
		gBuffer.setFont(myFont.deriveFont(myFontSize));
		if(flagUpdate){
			FontMetrics fm = gBuffer.getFontMetrics();
			Rectangle rectText = fm.getStringBounds(text, gBuffer).getBounds();
			Insets i = this.getInsets();
			frameX = rectText.width + i.left + i.right;
			frameY = rectText.height + i.top + i.bottom;
			this.setSize(frameX, frameY);
			imgBuffer = createImage(frameX, frameY);
			gBuffer = imgBuffer.getGraphics();

			flagUpdate =false;
		}
		/* キャンバスのサイズを取得する */
		Dimension d = getSize();

		/* 背景色を設定 */
		gBuffer.setColor(myBackColor);
		gBuffer.fillRect(0, 0, d.width, d.height);

		/* 描画色を設定 */
		gBuffer.setColor(myFontColor);
		FontMetrics fm = gBuffer.getFontMetrics();

		Insets insets = this.getInsets();
		gBuffer.drawString(text, insets.left, insets.top +fm.getMaxAscent());

		getGraphics().drawImage(imgBuffer, 0, 0, this);
		setBounds(MouseX - OffsetX, MouseY - OffsetY, frameX, frameY);

	}
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			OffsetX = e.getXOnScreen() - (int)getBounds().getX();
			OffsetY = e.getYOnScreen() - (int)getBounds().getY();
			try {
				Thread.sleep(100);				//スリープ100msec;
			} catch(InterruptedException _e) {
				;								//特に処理はしない
			}
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			pop.show(this , e.getX() , e.getY());
		}
	}
	public void mouseDragged(MouseEvent e) {
			MouseX = e.getXOnScreen();
			MouseY = e.getYOnScreen();
	}
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}