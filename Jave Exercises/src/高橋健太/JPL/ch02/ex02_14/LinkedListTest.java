package 高橋健太.JPL.ch02.ex02_14;

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

		testList_1.setNext(testList_2);
		assertEquals("setNextメソッドがただしく実装されてない", testList_2, testList_1.getNext());

		testList_1.set(test_3);
		assertEquals("setメソッドがただしく実装されてない", test_3, testList_1.get());
	}

}
