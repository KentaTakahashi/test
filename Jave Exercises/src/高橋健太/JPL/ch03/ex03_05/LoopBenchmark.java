package 高橋健太.JPL.ch03.ex03_05;

public class LoopBenchmark extends Benchmark {

	private int mLoopParm;

	public LoopBenchmark(int mLoopParm) {
		super();
		this.mLoopParm = mLoopParm;
	}
	@Override
	void benchmark() {
		int i = 0;
		while(i < mLoopParm) i++;
	}
	public static void main(String[] args) {
		if (args.length != 2){
			System.out.println("引数を2つ指定して下さい");
			System.exit(1);
		}
		int count = Integer.parseInt(args[0]);
		int loop = Integer.parseInt(args[1]);
		LoopBenchmark benchmark = new LoopBenchmark(loop);
		long time = benchmark.repeat(count);
		System.out.println(count + " count, " + benchmark.mLoopParm + " loop in " + time + " nanoseconds");
	}
}
