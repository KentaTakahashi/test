package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ClassDialog extends Dialog implements ActionListener {

	MainFrame owner;
	Class<?> cls;
	List constructorList = new List(20);
	private TextField text;
	java.util.List<Constructor> constructorListUtil = new ArrayList<Constructor>();
	java.util.List<Object> argsList = new ArrayList<Object>();

	public ClassDialog(String class_str, Frame owner) {
		super(owner);
		this.owner = (MainFrame)owner;
		//WindowClosw時にリソースを開放してダイアログを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {dispose();}
		});
		try {
			cls = Class.forName(class_str);
			for (Constructor<?> cl : cls.getDeclaredConstructors()){
				cl.setAccessible(true);
				constructorList.add(cl.toString());
				constructorListUtil.add(cl);
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		setTitle("ClassDialog");
		setSize(800, 600);

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		Insets insets = new Insets(0, 0, 0, 0);
		//GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady)
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 600.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

		constructorList.setSize(800, 300);
		gbc.gridy = 0;
        gbl.setConstraints(constructorList, gbc);
		add(constructorList);

		gbc.gridy = 1;
		text = new TextField("配列の要素数選択 (int型以外の値を設定した場合、単純なオブジェクト生成を行います)");
		gbl.setConstraints(text, gbc);
		add(text);

		Button ok_btn = new Button("コンストラクター選択");
		gbc.gridy = 2;
        gbl.setConstraints(ok_btn, gbc);
		add(ok_btn);
		ok_btn.addActionListener(this);

        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//配列かどうか判定
		int array_size;
		try {
			array_size =Integer.parseInt(text.getText());
	    } catch (NumberFormatException nfex) {
	    	 array_size = -1;
	    }

		int index = constructorList.getSelectedIndex();
		Constructor<?> cl = constructorListUtil.get(index);
		cl.setAccessible(true);
		Type[] types = cl.getGenericParameterTypes();

		//コンストラクターの引数をダイアログから入力、argsListに格納
		for(Type t:types) {
			SetParameterDialog d = new SetParameterDialog(t, this, owner.getObjectList(), owner.getobjectListUtil());
			argsList.add(d.getParam());
		}

		//設定した引数でオブジェクトを作成し、ownerのオブジェクトリストに追加登録
		try {
			Object[] args = argsList.toArray();
			if(array_size == -1)
				owner.addObject(cl.newInstance(args));
			else {
				Object array = Array.newInstance(cls, array_size);
				for(int i = 0; i < array_size; i++)
					Array.set(array, i, cl.newInstance(args));
				owner.addObject(array);
			}
		} catch (InstantiationException e1) {
			new MessageDialog(e1.toString(), this);
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			new MessageDialog(e1.toString(), this);
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			new MessageDialog(e1.toString(), this);
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			new MessageDialog(e1.toString(), this);
			new MessageDialog(e1.getCause().toString(), this);
			e1.printStackTrace();
		} finally {
			dispose();
		}
	}
}
