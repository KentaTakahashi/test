package 高橋健太.JPL.ch22.ex22_15;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Calculator extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel contentPane = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JTextField result = new JTextField(""); //計算結果を表示するテキストフィールド
	double stackedValue = 0.0; //演算子ボタンを押す前にテキストフィールドにあった値
	boolean isStacked = false; //stackedValueに数値を入力したかどうか
	boolean afterCalc = false; //演算子ボタンを押した後かどうか
	String currentOp = ""; //押された演算子ボタンの名前

	boolean isInverse = false;//valueの符号は負かどうか

	enum ExtraFunc {ABS, ACOS, ASIN, ATAN, ATAN2, CBRT, CEIL, COPYSIGN, COS, COSH, EXP, EXPM1, FLOOR, GETEXPONENT,
		HYPOT, IEEEREMAINDER, LOG, LOG10, LOG1P, MAX, MIN, NEXTAFTER, NEXTUP, POW, RANDOM, RINT, ROUND, SCALB,
		SIGNUM, SIN, SINH, SQRT, TAN, TANH, TODEGREES, TORADIANS, ULP}
	ExtraFunc extraFunc;

	CalcButton EqualButton = new CalcButton("＝");
	CalcButton ExtraButton = new CalcButton("imput next extra func value");

	double extraValue_1;
	double extraValue_2;
	boolean isExtra_1 = false;
	boolean isExtra_2 = false;

	//フレームのビルド
	public Calculator() {
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(300, 350));
		this.setTitle("電子式卓上計算機");
		this.setContentPane(contentPane);

		contentPane.add(result, BorderLayout.NORTH); //テキストフィールドを配置

		JPanel keyPanel = new JPanel(); //ボタンを配置するパネルを用意
		keyPanel.setLayout(new GridLayout(4, 4)); //4行4列のGridLayoutにする
		contentPane.add(keyPanel, BorderLayout.CENTER);

		keyPanel.add(new NumberButton("7"), 0); //ボタンをレイアウトにはめこんでいく
		keyPanel.add(new NumberButton("8"), 1);
		keyPanel.add(new NumberButton("9"), 2);
		keyPanel.add(new CalcButton("＋"), 3);
		keyPanel.add(new NumberButton("4"), 4);
		keyPanel.add(new NumberButton("5"), 5);
		keyPanel.add(new NumberButton("6"), 6);
		keyPanel.add(new CalcButton("－"), 7);
		keyPanel.add(new NumberButton("1"), 8);
		keyPanel.add(new NumberButton("2"), 9);
		keyPanel.add(new NumberButton("3"), 10);
		keyPanel.add(new CalcButton("×"), 11);
		keyPanel.add(new NumberButton("0"), 12);
		keyPanel.add(new NumberButton("."), 13);
		keyPanel.add(new CalcButton("％"), 14);
		keyPanel.add(new CalcButton("÷"), 15);

		contentPane.add(EqualButton, BorderLayout.SOUTH);//"="ボタンを配置する
		contentPane.add(new ClearButton(), BorderLayout.WEST);//Cボタンを配置する


		JMenuBar menubar = new JMenuBar();

	    JMenu menu = new JMenu("Extra Function");

	    menubar.add(menu);

	    menu.add(new FuncMenuItem("ABS"));
	    menu.add(new FuncMenuItem("ACOS"));
	    menu.add(new FuncMenuItem("ASIN"));
	    menu.add(new FuncMenuItem("ATAN"));
	    menu.add(new FuncMenuItem("ATAN2"));
	    menu.add(new FuncMenuItem("CBRT"));
	    menu.add(new FuncMenuItem("CEIL"));
	    menu.add(new FuncMenuItem("COPYSIGN"));
	    menu.add(new FuncMenuItem("COS"));
	    menu.add(new FuncMenuItem("COSH"));
	    menu.add(new FuncMenuItem("EXP"));
	    menu.add(new FuncMenuItem("EXPM1"));
	    menu.add(new FuncMenuItem("FLOOR"));
	    menu.add(new FuncMenuItem("GETEXPONENT"));
	    menu.add(new FuncMenuItem("HYPOT"));
	    menu.add(new FuncMenuItem("IEEEREMAINDER"));
	    menu.add(new FuncMenuItem("LOG"));
	    menu.add(new FuncMenuItem("LOG10"));
	    menu.add(new FuncMenuItem("LOG1P"));
	    menu.add(new FuncMenuItem("MAX"));
	    menu.add(new FuncMenuItem("MIN"));
	    menu.add(new FuncMenuItem("NEXTAFTER"));
	    menu.add(new FuncMenuItem("NEXTUP"));
	    menu.add(new FuncMenuItem("POW"));
	    menu.add(new FuncMenuItem("RANDOM"));
	    menu.add(new FuncMenuItem("RINT"));
	    menu.add(new FuncMenuItem("ROUND"));
	    menu.add(new FuncMenuItem("SCALB"));
	    menu.add(new FuncMenuItem("SIGNUM"));
	    menu.add(new FuncMenuItem("SIN"));
	    menu.add(new FuncMenuItem("SINH"));
	    menu.add(new FuncMenuItem("SQRT"));
	    menu.add(new FuncMenuItem("TAN"));
	    menu.add(new FuncMenuItem("TANH"));
	    menu.add(new FuncMenuItem("TODEGREES"));
	    menu.add(new FuncMenuItem("TORADIANS"));
	    menu.add(new FuncMenuItem("ULP"));

	    setJMenuBar(menubar);

		this.setVisible(true);
	}

	/* テキストフィールドに引数の文字列をつなげる */
	public void appendResult(String c) {
		if (!afterCalc) //演算子ボタンを押した直後でないなら
			result.setText(result.getText() + c); //押したボタンの名前をつなげる
		else {
			result.setText(c); //押したボタンの文字列だけを設定する（いったんクリアしたかに見える）
			afterCalc = false;
		}
	}

	/* 数字を入力するボタンの定義 */
	public class NumberButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public NumberButton(String keyTop) {
			super(keyTop); //JButtonクラスのコンストラクタを呼び出す
			this.addActionListener(this); //このボタンにアクションイベントのリスナを設定
		}

		public void actionPerformed(ActionEvent evt) {
			String keyNumber = this.getText(); //ボタンの名前を取り出す
			appendResult(keyNumber); //ボタンの名前をテキストフィールドにつなげる
		}
	}

	/* 演算子ボタンを定義 */
	public class CalcButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public CalcButton(String op) {
			super(op);
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if (result.getText().length() == 0 && this.getText().equals("－")) {
				appendResult("-");
				return;
			}

			if(isExtra_1) {
				double resultValue = (Double.valueOf(result.getText()));
				if (this.getText().equals("imput next extra func value")) {
					extraValue_1 = resultValue;
					isExtra_1 = false;
					isExtra_2 = true;
					changeEqualButton();

					stackedValue = 0.0;
					afterCalc = false;
					isStacked = false;
					result.setText("");
				}
				if (this.getText().equals("＝")) {
					extraValue_1 = resultValue;
					isExtra_1 = false;
					double d = callExtraFunc();
					result.setText(Double.toString(d));
				}
				return;
			}
			if(isExtra_2) {
				double resultValue = (Double.valueOf(result.getText()));
				if (this.getText().equals("＝")) {
					extraValue_2 = resultValue;
					isExtra_2 = false;
					double d = callExtraFunc();
					result.setText(Double.toString(d));
				}
				return;
			}

			if (isStacked) { //以前に演算子ボタンが押されたのなら計算結果を出す
				double resultValue = (Double.valueOf(result.getText()))
						.doubleValue();
				if (currentOp.equals("＋")) //演算子に応じて計算する
					stackedValue += resultValue;
				else if (currentOp.equals("－"))
					stackedValue -= resultValue;
				else if (currentOp.equals("×"))
					stackedValue *= resultValue;
				else if (currentOp.equals("÷"))
					stackedValue /= resultValue;
				else if (currentOp.equals("％"))
					stackedValue %= resultValue;
				result.setText(String.valueOf(stackedValue)); //計算結果をテキストフィールドに設定
			}
			currentOp = this.getText(); //ボタン名から押されたボタンの演算子を取り出す
			stackedValue = (Double.valueOf(result.getText())).doubleValue();
			afterCalc = true;
			if (currentOp.equals("＝"))
				isStacked = false;
			else
				isStacked = true;
		}
	}

	/* クリアボタンの定義 */
	public class ClearButton extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public ClearButton() {
			super("C");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			stackedValue = 0.0;
			afterCalc = false;
			isStacked = false;
			result.setText("");

			isExtra_1 = false;
			isExtra_2 = false;
		}
	}


	public class FuncMenuItem extends JMenuItem implements ActionListener {

		public FuncMenuItem(String func) {
			super(func);
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {

			stackedValue = 0.0;
			afterCalc = false;
			isStacked = false;
			result.setText("");

			String func = this.getText(); //アイテム名から押されたアイテムの関数を取り出す
			if (func.equals("ABS")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.ABS;
			} else if(func.equals("ACOS")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.ACOS;
			} else if(func.equals("ASIN")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.ASIN;
			} else if(func.equals("ATAN")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.ATAN;
			} else if(func.equals("ATAN2")) {
				inputExtraValue(2);
				extraFunc = ExtraFunc.ATAN2;
			} else if(func.equals("CBRT")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.CBRT;
			} else if(func.equals("CEIL")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.CEIL;
			} else if(func.equals("COPYSIGN")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.COPYSIGN;
			} else if(func.equals("COS")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.COS;
			} else if(func.equals("COSH")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.COSH;
			} else if(func.equals("EXP")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.EXP;
			} else if(func.equals("EXPM1")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.EXPM1;
			} else if(func.equals("FLOOR")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.FLOOR;
			} else if(func.equals("GETEXPONENT")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.GETEXPONENT;
			} else if(func.equals("HYPOT")) {
				inputExtraValue(2);
				extraFunc = ExtraFunc.HYPOT;
			} else if(func.equals("IEEEREMAINDER")) {
				inputExtraValue(2);
				extraFunc = ExtraFunc.IEEEREMAINDER;
			} else if(func.equals("LOG")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.LOG;
			} else if(func.equals("LOG10")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.LOG10;
			} else if(func.equals("LOG1P")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.LOG1P;
			} else if(func.equals("MAX")) {
				inputExtraValue(2);
				extraFunc = ExtraFunc.MAX;
			} else if(func.equals("MIN")) {
				inputExtraValue(2);
				extraFunc = ExtraFunc.MIN;
			} else if(func.equals("NEXTAFTER")) {
				inputExtraValue(2);
				extraFunc = ExtraFunc.NEXTAFTER;
			} else if(func.equals("NEXTUP")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.NEXTUP;
			} else if(func.equals("POW")) {
				inputExtraValue(2);
				extraFunc = ExtraFunc.POW;
			} else if(func.equals("RANDOM")) {
				//inputExtraValue(1);
				extraFunc = ExtraFunc.RANDOM;
				double d = callExtraFunc();
				result.setText(Double.toString(d));
			} else if(func.equals("RINT")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.RINT;
			} else if(func.equals("ROUND")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.ROUND;
			} else if(func.equals("SCALB")) {
				inputExtraValue(2);
				extraFunc = ExtraFunc.SCALB;
			} else if(func.equals("SIGNUM")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.SIGNUM;
			} else if(func.equals("SIN")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.SIN;
			} else if(func.equals("SINH")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.SINH;
			} else if(func.equals("SQRT")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.SQRT;
			} else if(func.equals("TAN")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.TAN;
			} else if(func.equals("TANH")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.TANH;
			} else if(func.equals("TODEGREES")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.TODEGREES;
			} else if(func.equals("TORADIANS")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.TORADIANS;
			} else if(func.equals("ULP")) {
				inputExtraValue(1);
				extraFunc = ExtraFunc.ULP;
			} else {
				try {
					throw new Exception();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		}

	}

	public void inputExtraValue(int num) {
		if(num == 1)
			isExtra_1 = true;
		if(num == 2) {
			isExtra_1 = true;
			isExtra_2 = true;
			changeEqualButton();
		}
	}

	//"＝"ボタンと"imput next extra func value"ボタンのチェンジ
	public void changeEqualButton() {
		if(isExtra_1) {
			contentPane.remove(EqualButton);
			ExtraButton = new CalcButton("imput next extra func value");
			contentPane.add(ExtraButton, BorderLayout.SOUTH);
			contentPane.revalidate();
		} else {
			contentPane.remove(ExtraButton);
			EqualButton = new CalcButton("＝");
			contentPane.add(EqualButton, BorderLayout.SOUTH);
			contentPane.revalidate();
		}
	}
	public double callExtraFunc() {

		double ret = 0.0;
		switch (extraFunc) {
		case ABS:
			ret = Math.abs(extraValue_1);
			break;
		case ACOS:
			ret = Math.acos(extraValue_1);
			break;
		case ASIN:
			ret = Math.asin(extraValue_1);
			break;
		case ATAN:
			ret = Math.atan(extraValue_1);
			break;
		case ATAN2:
			ret = Math.atan2(extraValue_1, extraValue_2);
			break;
		case CBRT:
			ret = Math.cbrt(extraValue_1);
			break;
		case CEIL:
			ret = Math.ceil(extraValue_1);
			break;
		case COPYSIGN:
			ret = Math.copySign(extraValue_1, extraValue_2);
			break;
		case COS:
			ret = Math.cos(extraValue_1);
			break;
		case COSH:
			ret = Math.cosh(extraValue_1);
			break;
		case EXP:
			ret = Math.exp(extraValue_1);
			break;
		case EXPM1:
			ret = Math.expm1(extraValue_1);
			break;
		case FLOOR:
			ret = Math.floor(extraValue_1);
			break;
		case GETEXPONENT:
			ret = Math.getExponent(extraValue_1);
			break;
		case HYPOT:
			ret = Math.hypot(extraValue_1, extraValue_2);
			break;
		case IEEEREMAINDER:
			ret = Math.IEEEremainder(extraValue_1, extraValue_2);
			break;
		case LOG:
			ret = Math.log(extraValue_1);
			break;
		case LOG10:
			ret = Math.log10(extraValue_1);
			break;
		case LOG1P:
			ret = Math.log1p(extraValue_1);
			break;
		case MAX:
			ret = Math.max(extraValue_1, extraValue_2);
			break;
		case MIN:
			ret = Math.min(extraValue_1, extraValue_2);
			break;
		case NEXTAFTER:
			ret = Math.nextAfter(extraValue_1, extraValue_2);
			break;
		case NEXTUP:
			ret = Math.nextUp(extraValue_1);
			break;
		case POW:
			ret = Math.pow(extraValue_1, extraValue_2);
			break;
		case RANDOM:
			ret = Math.random();
			break;
		case RINT:
			ret = Math.rint(extraValue_1);
			break;
		case ROUND:
			ret = Math.round(extraValue_1);
			break;
		case SCALB:
			ret = Math.scalb(extraValue_1, (int)extraValue_2);
			break;
		case SIGNUM:
			ret = Math.signum(extraValue_1);
			break;
		case SIN:
			ret = Math.sin(extraValue_1);
			break;
		case SINH:
			ret = Math.sinh(extraValue_1);
			break;
		case SQRT:
			ret = Math.sqrt(extraValue_1);
			break;
		case TAN:
			ret = Math.tan(extraValue_1);
			break;
		case TANH:
			ret = Math.tanh(extraValue_1);
			break;
		case TODEGREES:
			ret = Math.toDegrees(extraValue_1);
			break;
		case TORADIANS:
			ret = Math.toRadians(extraValue_1);
			break;
		case ULP:
			ret = Math.ulp(extraValue_1);
			break;
		default:
			try {
				throw new Exception();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		}
		stackedValue = ret;
		return ret;
	}

	public static void main(String[] args) {
		new Calculator();
	}
}