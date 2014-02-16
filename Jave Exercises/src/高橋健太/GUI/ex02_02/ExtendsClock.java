package 高橋健太.GUI.ex02_02;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ExtendsClock extends JFrame implements ActionListener{

	public static final String[] FONT_STR = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	public static final String[] FONTSIZE_STR = {"8", "12", "18", "24", "48", "72", "120"};

	public JComboBox Font_Select = new JComboBox(FONT_STR);
	public JComboBox FontSize_Select = new JComboBox(FONTSIZE_STR);

	private JDialog dialog;  //ダイアログの宣言
	private TimePanel time_panel;
	private JMenuBar menubar;

	private Font myFont = new Font("Myfont", Font.PLAIN, 48);
	private float myFontSize = 48;
	private int current_X;
	private int current_Y;

	public ExtendsClock(String title) {
		setTitle(title);							//Titleの設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //WindowClose時にプログラム終了するよう設定
		setSize(300, 150);						//Windowサイズの設定
		setBackground(Color.white);
		setVisible(true);

		time_panel = new TimePanel();
		add(time_panel);				//Windowを表示に設定
		new Timer(10, time_panel).start();

		//menubar追加
		menubar = new JMenuBar();
		JMenu menu1 = new JMenu("Menu");
		menubar.add(menu1);
		JMenuItem Property = new JMenuItem("Property");
	    menu1.add(Property);
	    Property.addActionListener(this);
		setJMenuBar(menubar);

	}
	public static void main(String[] args) {
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		new ExtendsClock("ExtendsClock");
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

			Font f = myFont;
			g.setFont(f);
			FontMetrics fm = g.getFontMetrics();
			Rectangle rectText = fm.getStringBounds(text, g).getBounds();
			Insets insets = ExtendsClock.this.getInsets();
			int frameX = rectText.width + insets.left + insets.right;
			int frameY = rectText.height + insets.top + insets.bottom + menubar.getHeight();
			ExtendsClock.this.setSize(frameX, frameY);

			g.drawString(text, 0, fm.getMaxAscent());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		dialog = new JDialog(this , "プロパティ" , true);   //ダイアログのタイトル


		Container p = dialog.getContentPane();
		p.setLayout(new GridLayout(4, 2));

		p.add(new JLabel("フォント"));
		Font_Select = new JComboBox(FONT_STR);
		Font_Select.setPreferredSize(new Dimension(80, 30));
		p.add(Font_Select);
		Font_Select.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				myFont = Font.decode(FONT_STR[Font_Select.getSelectedIndex()]);
				myFont = myFont.deriveFont(myFontSize);
				time_panel.repaint();

				System.out.println(myFont);
			}
		});

		p.add(new JLabel("フォントサイズ"));
		FontSize_Select = new JComboBox(FONTSIZE_STR);
		FontSize_Select.setPreferredSize(new Dimension(80, 30));
		p.add(FontSize_Select);
		FontSize_Select.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				myFontSize = Float.valueOf(FONTSIZE_STR[FontSize_Select.getSelectedIndex()]);
				myFont = myFont.deriveFont(myFontSize);
				time_panel.repaint();

				System.out.println(myFontSize);
			}
		});


		p.add(new JLabel("カラー"));
		JButton select_color = new JButton("カラーの選択");
		select_color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser colorchooser = new JColorChooser();
				Color color = colorchooser.showDialog(ExtendsClock.this, "カラーの選択", Color.white);
				time_panel.setForeground(color);
			}
		});
		p.add(select_color);

		p.add(new JLabel("背景色"));
		JButton select_back_color = new JButton("背景色の選択");
		select_back_color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser colorchooser = new JColorChooser();
				Color back_color = colorchooser.showDialog(ExtendsClock.this, "背景色の選択", Color.white);
				System.out.println(back_color.toString());
				time_panel.setBackground(back_color);
			}
		});
		p.add(select_back_color);


		dialog.setBounds(250,100,300,200); //表示されるダイアログの位置とサイズ
		dialog.setVisible(true);  //ダイアログの表示

	}

	class PropertyPanel extends JPanel implements ActionListener{


		public PropertyPanel() {
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
			Insets insets = ExtendsClock.this.getInsets();
			int frameX = rectText.width + insets.left + insets.right;
			int frameY = rectText.height + insets.top + insets.bottom;
			ExtendsClock.this.setSize(frameX, frameY);

			g.drawString(text, 0, fm.getMaxAscent());
		}

	}
}