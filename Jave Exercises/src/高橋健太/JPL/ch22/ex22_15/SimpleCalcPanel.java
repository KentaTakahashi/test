package 高橋健太.JPL.ch22.ex22_15;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SimpleCalcPanel extends JPanel implements ActionListener{
	JButton []btnNumb = new JButton[11];// 数字用
	JButton []btnOp = new JButton[6];	//オペレーション用ボタン
	JLabel lbl1 = new JLabel("0");
	JTextField txt1 = new JTextField("");

	public SimpleCalcPanel(){
		for(int i = 0; i  < btnNumb.length-1; i++){
			this.btnNumb[i] = new JButton("" + i);//数字表示ボタン生成
			this.add(this.btnNumb[i]);
		}
		this.btnNumb[10] = new JButton(".");
		this.add(this.btnNumb[10]);

		btnOp[0] = new JButton("+/-");
		btnOp[1] = new JButton("/");
		btnOp[2] = new JButton("*");
		btnOp[3] = new JButton("-");
		btnOp[4] = new JButton("+");
		btnOp[5] = new JButton("=");
		for(int i = 0; i  < btnOp.length; i++){
			this.add(this.btnOp[i]);//オペレーションボタン追加
		}
		this.add(this.lbl1);
		this.lbl1.setBackground(new Color(255, 255, 100));//ラベルの背景色設定
		this.lbl1.setOpaque(true);//ラベルを「不透明な」設定にします。
		this.lbl1.setHorizontalAlignment(SwingConstants.RIGHT);//右よせで文字列を表示
		this.lbl1.setFont(new Font(null, Font.BOLD, 24));

		this.add(this.txt1);
		this.txt1.setHorizontalAlignment(SwingConstants.RIGHT);//右よせで文字列を表示
		this.txt1.setFont(new Font(null, Font.BOLD, 24));
	}

	public void actionPerformed(ActionEvent e){
		//キー入力用ボタン処理記述予定
	}
}