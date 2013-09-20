package 高橋健太.JPL.ch10.ex10_01;


public class ConvertCharacter {

	static String convert(String str) {
		String ret = "";

		for(int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);
			if(tmp == '\n')      ret += "\\n";
			else if(tmp == '\t') ret += "\\t";
			else if(tmp == '\b') ret += "\\b";
			else if(tmp == '\r') ret += "\\r";
			else if(tmp == '\f') ret += "\\f";
			else if(tmp == '\\') ret += "\\\\";
			else if(tmp == '\'') ret += "\\'";
			else if(tmp == '\"') ret += "\\\"";
			else                 ret += tmp;
		}

		return ret;
	}

	public static void main(String[] args) {
		String str = "test \n \t \b \r \f \\ \' \" ";
		System.out.println(convert(str));
	}
}
