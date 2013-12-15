package 高橋健太.JPL.ch20.ex20_06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;


public class OpeInput{

	enum State {INIT, NAME_INUPUTED, OPE_INUPUTED}
	StreamTokenizer st;

	public OpeInput(Reader in) {
		st = new StreamTokenizer(in);
		st.ordinaryChar('=');
	}


	public void operate() throws IOException {
		double result[] = new double[3];
		State state = State.INIT;
		int index = 0;
		int ope = 0;
		for (int tokenType = st.nextToken();tokenType != StreamTokenizer.TT_EOF; tokenType = st.nextToken()) {
			switch (state) {
			case INIT://行の最初のワード
				index = -1;

				if(st.ttype == StreamTokenizer.TT_WORD) {
					if(st.sval.equals("APPLE")) index = 0;
					if(st.sval.equals("BERRY")) index = 1;
					if(st.sval.equals("CAKE")) index = 2;
				}
				if(index != -1)
					state = State.NAME_INUPUTED;
				break;
			case NAME_INUPUTED://name入力済み
				ope = -1;

				if(tokenType == '+') ope = 0;
				if(tokenType == '-') ope = 1;
				if(tokenType == '=') ope = 2;

				if(ope != -1)
					state = State.OPE_INUPUTED;
				break;
			case OPE_INUPUTED://op入力済み
				if(st.ttype == StreamTokenizer.TT_NUMBER) {
					if(ope == 0) result[index] += st.nval;
					if(ope == 1) result[index] -= st.nval;
					if(ope == 2) result[index]  = st.nval;
				}
				state = State.INIT;//演算実施有無にかかわらず、現在の行の処理を終了
				break;
			default:
				break;
			}
		}
		System.out.println("APPLE = " + result[0]);
		System.out.println("BERRY = " + result[1]);
		System.out.println("CAKE  = " + result[2]);
	}

	public static void main(String[] args) {

		String current_pass = new File("").getAbsolutePath();
		//System.out.println(current_pass);
		try {
			InputStream  in  = new FileInputStream(current_pass + "\\src\\高橋健太\\JPL\\ch20\\ex20_06\\inFile.txt");
			OpeInput mlp = new OpeInput(new InputStreamReader(in));
			mlp.operate();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
