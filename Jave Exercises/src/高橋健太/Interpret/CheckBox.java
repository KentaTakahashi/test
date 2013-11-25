package 高橋健太.Interpret;

import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CheckBox extends Frame {

	private final String[] checkList = {
			"private final field を書き換えることが出来る",
			"自分自身を呼び出すことが出来る",
			"配列を作成できる",
			"配列の要素の参照先を変えることが出来る",
			"配列の要素として含まれるオブジェクトを操作できる",
			"メソッドの戻り値が確認できる",
			"メソッド、コンストラクタの例外処理を表示できる",
			"Frameオブジェクトを表示できる",
			"FrameオブジェクトにBottonを追加できる",
			"static field が書き換えられる(want)",
			"多次元配列を処理できる(want)",
			"",
			"検索/ソートができる(メモ)",
			"オブジェクト名がつけれる(メモ)",
			"リフレッシュボタンをつける(メモ)",
			"提出時は実行ファイルも添付する(メモ)"
			};

	CheckBox(String title) {
		super(title);

		//チェックリスト登録
		for(String s:checkList)
			add(new Checkbox(s));

		//チェックリスト分の行のあるレイアウトを作成・表示する
	    setBounds(800, 0, 0, 0);
		setLayout(new GridLayout(checkList.length, 1));
	    pack();
	    setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {setVisible(false);}
		});
	}

}
