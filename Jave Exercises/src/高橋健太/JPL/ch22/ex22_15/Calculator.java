package 高橋健太.JPL.ch22.ex22_15;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
public class Calculator extends JFrame {
	Container container = getContentPane();
	SimpleCalcPanel panel = new NomalCalcPanel();

	public Calculator() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("SimpleCalculator");

		this.container.add( panel, BorderLayout.CENTER);

		//this.setResizable(false);//【1】ユーザがこのフレームのサイズを変更できなく設定します。

		this.setBounds(0, 0, 250, 250);//フレームサイズ指定
		this.setVisible(true);

		this.setAlwaysOnTop(true);//ほかのすべてのウィンドウの手前に表示されるように、最前面ウィンドウへ設定します
	}

	public static void main(String[] args) {
		JFrame f = new Calculator();
	}
}