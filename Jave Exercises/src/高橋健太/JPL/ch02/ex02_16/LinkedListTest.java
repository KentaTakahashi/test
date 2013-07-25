package 高橋健太.JPL.ch02.ex02_16;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {

		Vehicle test_1 = new Vehicle("Alice");
		Vehicle test_2 = new Vehicle("Bob");
		Vehicle test_3 = new Vehicle("Carol");

		LinkedList testList_1 = new LinkedList(test_1);
		LinkedList testList_2 = new LinkedList(test_2);
		LinkedList testList_3 = new LinkedList(test_3);

		testList_1.setNext(testList_2);
		testList_2.setNext(testList_3);

		assertEquals("countElementメソッドがただしく実装されてない", 3, testList_1.countElement());
		assertEquals("countElementメソッドがただしく実装されてない", 2, testList_2.countElement());

	}
}
