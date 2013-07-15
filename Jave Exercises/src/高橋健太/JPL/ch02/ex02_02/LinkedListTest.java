package 高橋健太.JPL.ch02.ex02_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		LinkedList testList_1 = new LinkedList((Object)"testList_1");
		LinkedList testList_2 = new LinkedList((Object)"testList_2", testList_1);

		assertEquals("testList_1が代入されてない", "testList_1", testList_1.get());
		assertEquals("testList_2が代入されてない", "testList_2", testList_2.get());
		assertEquals("testList_2のnextにtestList_1が代入されてない", testList_1, testList_2.getNext());
	}

}
