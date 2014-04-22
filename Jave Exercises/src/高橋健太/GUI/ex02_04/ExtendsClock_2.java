package 高橋健太.GUI.ex02_04;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

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

public class ExtendsClock_2 extends JFrame implements ActionListener{

	private Preferences prefs;					//* プリファレンス */
	private static final String KEY_FONT = "font2";
	private static final String KEY_FONTSIZE = "fontsize2";
	private static final String KEY_FONTCOLOR_R = "fontcolor_R";
	private static final String KEY_FONTCOLOR_G = "fontcolor_G";
	private static final String KEY_FONTCOLOR_B = "fontcolor_B";
	private static final String KEY_BACKCOLOR_R = "backcolor_R";
	private static final String KEY_BACKCOLOR_G = "backcolor_G";
	private static final String KEY_BACKCOLOR_B = "backcolor_B";
	private static final String KEY_CURRENT_X = "current2_x";
	private static final String KEY_CURRENT_Y = "current2_y";


	private boolean  flagUpdate = true;			/* UpDateされたか */
	private Image    imgBuffer;					/* バッファ用のイメージ */
	private Graphics gBuffer;					/* バッファ用のGraphicsクラス */

	private List font_list = new List();
	private List fontsize_list = new List();
	private List color_list = new List();
	private List back_list = new List();



	public static final String[] FONT_STR = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	public static final String[] FONTSIZE_STR = {"8", "12", "18", "24", "48", "72", "120"};

	public JComboBox Font_Select = new JComboBox(FONT_STR);
	public JComboBox FontSize_Select = new JComboBox(FONTSIZE_STR);

	private JDialog dialog;  //ダイアログの宣言
	private TimePanel time_panel;
	private JMenuBar menubar;

	private Font myFont = new Font("Myfont", Font.PLAIN, 48);
	private float myFontSize = 48;

	//defult:Black
	private int myFontColorRed 		= 0;
	private int myFontColorGreen 	= 0;
	private int myFontColorBlue 	= 0;

	//defult:White
	private int myBackColorRed 		= 255;
	private int myBackColorGreen 	= 255;
	private int myBackColorBlue 	= 255;
	private int current_X;
	private int current_Y;

	public ExtendsClock_2(String title) {
		setTitle(title);						//Titleの設定
		prefs = Preferences.userNodeForPackage(this.getClass());//プリファレンスのノード取得
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				save();//プリファレンスのセーブ
				System.exit(0);						//WindowClosw時にプログラムの終了処理を実行する
			}
		});
		load();//プリファレンスのロード
		setSize(300, 150);						//Windowサイズの設定
		setLocation(current_X, current_Y);
		setVisible(true);

		time_panel = new TimePanel();
		time_panel.setForeground(new Color(myFontColorRed, myFontColorGreen, myFontColorBlue));
		time_panel.setBackground(new Color(myBackColorRed, myBackColorGreen, myBackColorBlue));
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
		new ExtendsClock_2("ExtendsClock");
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
			Font f = myFont.deriveFont(myFontSize);
			g.setFont(f);
			FontMetrics fm = g.getFontMetrics();
			Rectangle rectText = fm.getStringBounds(text, g).getBounds();
			Insets insets = ExtendsClock_2.this.getInsets();
			int frameX = rectText.width + insets.left + insets.right;
			int frameY = rectText.height + insets.top + insets.bottom + menubar.getHeight();
			ExtendsClock_2.this.setSize(frameX, frameY);

			g.drawString(text, 0, fm.getMaxAscent());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		dialog = new JDialog(this , "プロパティ" , true);   //ダイアログのタイトル


		Container p = dialog.getContentPane();

		Insets insets = new Insets(0, 0, 0, 0);
		//GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady)
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 200.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

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
				Color color = colorchooser.showDialog(ExtendsClock_2.this, "カラーの選択", Color.white);

				//プリファレンス保存用
				myFontColorRed 		= color.getRed();
				myFontColorGreen 	= color.getGreen();
				myFontColorBlue 	= color.getBlue();
				System.out.println("Font R:" + myFontColorRed
						+ " G:" + myFontColorGreen
						+ " B:" + myFontColorBlue);

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
				Color back_color = colorchooser.showDialog(ExtendsClock_2.this, "背景色の選択", Color.white);

				//プリファレンス保存用
				myBackColorRed 		= back_color.getRed();
				myBackColorGreen 	= back_color.getGreen();
				myBackColorBlue 	= back_color.getBlue();
				System.out.println("BackColor R:" + myBackColorRed
						+ " G:" + myBackColorGreen
						+ " B:" + myBackColorBlue);


				time_panel.setBackground(back_color);
			}
		});
		p.add(select_back_color);


		dialog.setBounds(250,100,300,200); //表示されるダイアログの位置とサイズ
		dialog.setVisible(true);  //ダイアログの表示

	}

	class PropertyPanel extends JPanel implements ActionListener{


		public PropertyPanel() {
			setBackground(new Color(myBackColorRed, myBackColorGreen, myBackColorBlue));
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
			Insets insets = ExtendsClock_2.this.getInsets();
			int frameX = rectText.width + insets.left + insets.right;
			int frameY = rectText.height + insets.top + insets.bottom;
			ExtendsClock_2.this.setSize(frameX, frameY);

			g.drawString(text, 0, fm.getMaxAscent());
		}

	}
	public void save() {
        try {
        	prefs.put(KEY_FONT, myFont.getFamily());
        	prefs.putFloat(KEY_FONTSIZE, myFontSize);
        	prefs.putInt(KEY_FONTCOLOR_R, myFontColorRed);
        	prefs.putInt(KEY_FONTCOLOR_G, myFontColorGreen);
        	prefs.putInt(KEY_FONTCOLOR_B, myFontColorBlue);
        	prefs.putInt(KEY_BACKCOLOR_R, myBackColorRed);
        	prefs.putInt(KEY_BACKCOLOR_G, myBackColorGreen);
        	prefs.putInt(KEY_BACKCOLOR_B, myBackColorBlue);
        	prefs.putInt(KEY_CURRENT_X, getX());
        	prefs.putInt(KEY_CURRENT_Y, getY());
            prefs.flush();

            System.out.println("Font R:" + myFontColorRed
    				+ " G:" + myFontColorGreen
    				+ " B:" + myFontColorBlue);

        } catch (BackingStoreException ex) {
            ex.printStackTrace();
        }
    }
    public void load() {
        String font = prefs.get(KEY_FONT, "no data");
        if(font.equals("no data"))return;//フォントが設定されて無ければリターン
        myFont = Font.decode(font);
        myFontSize = prefs.getFloat(KEY_FONTSIZE, -1);
        myFontColorRed 		= prefs.getInt(KEY_FONTCOLOR_R, -1);
        myFontColorGreen 	= prefs.getInt(KEY_FONTCOLOR_G, -1);
        myFontColorBlue 	= prefs.getInt(KEY_FONTCOLOR_B, -1);
        myBackColorRed 		= prefs.getInt(KEY_BACKCOLOR_R, -1);
        myBackColorGreen 	= prefs.getInt(KEY_BACKCOLOR_G, -1);
        myBackColorBlue 	= prefs.getInt(KEY_BACKCOLOR_B, -1);
        current_X = prefs.getInt(KEY_CURRENT_X, -1);
        current_Y = prefs.getInt(KEY_CURRENT_Y, -1);

		System.out.println("Font R:" + myFontColorRed
				+ " G:" + myFontColorGreen
				+ " B:" + myFontColorBlue);

    }
}