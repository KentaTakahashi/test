package 高橋健太.JPL.ch04.ex04_02;

public class TestSort {

	static Object[] testData = {
		0.3, 1.3e-2, 7.9, 3.17
	};

	public static void main(String[] args) {
		mySort bsort = new CheatingSortObject();
		SortMetrics metrics = bsort.sort(testData);
		System.out.println("Metrics: " + metrics);
		for(int i = 0; i < testData.length; i++) {
			System.out.printf("\n hashCode: %012d Object: %s", testData[i].hashCode(), testData[i]);//ex03_12修正箇所
		}
	}
}
