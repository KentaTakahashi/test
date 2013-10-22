package 高橋健太.JPL.ch12.ex12_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test_01() {
		LinkedList<String> testList_1 = new LinkedList<String>("testList_1");
		LinkedList<String> testList_2 = new LinkedList<String>("testList_2", testList_1);

		try {
			LinkedList<String> tmp = testList_2.find("testList_1");
			assertEquals("find成功", testList_1,tmp);
		} catch (ObjectNotFoundException expected) {
			fail("ここには来ない");
	    }
	}
	@Test
	public void test_02() {
		LinkedList<String> testList_1 = new LinkedList<String>("testList_1");
		LinkedList<String> testList_2 = new LinkedList<String>("testList_2", testList_1);

		try {
			LinkedList<String> tmp = testList_2.find("testList_3");
			fail("ここには来ない");
		} catch (ObjectNotFoundException expected) {
			assertEquals("例外のメッセージ確認",
					expected.getMessage(), "No LinkedList named \"testList_3\" found");
	    }
	}
}
