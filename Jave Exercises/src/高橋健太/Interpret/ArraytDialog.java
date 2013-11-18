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
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArraytDialog extends Dialog implements ActionListener {

	MainFrame owner;
	Object array;
	List objectList = new List(15);
	java.util.List<Object> objectListUtil = new ArrayList<Object>();
	Button object_select;

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
			objectList.add((Array.get(array, i).toString()));
			objectListUtil.add(Array.get(array, i));
		}
		setTitle("ArraytDialog");
		setSize(1024, 768);

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;

		objectList.setSize(800, 300);
		gbc.gridy = 0;
	    gbl.setConstraints(objectList, gbc);
		add(objectList);

		object_select = new Button("Object 選択");
		gbc.gridy = 1;
	    gbl.setConstraints(object_select, gbc);
		add(object_select);
		object_select.addActionListener(this);

		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmdName=e.getActionCommand();
		if("Object 選択".equals(cmdName)){
			int index = objectList.getSelectedIndex();
			Object obj = objectListUtil.get(index);

			if(obj.getClass().isArray())
				new ArraytDialog((Object[])obj, owner);
			else
				new ObjectDialog(obj, owner);
			//dispose();
		}
	}
}
