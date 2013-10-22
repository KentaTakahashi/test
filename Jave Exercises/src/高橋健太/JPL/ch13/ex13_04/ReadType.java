package 高橋健太.JPL.ch13.ex13_04;

import java.util.ArrayList;

public class ReadType{

	public static void Read(String input) {

		ArrayList<Object> list = new ArrayList<Object>();

		int index = 0;

		while(index < input.length()) {
			//"type value"形式の行の読み出し
			int index_space = input.indexOf(" ", index);
			String type = input.substring(index, index_space);
			index = index_space + 1;//indexをシーク
			int index_LF = input.indexOf('\n', index);
			String value = input.substring(index, index_LF);
			index = index_LF + 1;//indexをシーク

			if(type.equalsIgnoreCase("Byte"))
				list.add(new Byte(value));
			else if(type.equalsIgnoreCase("Short"))
				list.add(new Short(value));
			else if(type.equalsIgnoreCase("Integer"))
				list.add(new Integer(value));
			else if(type.equalsIgnoreCase("Long"))
				list.add(new Long(value));
			else if(type.equalsIgnoreCase("Float"))
				list.add(new Float(value));
			else if(type.equalsIgnoreCase("Double"))
				list.add(new Double(value));
			else if(type.equalsIgnoreCase("Boolean"))
				list.add(new Boolean(value));
			else if(type.equalsIgnoreCase("Character")) {
				//value が一文字の時のみリストに追加
				if(value.length() == 1) list.add(new Character(value.charAt(0)));
			} else {
				//どのTypeとも一致しない場合、何もせず行を読み飛ばす
			}
		}

		//list表示、value(type)の形式で表示する
		System.out.println("value" + "\t(type)");
		System.out.println("--------------------------");
		for(Object obj: list) {
			Class<? extends Object> c = obj.getClass();
			System.out.println( obj.toString() + "\t(" + c.getName() + ")");
		}

	}

	public static void main(String[] args) {
		String test =
				"Byte 1\n" +
				"Short -10\n" +
				"Integer 100\n" +
				"Long -1000\n" +
				"Float 1.0e3\n" +
				"Double -1.0e2\n" +
				"Boolean TRUE\n" +
				"Character c\n";

		Read(test);
	}
}
