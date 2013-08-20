package 高橋健太.JPL.ch06.ex06_04;

enum Signal {
	RED("RED"),
	YELLOW("YELLOW"),
	BLUE("BLUE");

	Color color;
	Signal(String colorStr) { color = new Color(colorStr); }

	public Color getColor() { return color; }

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