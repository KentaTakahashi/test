package 高橋健太.JPL.ch17.ex17_05;

public interface Resource {
	void use(Object key, Object... args);
	void release();
}
