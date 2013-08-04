package 高橋健太.GUI.ex01_02;

import java.awt.Button;
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

	private Font myFont = new Font("Myfont", Font.PLAIN, 12);
	private List font_list = new List();
	private List fontsize_list = new List();
	private List color_list = new List();
	private List back_list = new List();


	public ExtendsClock(String str) {
		super(str);
		setMyMenu();
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
		// TODO 自動生成されたメソッド・スタブ
		g.setFont(myFont);
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
			setSize(400, 400);

			//GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady)

			Label font_label = new Label("フォント");
			Label fontsize_label = new Label("フォントサイズ");
			Label color_label = new Label("カラー");
			Label back_label = new Label("背景色");

			Button ok_btn = new Button("OK");

			font_list.setSize(1, 1);

			font_label.setAlignment(Label.RIGHT);
			fontsize_label.setAlignment(Label.RIGHT);
			color_label.setAlignment(Label.RIGHT);
			back_label.setAlignment(Label.RIGHT);

			Insets insets = new Insets(0, 0, 0, 0);
			GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);

			gbl.setConstraints(font_label, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(fontsize_label, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(color_label, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(back_label, gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			gbl.setConstraints(font_list, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(fontsize_list, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(color_list, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(back_list, gbc);
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbl.setConstraints(ok_btn, gbc);

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

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			String[] fs = ge.getAvailableFontFamilyNames();
			for (String name : fs) {
				font_list.add(name);
			}
	    }
	    public void actionPerformed(ActionEvent e) {
	    	//setFont(font_list.getSelectedItem());
	    	myFont = new Font("Myfont", Font.ROMAN_BASELINE, 20);
	    	System.out.println("test");
	    	setVisible(false);
	    }
	}
}
