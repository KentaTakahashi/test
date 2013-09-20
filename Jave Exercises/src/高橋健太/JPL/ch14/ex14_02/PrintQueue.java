package 高橋健太.JPL.ch14.ex14_02;

import java.util.LinkedList;

public class PrintQueue{

	LinkedList<Printjob> jobList = new LinkedList<Printjob>();
	Printjob stub;
	public void add(Printjob job) {
		// jobListの末尾に新規jobを追加する
		jobList.add(job);
	}
	public Printjob remove() {
		// このリストの先頭 (最初の要素) 取得し、削除します。
		//return jobList.remove();
		return stub;
	}
}
