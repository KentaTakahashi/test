package 高橋健太.Interpret;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class FieldDialog extends Dialog implements ActionListener {

	private Dialog owner;
	private Object obj;

	public FieldDialog(Field field, Object obj, Dialog owner) {
		super(owner);
		this.owner = owner;
		this.obj = obj;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
