package SandGlass;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class PropertyDialog extends JFrame
{
    private Container p;
    static public final String[] FRAME_SIZE = {"200", "400", "600", "800", "1000"};
    static public final String[] FPS = {"15", "24", "30", "48", "72"};
    static public final String[] BALL_NUM = {"10", "50", "100", "200", "500", "1000", "2000", "5000", "10000", "30000"};
    static public final String[] BALLSIZE_STR = {"1", "2", "3", "5", "7", "10", "15"};

    public JComboBox FRAME_SIZE_Select;
    public JComboBox FPS_Select;
    public JComboBox BALL_NUM_Select;
    public JComboBox BALLSIZE_STR_Select;

    public JCheckBox checkRandomBallSize;

    public JTextField gravityText;
    public JTextField e1_Text;
    public JTextField e2_Text;
    public JTextField e3_Text;

    private JDialog dialog;  //ダイアログの宣言

    static int FRAME_SIZE_Index = 2;
    static int FPS_Index        = 1;
    static int BALL_NUM_Index   = 4;
    static int BALLSIZE_Index   = 3;
    static private boolean isRandomBallSize = false;
    static String Gravity_Str = "0.01";
    static String e1_Str      = "0.5";
    static String e2_Str      = "0.95";
    static String e3_Str      = "0.5";

    public PropertyDialog() {
        super("プロパティ");
        dialog = new JDialog(this , "プロパティ" , true);   //ダイアログのタイトル

        p = dialog.getContentPane();

        GridBagLayout gbl = new GridBagLayout();
        p.setLayout(gbl);
        p.setSize(500, 600);

        FRAME_SIZE_Select = new JComboBox(FRAME_SIZE);
        FRAME_SIZE_Select.setSelectedIndex(FRAME_SIZE_Index);
        FRAME_SIZE_Select.setPreferredSize(new Dimension(80, 40));

        FPS_Select = new JComboBox(FPS);
        FPS_Select.setSelectedIndex(FPS_Index);
        FPS_Select.setPreferredSize(new Dimension(80, 30));

        BALL_NUM_Select = new JComboBox(BALL_NUM);
        BALL_NUM_Select.setSelectedIndex(BALL_NUM_Index);
        BALL_NUM_Select.setPreferredSize(new Dimension(80, 30));

        BALLSIZE_STR_Select = new JComboBox(BALLSIZE_STR);
        BALLSIZE_STR_Select.setSelectedIndex(BALLSIZE_Index);
        BALLSIZE_STR_Select.setPreferredSize(new Dimension(80, 30));

        checkRandomBallSize = new JCheckBox("", isRandomBallSize);

        gravityText = new JTextField(Gravity_Str, 80);
        e1_Text     = new JTextField(e1_Str, 80);
        e2_Text     = new JTextField(e2_Str, 80);
        e3_Text     = new JTextField(e3_Str, 80);

        Insets insets = new Insets(0, 0, 0, 0);
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 200.0, 100.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);


        JLabel l_1 = new JLabel("フレームサイズ"           , JLabel.LEFT);
        JLabel l_2 = new JLabel("FPS"                      , JLabel.LEFT);
        JLabel l_3 = new JLabel("ボール個数"               , JLabel.LEFT);
        JLabel l_4 = new JLabel("ボールサイズ"             , JLabel.LEFT);
        JLabel l_5 = new JLabel("サイズランダム"           , JLabel.LEFT);
        JLabel l_6 = new JLabel("重力係数"                 , JLabel.LEFT);
        JLabel l_7 = new JLabel("反発係数　ボール‐ボール" , JLabel.LEFT);
        JLabel l_8 = new JLabel("反発係数　ボール‐側面"   , JLabel.LEFT);
        JLabel l_9 = new JLabel("反発係数　ボール‐底面"   , JLabel.LEFT);
        JButton ok_btn = new JButton("OK");



        gbl.setConstraints(l_1, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(l_2, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(l_3, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(l_4, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(l_5, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(l_6, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(l_7, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(l_8, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(l_9, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        gbl.setConstraints(FRAME_SIZE_Select, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(FPS_Select, gbc);
        gbl.setConstraints(BALL_NUM_Select, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(BALLSIZE_STR_Select, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(checkRandomBallSize, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(gravityText, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(e1_Text, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(e2_Text, gbc);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbl.setConstraints(e3_Text, gbc);

        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridwidth = 1;
        gbl.setConstraints(ok_btn, gbc);
        gbc.gridx =2;

        p.add(l_1);
        p.add(l_2);
        p.add(l_3);
        p.add(l_4);
        p.add(l_5);
        p.add(l_6);
        p.add(l_7);
        p.add(l_8);
        p.add(l_9);

        p.add(FRAME_SIZE_Select);
        p.add(FPS_Select);
        p.add(BALL_NUM_Select);
        p.add(BALLSIZE_STR_Select);
        p.add(checkRandomBallSize);
        p.add(gravityText);
        p.add(e1_Text);
        p.add(e2_Text);
        p.add(e3_Text);

        p.add(ok_btn);
        //OKが押された場合、設定を反映してダイアログを閉じる
        ok_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int ballNumIndex = BALL_NUM_Select.getSelectedIndex();

                FRAME_SIZE_Index = FRAME_SIZE_Select.getSelectedIndex();
                FPS_Index        = FPS_Select.getSelectedIndex();
                BALL_NUM_Index   = BALL_NUM_Select.getSelectedIndex();
                BALLSIZE_Index   = BALLSIZE_STR_Select.getSelectedIndex();
                isRandomBallSize = checkRandomBallSize.isSelected();
                Gravity_Str = gravityText.getText();
                e1_Str      = e1_Text.getText();
                e2_Str      = e2_Text.getText();
                e3_Str      = e3_Text.getText();;

                DrawPanel.panelHeight =  Integer.valueOf(FRAME_SIZE[FRAME_SIZE_Index]);
                DrawPanel.FPS = Integer.valueOf(FPS[FPS_Index]);
                DrawPanel.ballNum =  Integer.valueOf(BALL_NUM[ballNumIndex]);
                DrawPanel.ballRadius = Integer.valueOf(BALLSIZE_STR[BALLSIZE_Index]);
                DrawPanel.isRandomBallSize = isRandomBallSize;
                Ball.gravity = Double.valueOf(Gravity_Str);
                Ball.e = Double.valueOf(e1_Str);
                Ball.e2 = Double.valueOf(e2_Str);
                Ball.e3 = Double.valueOf(e3_Str);

                dialog.setVisible(false);
            }
        });

        dialog.setBounds(250,100,300,200); //表示されるダイアログの位置とサイズ
        dialog.setVisible(true);  //ダイアログの表示
    }
}
