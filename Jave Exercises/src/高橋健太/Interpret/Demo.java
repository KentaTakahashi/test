package 高橋健太.Interpret;

public class Demo {
	private final String private_final= "for private final test";

	public Demo(String str)
			throws Exception, NullPointerException
	{
		new MessageDialog("何を渡されてもException! (\"null\"を渡されるとNullPointerException!!)", null);
		if(str.equals("null"))throw new NullPointerException();

		throw new Exception();
	}
}
