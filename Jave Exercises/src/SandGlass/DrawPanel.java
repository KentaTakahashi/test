package SandGlass;

import static java.awt.RenderingHints.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawPanel extends JPanel implements ActionListener {

    static int ballNum;
    static int ballRadius;
    static int panelHeight;
    static int panelWidth;
    static int FPS;		//1秒あたりのフレーム数
    static Timer timer;			//timer

    static Ball balls[];
    static boolean isRandomBallSize;
    static boolean isShowedDialog;

    static Date startTime;

    JFrame owner;

    public DrawPanel(JFrame owner) {
        this.owner = owner;
        init();
        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    public void actionPerformed(ActionEvent e) {
    	boolean is_finish = true;

    	for(int i = 0; i < ballNum; i++) {
    		//衝突判定、衝突していれば、それぞれ反射
    		//取りあえず総当りで実装、ＲＤＣアルゴリズムやセル分割法の方が高速
    		for(int j = i + 1; j < ballNum; j++) {
    			if(balls[i].hit(balls[j])) {
    				balls[i].refrect(balls[j]);
    				//balls[j].refrect(balls[i]);//反発計算は片方のインスタンスで処理する
    			}
    		}
    		//座標更新
    		balls[i].update();

    		//終了判定、ボールが一つでも画面上半分にあれば偽
    		if(balls[i].Y < panelHeight /2)is_finish = false;
    	}

    	if(is_finish) finish();

        repaint();
    }

    private void init() {
        new PropertyDialog();

        setBackground(Color.white);
        panelWidth  = panelHeight + ballRadius;

        System.out.println(panelWidth + " x " + panelHeight);

        Insets insets = owner.getInsets();
        int frameX = panelWidth + insets.left + insets.right;
        int frameY = panelHeight + insets.top + insets.bottom;
        owner.setSize(new Dimension(frameX, frameY));
        //setPreferredSize(new Dimension(panelWidth, panelHeight));
        //setSize(panelWidth, panelHeight);

        balls = new Ball[ballNum];
        for(int i = 0; i < ballNum; i++) {
            if(isRandomBallSize)
                balls[i] = new Ball(ballRadius * Math.random());
            else
                balls[i] = new Ball(ballRadius);
        }

        timer = new Timer(1000/FPS, this);
        timer.start();

        startTime = new Date();

        isShowedDialog = false;
    }
    private void finish() {
        System.out.println("finish");
    	if(!isShowedDialog) {
            isShowedDialog = true;
    	    new FinishDialog();

            timer.stop();
    		init();
    	}
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        for(Ball b: balls)
        	 b.draw(g2D);

        Date date = new Date();
        String text = date.toString();
        g.drawString(text, 0, panelHeight - 10);

    }
	class FinishDialog extends JFrame
	{
	    private Container p;
	    private JDialog dialog;  //ダイアログの宣言


	    public FinishDialog() {
	        super("タイムアップ");
	        dialog = new JDialog(this , "タイムアップ" , true);   //ダイアログのタイトル

	        p = dialog.getContentPane();

	        GridBagLayout gbl = new GridBagLayout();
	        p.setLayout(gbl);
	        p.setSize(10, 10);

	        Insets insets = new Insets(0, 0, 0, 0);
	        GridBagConstraints gbc = new GridBagConstraints(0, 0, 0, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

	        Date finish_time = new Date();
	        long diff_time = finish_time.getTime() - startTime.getTime();

	        JLabel l_1 = new JLabel(diff_time/1000 + "秒", JLabel.CENTER);
	        JButton ok_btn = new JButton("OK");

	        gbl.setConstraints(l_1, gbc);
	        gbc.gridy = GridBagConstraints.RELATIVE;
	        gbl.setConstraints(ok_btn, gbc);

	        p.add(l_1);
	        p.add(ok_btn);
	        //OKが押された場合、設定を反映してダイアログを閉じる
	        ok_btn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	                dialog.setVisible(false);
	            }
	        });

	        dialog.setBounds(250,100,300,200); //表示されるダイアログの位置とサイズ
	        dialog.setVisible(true);  //ダイアログの表示
	    }
	}
}

