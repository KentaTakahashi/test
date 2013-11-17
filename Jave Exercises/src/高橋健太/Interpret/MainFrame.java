package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainFrame extends Frame implements ActionListener{

	Button bt1,bt2;
	List classList = new List(15);
	List objectList = new List(15);
	java.util.List<Object> objectListUtil = new ArrayList<Object>();

	public MainFrame(String str) {

		super(str);//Titleの設定

		//WindowClosw時にプログラムの終了処理を実行する
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});

		setSize(800, 600);
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		Insets insets = new Insets(0, 0, 0, 0);
		//GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady)
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 800.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

		classList.setSize(800, 200);
		objectList.setSize(800, 200);
		for (String cl : ClassList.getList()) classList.add(cl);
		bt1 = new Button("選択したクラスのプロパティーを表示");
		bt2 = new Button("選択したオブジェクトのプロパティを表示");

		gbc.gridy = 0;
		gbl.setConstraints(classList, gbc);
		add(classList);
		gbc.gridy = 1;
		gbl.setConstraints(bt1, gbc);
		add(bt1);
		gbc.gridy = 2;
		gbl.setConstraints(objectList, gbc);
		add(objectList);
		gbc.gridy = 3;
		gbl.setConstraints(bt2, gbc);
		add(bt2);
		bt1.addActionListener(this);
		bt2.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmdName=e.getActionCommand();
		if("選択したクラスのプロパティーを表示".equals(cmdName)){
			new ClassDialog(classList.getSelectedItem(), this);
		} else if("選択したオブジェクトのプロパティを表示".equals(cmdName)){
			int index = objectList.getSelectedIndex();
			new ObjectDialog(objectListUtil.get(index), this);
		}
	}

	public void addObject(Object obj) {
		objectListUtil.add(obj);
		objectList.add(obj.toString());
	}
	public List getObjectList() {
		List copyList = new List(15);
		for(int i = 0;i < objectList.getItemCount(); i++ )
			copyList.add(objectList.getItem(i));
		return copyList;
	}
	public java.util.List<Object> getobjectListUtil() {
		return objectListUtil;
	}
}
