package 高橋健太.GUI.ex01_02;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import 高橋健太.GUI.ex01_01.SimpleClock;

public class ExtendsClock extends SimpleClock{

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

	class MyPropertyDialog extends Dialog implements ActionListener {
		MyPropertyDialog(Frame owner) {
	        super(owner);
	        setLayout(new FlowLayout());
	        Button b1 = new Button("OK");
	        b1.addActionListener(this);
	        add(b1);
	        setTitle("MyDialog");
	        setSize(80, 80);
	    }
	    public void actionPerformed(ActionEvent e) {
	    	setVisible(false);
	    }
	}
}
