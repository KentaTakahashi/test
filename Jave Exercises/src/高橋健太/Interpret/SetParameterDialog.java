package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Type;

public class SetParameterDialog extends Dialog implements ActionListener {

	private Dialog owner;
	List objectList;
	java.util.List<Object> objectListUtil;
	private Type type;
	private TextField text;
	private Object parm = null;

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
		setSize(800, 600);

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		Insets insets = new Insets(0, 0, 0, 0);
		//GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady)
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 600.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);


		gbc.gridy = 0;
		Label label = new Label("パラメータタイプ: " + type.toString());
        gbl.setConstraints(label, gbc);
        add(label);

		gbc.gridy = 1;
		text = new TextField();
		gbl.setConstraints(text, gbc);
		add(text);

		gbc.gridy = 2;
		Label label_2 = new Label("生成済みのオブジェクトリスト");
        gbl.setConstraints(label_2, gbc);
        add(label_2);

        gbc.gridy = 3;
        gbl.setConstraints(objectList, gbc);
        add(objectList);

		gbc.gridy = 4;
		Button ok_btn = new Button("パラメータ設定");
        gbl.setConstraints(ok_btn, gbc);
		add(ok_btn);
		ok_btn.addActionListener(this);

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
