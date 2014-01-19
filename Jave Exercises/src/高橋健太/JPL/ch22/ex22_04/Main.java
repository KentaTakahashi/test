package 高橋健太.JPL.ch22.ex22_04;

public class Main {

	public static void main(String[] args) {
		AttributedImpl ai = new AttributedImpl();
		Eye eye = new Eye(ai);

		ai.add(new Attr());
		ai.remove(null);
	}

}
