package 高橋健太.JPL.ch06.ex06_05;

enum Signal {
	RED {Color getColor(){return new Color("RED"); }},
	YELLOW{Color getColor(){return (new Color("YELLOW")); }},
	BLUE{Color getColor(){return (new Color("BLUE")); }};

	//このenumが定義するメソッドの宣言
	abstract Color getColor();

	//インナークラス、色をStringで持つ
	class Color {
		String myColor;
		Color(String colorStr) { myColor = colorStr; }
		public String toString() { return myColor; }
	}
	public static void main(String[] args) {

		System.out.println("Signal.RED.getColor()    = " + Signal.RED.getColor());
		System.out.println("Signal.YELLOW.getColor() = " + Signal.YELLOW.getColor());
		System.out.println("Signal.BLUE.getColor()   = " + Signal.BLUE.getColor());
	}
}