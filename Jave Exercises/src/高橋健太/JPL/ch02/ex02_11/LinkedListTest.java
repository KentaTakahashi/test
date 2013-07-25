package 高橋健太.JPL.ch02.ex02_11;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {

		Vehicle test_1 = new Vehicle("Alice");
		Vehicle test_2 = new Vehicle("Bob");
		Vehicle test_3 = new Vehicle("Carol");

		LinkedList testList_3 = new LinkedList(test_3);
		LinkedList testList_2 = new LinkedList(test_2, testList_3);
		LinkedList testList_1 = new LinkedList(test_1, testList_2);

		String expected_1 = "ID:0, Speed:0, Direction:0, Owner:Alice";
		String expected_2 = "ID:1, Speed:0, Direction:0, Owner:Bob";
		String expected_3 = "ID:2, Speed:0, Direction:0, Owner:Carol";

		assertEquals("toStringメソッドがただしく実装されてない", expected_1, testList_1.toString());
		assertEquals("toStringメソッドがただしく実装されてない", expected_2, testList_2.toString());
		assertEquals("toStringメソッドがただしく実装されてない", expected_3, testList_3.toString());
	}

}
