package 高橋健太.JPL.ch12.ex12_01;

public class ObjectNotFoundException extends Exception {

	public final String listName;
	public ObjectNotFoundException(String name) {
		super("No LinkedList named \"" + name + "\" found");
		listName = name;
	}
}
