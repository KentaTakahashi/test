package 高橋健太.JPL.ch17.ex17_04;

public interface Resource {
	void use(Object key, Object... args);
	void release();
}
