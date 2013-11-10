package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ObjectDialog extends Dialog implements ActionListener {

	MainFrame owner;
	Class<?> cls;
	Object obj;
	List fieldList = new List(15);
	List methodList = new List(15);
	java.util.List<Field> fieldListUtil = new ArrayList<Field>();
	java.util.List<Method> methodListtUtil = new ArrayList<Method>();
	Button field_select, method_select;

	public ObjectDialog(Object obj, Frame owner) {
		super(owner);
		this.owner = (MainFrame)owner;
		this.obj = obj;
		//WindowClosw時にリソースを開放してダイアログを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {dispose();}
		});

		//この Objectの実行時クラスを取得
		cls = obj.getClass();

		//この Objectのフィールド名とフィールドオブジェクトをリストに格納
		for (Field f : cls.getDeclaredFields()){
			f.setAccessible(true);
			fieldList.add(f.toString());
			fieldListUtil.add(f);
		}
		//この Objectのメソッド名とメソッドオブジェクトをリストに格納
		for (Method m : cls.getDeclaredMethods()){
			m.setAccessible(true);
			methodList.add(m.toString());
			methodListtUtil.add(m);
		}

		setTitle("ObjectDialog");
		setSize(1024, 768);

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;

		fieldList.setSize(800, 300);
		gbc.gridy = 0;
        gbl.setConstraints(fieldList, gbc);
		add(fieldList);

		field_select = new Button("Field 選択");
		gbc.gridy = 1;
        gbl.setConstraints(field_select, gbc);
		add(field_select);
		field_select.addActionListener(this);

		methodList.setSize(800, 300);
		gbc.gridy = 2;
        gbl.setConstraints(methodList, gbc);
		add(methodList);

		method_select = new Button("Method 選択");
		gbc.gridy = 3;
        gbl.setConstraints(method_select, gbc);
		add(method_select);
		method_select.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmdName=e.getActionCommand();
		if("Field 選択".equals(cmdName)){
			int index = fieldList.getSelectedIndex();
			new FieldDialog(fieldListUtil.get(index), obj, this);

		} else if("Method 選択".equals(cmdName)){
			int index = methodList.getSelectedIndex();
			try {
				methodListtUtil.get(index).invoke(obj, null);
			} catch (IllegalArgumentException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

			dispose();
		}
	}
}
