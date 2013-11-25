package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainFrame extends Frame implements ActionListener, ItemListener, TextListener {

	GridBagLayout gbl = new GridBagLayout();
	TextField classSelect = new TextField(70);//70列幅のテキストフィールド作成
	List classList = new List(15);
	List objectList = new List(15);
	LimitList limitClassList;
	Button classSelectButton = new Button("選択したクラスのプロパティーを表示");
	Button objecSelecrButton = new Button("選択したオブジェクトのプロパティを表示");
	Button clearButton = new Button("TextField を Clear");

	//awtのListと紐づいたObjectのリスト
	java.util.List<Object> objectListUtil = new ArrayList<Object>();

	//classリスト代入
	{
		for (String cl : ClassList.getList())
			classList.add(cl);
	}

	public MainFrame(String title) {
		super(title);//Titleの設定

		//WindowClosw時にプログラムの終了処理を実行する
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});

		setLayout(gbl);

		limitClassList = new LimitList(classList, null);

		// (0, 0) 幅=70, 高さ=1
		gbl.setConstraints(classSelect, setGBC(0, 0, 70, 1));
		add(classSelect);

		// (70, 0) 幅=1, 高さ=1
		gbl.setConstraints(classSelectButton, setGBC(70, 0, 1, 1));
		add(classSelectButton);

		// (0, 1) 幅=70, 高さ=15
		gbl.setConstraints(classList, setGBC(0, 1, 70, 15));
		add(classList);

		// (70, 1) 幅=1, 高さ=1
		gbl.setConstraints(clearButton, setGBC(70, 1, 1, 1));
		//add(clearButton);

		// (0, 16) 幅=70, 高さ=15
		gbl.setConstraints(objectList, setGBC(0, 16, 70, 15));
		add(objectList);

		// (70, 16) 幅=1, 高さ=1
		gbl.setConstraints(objecSelecrButton, setGBC(70, 16, 1, 1));
		add(objecSelecrButton);

		classSelect.addTextListener(this);
		classList.addItemListener (this);
		classSelectButton.addActionListener(this);
		objecSelecrButton.addActionListener(this);
		clearButton.addActionListener(this);

		pack();
		setVisible(true);
	}


	//GridBagConstraintsの座標系を設定する
	static public GridBagConstraints setGBC(int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		return gbc;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmdName=e.getActionCommand();
		if("選択したクラスのプロパティーを表示".equals(cmdName)){
			new ClassDialog(classSelect.getText(), this);
		} else if("選択したオブジェクトのプロパティを表示".equals(cmdName)){
			int index = objectList.getSelectedIndex();
			Object obj = objectListUtil.get(index);
			if(obj.getClass().isArray())
				new ArraytDialog(obj, this);
			else
				new ObjectDialog(obj, this);
		} else if("TextField を Clear".equals(cmdName)){
			classSelect.setText("");
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == classList)
			classSelect.setText(classList.getSelectedItem());
	}
	@Override
	public void textValueChanged(TextEvent e) {
		TextField tf = (TextField)e.getSource();

        remove(classList);
        classList = limitClassList.refresh(tf.getText());

	     // (0, 1) 幅=70, 高さ=15
		gbl.setConstraints(classList, setGBC(0, 1, 70, 15));
		add(classList);
		classList.addItemListener (this);
		setVisible(true);
	}

}
