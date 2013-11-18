package 高橋健太.Interpret;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MessageDialog extends Dialog implements ActionListener {

	public MessageDialog(String message, Dialog owner) {
		super(owner);

		//WindowClosw時にリソースを開放してダイアログを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {dispose();}
		});

		setTitle("MessageDialog");
		setSize(400, 100);

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		Insets insets = new Insets(0, 0, 0, 0);
		//GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady)
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 600.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

		gbc.gridy = 0;
		Label label = new Label(message);
        gbl.setConstraints(label, gbc);
        add(label);

		gbc.gridy = 1;
		Button ok_btn = new Button("OK");
        gbl.setConstraints(ok_btn, gbc);
		add(ok_btn);
		ok_btn.addActionListener(this);

		setModal(true);
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
