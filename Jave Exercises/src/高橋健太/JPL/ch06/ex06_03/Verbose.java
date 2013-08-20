package 高橋健太.JPL.ch06.ex06_03;

interface Verbose {
	enum Level {
		SILENT,
		TERSE,
		NORMAL,
		VERBOSE,
	}

	void setVerbosity(Level level);
	Level getVerbosity();
}