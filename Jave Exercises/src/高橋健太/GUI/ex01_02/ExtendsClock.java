package 高橋健太.GUI.ex01_02;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import 高橋健太.GUI.ex01_01.SimpleClock;

public class ExtendsClock extends SimpleClock{


	static final List font_list = new List();
	static final List fontsize_list = new List();
	static final List color_list = new List();
	static final List back_list = new List();
	static final String[] COLOR_STR = {
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
	static final Color[] COLOR = {
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

	private Font myFont = new Font("Myfont", Font.PLAIN, 12);
	private Color myFontColor;
	private Color myBackColor;


	public ExtendsClock(String str) {
		super(str);
		init_list();
		setMyMenu();
	}
	private void init_list() {
		//fontリスト設定
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fs = ge.getAvailableFontFamilyNames();
		for (String name : fs) {
			font_list.add(name);
		}
		//フォントサイズリスト設定
		String[] fsize = {"8", "9", "10", "11", "12", "14", "16", "18", "24", "48", "72"};
		for (String name : fsize) {
			fontsize_list.add(name);
		}
		//フォントカラーリスト設定
		for (String name : COLOR_STR) {
			color_list.add(name);
		}
		//フォント背景カラーリスト設定
		for (String name : COLOR_STR) {
			back_list.add(name);
		}
		//debug
		font_list.setSize(50, 100);
		fontsize_list.setSize(300, 100);
	}
	public static void main(String[] args) {
		ExtendsClock clock	= new ExtendsClock("ExtendsClock");
		Thread th			= new Thread(clock);
		th.start();
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
		g.setFont(myFont);
		g.setColor(myFontColor);
		setBackground(myBackColor);
		super.paint(g);
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
			gbc.fill = GridBagConstraints.NONE;
			gbl.setConstraints(ok_btn, gbc);
			gbc.gridx = 2;
			gbl.setConstraints(cancel_btn, gbc);

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
			if(select_size != null) myFont = myFont.deriveFont(Float.valueOf(select_size));

			for(int i = 0; i < COLOR_STR.length; i++) {
				if(COLOR_STR[i].equals(select_color)) myFontColor = COLOR[i];
				if(COLOR_STR[i].equals(select_back)) myBackColor = COLOR[i];
			}
			setVisible(false);
	    }
	}
}
