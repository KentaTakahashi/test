package 高橋健太.JPL.ch10.ex10_02;


public class ConvertCharacter {

	static String convert(String str) {
		String ret = "";

		for(int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);
			switch(tmp) {
			case '\n':
				ret += "\\n";
				break;
			case '\t':
				ret += "\\t";
				break;
			case '\b':
				ret += "\\b";
				break;
			case '\r':
				ret += "\\r";
				break;
			case '\f':
				ret += "\\f";
				break;
			case '\\':
				ret += "\\\\";
				break;
			case '\'':
				ret += "\\'";
				break;
			case '\"':
				ret += "\\\"";
				break;
			default:
				ret += tmp;
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		String str = "test \n \t \b \r \f \\ \' \" ";
		System.out.println(convert(str));
	}
}
