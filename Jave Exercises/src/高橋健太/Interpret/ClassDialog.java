package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ClassDialog extends Dialog implements ActionListener {

	MainFrame owner;
	Class<?> cls;
	List constructorList = new List(20);
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

		Button ok_btn = new Button("コンストラクター選択");
		gbc.gridy = 1;
        gbl.setConstraints(ok_btn, gbc);
		add(ok_btn);
		ok_btn.addActionListener(this);

        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = constructorList.getSelectedIndex();
		Constructor<?> cl = constructorListUtil.get(index);
		Type[] types = cl.getGenericParameterTypes();

		System.out.println(types.length);

		//コンストラクターの引数をダイアログから入力、argsListに格納
		for(Type t:types) {
			SetParameterDialog d = new SetParameterDialog(t, this, owner.getObjectList(), owner.getobjectListUtil());
			argsList.add(d.getParam());
		}

		//設定した引数でオブジェクトを作成し、ownerのオブジェクトリストに追加登録
		try {
			Object[] args = argsList.toArray();
			owner.addObject(cl.newInstance(args));
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} finally {
			dispose();
		}
	}
}
