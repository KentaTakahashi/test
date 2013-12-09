package 高橋健太.JPL.ch17.ex17_04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class ResourceManagerTest {

	private static List<Object> keyList = new ArrayList<Object>();
	private static List<Resource> resourceList = new ArrayList<Resource>();

	private static Long freeMemory_preUse;
	private static Long freeMemory_useRes;
	private static Long freeMemory_clearRes;

	@BeforeClass
    public static void initialize() throws InterruptedException {
		Runtime rt = Runtime.getRuntime();

		rt.gc();
		freeMemory_preUse = rt.freeMemory();

		ResourceManager rm = new ResourceManager();

		for(int i = 0; i <1000; i++) {
			Object key = Integer.toString(i);
			keyList.add(key);
			resourceList.add(rm.getResource(key));
		}

		rt.gc();
		freeMemory_useRes = rt.freeMemory();

		for(int i = 0; i <1000; i++) {
			Object key = keyList.get(i);
			Resource res = resourceList.get(i);
			res.use(key);
		}

		for(int i = 0; i <1000; i++) {
			Resource res = resourceList.get(i);
			res.release();
		}

		keyList.clear();//リソースへの参照をすべて削除
		resourceList.clear();//リソースへの参照をすべて削除

		rm.shutdown();
		rt.gc();

		rm.reaper.join();

		rt.gc();
		freeMemory_clearRes = rt.freeMemory();

		System.out.println("freeMemory_preUse  :" + freeMemory_preUse);
		System.out.println("freeMemory_useRes  :" + freeMemory_useRes);
		System.out.println("freeMemory_clearRes:" + freeMemory_clearRes);
    }
	@Test
	public void test_1(){
		//リソースを確保すれば、freeMemoryは減るはず
		assertTrue(freeMemory_preUse > freeMemory_useRes);
	}
	@Test
	public void test_2(){
		//リソースを開放すれば、freeMemoryは増えるはず
		assertTrue(freeMemory_useRes < freeMemory_clearRes);
	}
}
