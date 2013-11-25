package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Type;

public class SetParameterDialog extends Dialog implements ActionListener {

	Dialog owner;
	List objectList;
	java.util.List<Object> objectListUtil;
	Type type;
	TextField text = new TextField(70);//70列幅のテキストフィールド作成
	Object parm = null;
	Button ok_btn = new Button("パラメータ設定");

	GridBagLayout gbl = new GridBagLayout();

	public SetParameterDialog(Type type, Dialog owner, List objectList, java.util.List<Object> objectListUtil) {
		super(owner);
		this.owner = owner;
		this.objectList = objectList;
		this.objectListUtil = objectListUtil;
		this.type = type;

		//WindowClosw時にリソースを開放してダイアログを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {dispose();}
		});

		setTitle("SetParameterDialog");

		setLayout(gbl);

		// (0, 0) 幅=70, 高さ=1
		Label label = new Label("パラメータタイプ: " + type.toString());
		gbl.setConstraints(label, MainFrame.setGBC(0, 0, 70, 1));
		add(label);

		// (0, 1) 幅=70, 高さ=1
		gbl.setConstraints(text, MainFrame.setGBC(0, 1, 70, 1));
		add(text);

		// (0, 2) 幅=70, 高さ=1
		Label label_2 = new Label("生成済みのオブジェクトリスト");
		gbl.setConstraints(label_2, MainFrame.setGBC(0, 2, 70, 1));
		add(label_2);

		// (0, 3) 幅=70, 高さ=15
		gbl.setConstraints(objectList, MainFrame.setGBC(0, 3, 70, 15));
		add(objectList);

		// (0, 19) 幅=70, 高さ=1
		gbl.setConstraints(ok_btn, MainFrame.setGBC(0, 19, 10, 1));
		add(ok_btn);

		ok_btn.addActionListener(this);

		pack();
		setModal(true);
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmdName=e.getActionCommand();
		if("パラメータ設定".equals(cmdName)){

			String className = ((Class<?>) type).getName();

			//typeにより、パラメータを変換して格納する
			if(className.equals(String.class.getName()))
				parm = text.getText();
			else if(className.equals(boolean.class.getName()))
				parm = new Boolean(text.getText());
			else if(className.equals(byte.class.getName()))
				parm = new Byte(text.getText());
			else if(className.equals(short.class.getName()))
				parm = new Short(text.getText());
			else if(className.equals(int.class.getName()))
				parm = new Integer(text.getText());
			else if(className.equals(long.class.getName()))
				parm = new Long(text.getText());
			else if(className.equals(float.class.getName()))
				parm = new Float(text.getText());
			else if(className.equals(double.class.getName()))
				parm = new Double(text.getText());
			else if(objectList.getSelectedIndex() != -1) {
				parm = objectListUtil.get(objectList.getSelectedIndex());
			} else{
				System.out.println(type.toString() + "パラメータ設定エラー(nullを代入します)");
				parm = null;
			}
			dispose();
		}
	}

	public Object getParam() {
		return parm;
	}
}
