package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MessageDialog extends Dialog implements ActionListener {

	GridBagLayout gbl = new GridBagLayout();

	public MessageDialog(String message, Dialog owner) {
		super(owner);

		//WindowClosw時にリソースを開放してダイアログを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {dispose();}
		});

		setTitle("MessageDialog");

		setBounds(200, 200, 0, 0);
		setLayout(gbl);

		// (0, 0) 幅=50, 高さ=1
		Label label = new Label(message);
		gbl.setConstraints(label, MainFrame.setGBC(0, 0, 50, 1));
		add(label);

		// (0, 1) 幅=50, 高さ=1
		Button ok_btn = new Button("OK");
		gbl.setConstraints(ok_btn, MainFrame.setGBC(0, 1, 50, 1));
		add(ok_btn);

		ok_btn.addActionListener(this);

		pack();
		setModal(true);
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
