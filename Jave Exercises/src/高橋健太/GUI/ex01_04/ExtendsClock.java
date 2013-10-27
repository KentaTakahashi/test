package 高橋健太.GUI.ex01_04;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class ExtendsClock extends Frame implements Runnable {

	private Preferences prefs;					//* プリファレンス */
	private static final String KEY_FONT = "font";
	private static final String KEY_FONTSIZE = "fontsize";
	private static final String KEY_FONTCOLOR = "fontcolor";
	private static final String KEY_BACKCOLOR = "backcolor";
	private static final String KEY_CURRENT_X = "current_x";
	private static final String KEY_CURRENT_Y = "current_y";


	private boolean  flagUpdate = true;			/* UpDateされたか */
	private Image    imgBuffer;					/* バッファ用のイメージ */
	private Graphics gBuffer;					/* バッファ用のGraphicsクラス */

	private List font_list = new List();
	private List fontsize_list = new List();
	private List color_list = new List();
	private List back_list = new List();

	public static final String[] FONT_STR = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	public static final String[] FONTSIZE_STR = {"8", "12", "18", "24", "48", "72", "120"};
	public static final String[] COLOR_STR = {
		"BLACK",
		"BLUE",
		"CYAN",
		"DARK_GRAY",
		"GRAY",
		"GREEN",
		"LIGHT_GRAY",
		"MAGENTA",
		"ORANGE",
		"PINK",
		"RED",
		"WHITE",
		"YELLOW"
		};
	public static final Color[] COLOR = {
		Color.BLACK,
		Color.BLUE,
		Color.CYAN,
		Color.DARK_GRAY,
		Color.GRAY,
		Color.GREEN,
		Color.LIGHT_GRAY,
		Color.MAGENTA,
		Color.ORANGE,
		Color.PINK,
		Color.RED,
		Color.WHITE,
		Color.YELLOW
		};

	private Font myFont = new Font("Myfont", Font.PLAIN, 48);
	private float myFontSize = 48;
	private int myFontColorIndex = 0;	//BLACK
	private int myBackColorIndex = 11;	//WHITE
	private int current_X;
	private int current_Y;

	public ExtendsClock(String str) {
		setTitle(str);							//Titleの設定
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				save();//プリファレンスのセーブ
				System.exit(0);						//WindowClosw時にプログラムの終了処理を実行する
			}
		});

		//WindowClose時にプログラム終了するよう設定
		init_list();
		setMyMenu();
		prefs = Preferences.userNodeForPackage(this.getClass());//プリファレンスのノード取得
		/*
		try {
			prefs.removeNode();
		} catch (BackingStoreException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		*/
		load();//プリファレンスのロード
		//setSize(100, 100);
		setLocation(current_X, current_Y);
		setVisible(true);						//Windowを表示に設定
	}
	private void init_list() {
		//fontリスト設定
		for (String fn : FONT_STR) font_list.add(fn);
		//フォントサイズリスト設定
		for (String fs : FONTSIZE_STR) fontsize_list.add(fs);
		//フォントカラーリスト設定
		for (String fc : COLOR_STR) color_list.add(fc);
		//フォント背景カラーリスト設定
		for (String bl : COLOR_STR) back_list.add(bl);
	}
	public static void main(String[] args) {
		ExtendsClock clock	= new ExtendsClock("ExtendsClock");
		Thread th			= new Thread(clock);
		th.start();
	}
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
	private void setMyMenu() {

		// [MenuBar]作成
		setLayout(new FlowLayout());
		MenuBar menuBar = new MenuBar();
		setMenuBar(menuBar);

		// [Menu]作成
		Menu menu = new Menu("Menu");
		menuBar.add(menu);

		// [Menu]-[Property]作成、OpenMyDialog
		MenuItem menuProperty = new MenuItem("Property...", new MenuShortcut('P'));
		menuProperty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenMyDialog();
			}
		});
		menu.add(menuProperty);
	}
	private void OpenMyDialog() {
		MyPropertyDialog dlg = new MyPropertyDialog(this);
        dlg.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {

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
			int frameX = rectText.width + i.left + i.right;
			int frameY = rectText.height + i.top + i.bottom;
			setSize(frameX, frameY);
			imgBuffer = createImage(frameX, frameY);
			gBuffer = imgBuffer.getGraphics();

			flagUpdate =false;
		}

		/* キャンバスのサイズを取得する */
		Dimension d = getSize();

		/* 背景色を設定 */
		gBuffer.setColor(COLOR[myBackColorIndex]);
		gBuffer.fillRect(0, 0, d.width, d.height);

		/* 描画色を設定 */
		gBuffer.setColor(COLOR[myFontColorIndex]);
		FontMetrics fm = gBuffer.getFontMetrics();

		Insets insets = this.getInsets();
		gBuffer.drawString(text, insets.left, insets.top +fm.getMaxAscent());

		g.drawImage(imgBuffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	class MyPropertyDialog extends Dialog implements ActionListener {
		MyPropertyDialog(Frame owner) {
			super(owner);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
			});//WindowClose時にプログラム終了するよう設定

			GridBagLayout gbl = new GridBagLayout();
			setLayout(gbl);
			setTitle("MyDialog");
			setSize(300, 400);


			Label font_label = new Label("フォント");
			Label fontsize_label = new Label("フォントサイズ");
			Label color_label = new Label("カラー");
			Label back_label = new Label("背景色");

			Button ok_btn = new Button("OK");
			Button cancel_btn = new Button("キャンセル");

			font_label.setAlignment(Label.RIGHT);
			fontsize_label.setAlignment(Label.RIGHT);
			color_label.setAlignment(Label.RIGHT);
			back_label.setAlignment(Label.RIGHT);

			Insets insets = new Insets(0, 0, 0, 0);
			//GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady)
			GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 200.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

			gbl.setConstraints(font_label, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(fontsize_label, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(color_label, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(back_label, gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridwidth = 2;

			gbl.setConstraints(font_list, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(fontsize_list, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(color_list, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(back_list, gbc);

			gbc.gridy = GridBagConstraints.RELATIVE;
			gbc.gridwidth = 1;
			gbl.setConstraints(ok_btn, gbc);
			gbc.gridx =2;
			//gbc.gridwidth = 3;
			gbl.setConstraints(cancel_btn, gbc);

			/*
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.NONE;
			gbl.setConstraints(ok_btn, gbc);
			gbc.gridx = 2;
			gbl.setConstraints(cancel_btn, gbc);
			*/

			add(font_label);
			add(fontsize_label);
			add(color_label);
			add(back_label);

			add(font_list);
			add(fontsize_list);
			add(color_list);
			add(back_list);

			add(ok_btn);
			ok_btn.addActionListener(this);

			add(cancel_btn);
			//キャンセルボタンが押された場合、何も設定せずダイアログを閉じる
			cancel_btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
	    }
		//OKボタンが押された場合、設定された項目をメンバ変数に設定する
		public void actionPerformed(ActionEvent e) {
			String select_font = font_list.getSelectedItem();
			String select_size = fontsize_list.getSelectedItem();
			String select_color = color_list.getSelectedItem();
			String select_back = back_list.getSelectedItem();

			if(select_font != null) myFont = Font.decode(select_font);
			if(select_size != null) myFontSize = Float.valueOf(select_size);

			for(int i = 0; i < COLOR_STR.length; i++) {
				if(COLOR_STR[i].equals(select_color)) myFontColorIndex = i;
				if(COLOR_STR[i].equals(select_back)) myBackColorIndex = i;
			}
			setVisible(false);

			flagUpdate = true;
	    }
	}
	public void save() {
        try {
        	prefs.put(KEY_FONT, myFont.getFamily());
        	prefs.putFloat(KEY_FONTSIZE, myFontSize);
        	prefs.putInt(KEY_FONTCOLOR, myFontColorIndex);
        	prefs.putInt(KEY_BACKCOLOR, myBackColorIndex);
        	prefs.putInt(KEY_CURRENT_X, getX());
        	prefs.putInt(KEY_CURRENT_Y, getY());
            prefs.flush();
        } catch (BackingStoreException ex) {
            ex.printStackTrace();
        }
    }
    public void load() {
        String font = prefs.get(KEY_FONT, "no data");
        if(font.equals("no data"))return;//フォントが設定されて無ければリターン
        myFont = Font.decode(font);
        myFontSize = prefs.getFloat(KEY_FONTSIZE, -1);
        myFontColorIndex = prefs.getInt(KEY_FONTCOLOR, -1);
        myBackColorIndex = prefs.getInt(KEY_BACKCOLOR, -1);
        current_X = prefs.getInt(KEY_CURRENT_X, -1);
        current_Y = prefs.getInt(KEY_CURRENT_Y, -1);
    }
}
