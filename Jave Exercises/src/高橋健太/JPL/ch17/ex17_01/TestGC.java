package 高橋健太.JPL.ch17.ex17_01;

import java.util.ArrayList;
import java.util.List;

public final class TestGC {

	private List<Integer> objectList = new ArrayList<Integer>();

	public static void main(String[] args) {

		TestGC c = new TestGC();
		c.testGC();
	}

	private void testGC() {

		Runtime rt = Runtime.getRuntime();
		rt.gc();

		System.out.println("maxMemory  :" + rt.maxMemory());
		System.out.println("totalMemory:" + rt.totalMemory());
		System.out.println("freeMemory :" + rt.freeMemory() + "(1：オブジェクト作成前)");

		for(int i = 0; i <10000; i++)
			objectList.add(new Integer(i));

		System.out.println("freeMemory :" + rt.freeMemory() + "(2：オブジェクト作成後)");

		objectList.clear();//リソースへの参照をすべて削除

		System.out.println("freeMemory :" + rt.freeMemory() + "(3：オブジェクト解放後)");

		rt.gc();

		System.out.println("freeMemory :" + rt.freeMemory() + "(4：gc()のcall後)");

	}
}