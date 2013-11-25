package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArraytDialog extends Dialog implements ActionListener {

	MainFrame owner;
	Object array;
	List objectList = new List(15);
	java.util.List<Object> objectListUtil = new ArrayList<Object>();
	Button arrayGetButton = new Button("array要素表示");
	Button arraySetButton = new Button("array要素設定");

	GridBagLayout gbl = new GridBagLayout();

	public ArraytDialog(Object array, Frame owner) {
		super(owner);
		this.owner = (MainFrame)owner;
		this.array = array;
		//WindowClosw時にリソースを開放してダイアログを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {dispose();}
		});

		//この Objectの実行時クラスを取得
		for(int i = 0; i < Array.getLength(array); i++) {
			Object o = Array.get(array, i);
			if(o !=null)
				objectList.add(o.toString());
			else
				objectList.add("null");
			objectListUtil.add(o);
		}
		setTitle("ArraytDialog");

		setBounds(100, 100, 0, 0);
		setLayout(gbl);

		// (0, 0) 幅=70, 高さ=15
		gbl.setConstraints(objectList, MainFrame.setGBC(0, 0, 70, 15));
		add(objectList);

		// (70, 0) 幅=1, 高さ=1
		gbl.setConstraints(arrayGetButton, MainFrame.setGBC(70, 0, 1, 1));
		add(arrayGetButton);

		// (70, 1) 幅=1, 高さ=1
		gbl.setConstraints(arraySetButton, MainFrame.setGBC(70, 1, 1, 1));
		add(arraySetButton);

		/************* Listener登録 **************/
		arrayGetButton.addActionListener(this);
		arraySetButton.addActionListener(this);

		pack();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmdName=e.getActionCommand();
		if ("array要素表示".equals(cmdName)) {
			getArray‎Element();
		} else if("array要素設定".equals(cmdName)) {
			setArray‎Element();
		}
	}
	private void getArray‎Element() {
		int index = objectList.getSelectedIndex();
		Object obj = objectListUtil.get(index);

		if(obj.getClass().isArray())
			new ArraytDialog((Object[])obj, owner);
		else
			new ObjectDialog(obj, owner);
	}
	private void setArray‎Element() {
		int index = objectList.getSelectedIndex();
		SetParameterDialog d = new SetParameterDialog(array.getClass(), this, owner.getObjectList(), owner.getobjectListUtil());
		Array.set(array, index, d.getParam());

		remove(objectList);
		// (0, 0) 幅=70, 高さ=15
		gbl.setConstraints(objectList, MainFrame.setGBC(0, 0, 70, 15));
		add(objectList);
		pack();
		setVisible(true);
	}
}
