package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ObjectDialog extends Dialog implements ActionListener, TextListener {

	MainFrame owner;
	Class<?> cls;
	Object obj;
	List constructorList = new List(15);
	List fieldList = new List(15);
	List methodList = new List(15);
	java.util.List<Field> fieldListUtil = new ArrayList<Field>();
	java.util.List<Method> methodListUtil = new ArrayList<Method>();
	LimitList limitFieldList;
	LimitList limitMethodList;
	Button fieldGetButton = new Button("field 表示");
	Button fieldSetButton = new Button("field 設定");
	Button methodSelectButton = new Button("method 実行");
	TextField fieldSelect = new TextField(150);//150列幅のテキストフィールド作成
	TextField methodSelect = new TextField(150);//150列幅のテキストフィールド作成

	GridBagLayout gbl = new GridBagLayout();

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

		Class<?> c = cls;
		while(c != null) {
			//この Objectと継承元のフィールド名とフィールドオブジェクトをリストに格納
			for (Field f : c.getDeclaredFields()){
				f.setAccessible(true);
				if(f.toString().indexOf("static") == -1 && !fieldListUtil.contains(f.toString())) {
					fieldList.add(f.toString());
					fieldListUtil.add(f);
				}
			}
			//この Objectと継承元のメソッド名とメソッドドオブジェクトをリストに格納
			for (Method m : c.getDeclaredMethods()){
				m.setAccessible(true);
				if(m.toString().indexOf("static") == -1 && !methodListUtil.contains(m.toString())) {
					methodList.add(m.toString());
					methodListUtil.add(m);
				}
			}
			c = c.getSuperclass();
		}

		setTitle("ObjectDialog");

		setBounds(100, 100, 0, 0);
		setLayout(gbl);

		limitFieldList = new LimitList(fieldList, fieldListUtil);
		limitMethodList = new LimitList(methodList, methodListUtil);

		/************* Field Layout登録 **************/
		// (0, 0) 幅=70, 高さ=1
		gbl.setConstraints(fieldSelect, MainFrame.setGBC(0, 0, 70, 1));
		add(fieldSelect);

		// (70, 0) 幅=1, 高さ=1
		gbl.setConstraints(fieldGetButton, MainFrame.setGBC(70, 0, 1, 1));
		add(fieldGetButton);

		// (0, 1) 幅=70, 高さ=15
		gbl.setConstraints(fieldList, MainFrame.setGBC(0, 1, 70, 15));
		add(fieldList);

		// (70, 1) 幅=1, 高さ=1
		gbl.setConstraints(fieldSetButton, MainFrame.setGBC(70, 1, 1, 1));
		add(fieldSetButton);

		/************* Method Layout登録 **************/
		// (0, 16) 幅=70, 高さ=1
		gbl.setConstraints(methodSelect, MainFrame.setGBC(0, 16, 70, 1));
		add(methodSelect);

		// (70, 16) 幅=1, 高さ=1
		gbl.setConstraints(methodSelectButton, MainFrame.setGBC(70, 16, 1, 1));
		add(methodSelectButton);

		// (0, 17) 幅=70, 高さ=15
		gbl.setConstraints(methodList, MainFrame.setGBC(0, 17, 70, 15));
		add(methodList);

		/************* Listener登録 **************/
		fieldSelect.addTextListener(this);
		methodSelect.addTextListener(this);
		fieldGetButton.addActionListener(this);
		fieldSetButton.addActionListener(this);
		methodSelectButton.addActionListener(this);

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmdName=e.getActionCommand();
		if ("field 表示".equals(cmdName)){
			getField();
		} else if("field 設定".equals(cmdName)){
			setField();
		} else if("method 実行".equals(cmdName)){
			executeMethod();
		}
	}
	private void getField() {
		try {
			int index = fieldList.getSelectedIndex();
			Field f = (Field)limitFieldList.getObject(index);
			f.setAccessible(true);
			new MessageDialog(f.get(obj).toString(), this);
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
	private void setField() {
		try {
			int index = fieldList.getSelectedIndex();
			Field f = (Field)limitFieldList.getObject(index);
			f.setAccessible(true);

			Class<?> fieldCls = f.getType();
			SetParameterDialog d = new SetParameterDialog(fieldCls, this, owner.getObjectList(), owner.getobjectListUtil());

			//書き込み対象FieldのModifierを取得し、final修飾子を取り外す
			f.setAccessible(true);
			int modifiers = f.getModifiers();
			Field modifierField = f.getClass().getDeclaredField("modifiers");
			modifiers = modifiers & ~Modifier.FINAL;
			modifierField.setAccessible(true);
			modifierField.setInt(f, modifiers);

			f.set(obj, d.getParam());

		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	private void executeMethod() {
		try {
			int index = methodList.getSelectedIndex();
			Method m = (Method)limitMethodList.getObject(index);
			Type[] types = m.getParameterTypes();
			//メドッドの引数をダイアログから入力、argsListに格納
			java.util.List<Object> argsList = new ArrayList<Object>();
			for(Type t:types) {
				SetParameterDialog d = new SetParameterDialog(t, this, owner.getObjectList(), owner.getobjectListUtil());
				argsList.add(d.getParam());
			}
			Object[] args = argsList.toArray();

			Object ret = m.invoke(obj, args);
			if(ret != null) {
				new MessageDialog(ret.toString(), this);
				owner.addObject(ret);
			}

		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			new MessageDialog(e1.getCause().toString(), this);
			e1.printStackTrace();
		}
	}
	@Override
	public void textValueChanged(TextEvent e) {
		TextField tf = (TextField)e.getSource();

		if (tf == fieldSelect) {
			remove(fieldList);
			fieldList = limitFieldList.refresh(tf.getText());
			// (0, 1) 幅=70, 高さ=15
			gbl.setConstraints(fieldList, MainFrame.setGBC(0, 1, 70, 15));
			add(fieldList);
		} else if (tf == methodSelect) {
			remove(methodList);
			methodList = limitMethodList.refresh(tf.getText());
			// (0, 17) 幅=70, 高さ=15
			gbl.setConstraints(methodList, MainFrame.setGBC(0, 17, 70, 15));
			add(methodList);
		}
		setVisible(true);
	}
}
