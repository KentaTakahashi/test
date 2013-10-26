package 高橋健太.JPL.ch14.ex14_10;

import java.util.LinkedList;

/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 */

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.
 *
 * [Instruction]
 *  Implement one constructor and three methods.
 *  Don't forget to write a Test program to test this class.
 *  Pay attention to @throws tags in the javadoc.
 *  If needed, you can put "synchronized" keyword to methods.
 *  All classes for implementation must be private inside this class.
 *  Don't use java.util.concurrent package.
 */
public class ThreadPool {

	private final int numberOfThreads;
	private final threadPoolTask[] myThreads;
	private final LinkedList<Runnable> queue;
	private boolean isRun;

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize the max size of queue
	 * @param numberOfThreads the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException if either queueSize or numberOfThreads
	 *         is less than 1
	 */
    public ThreadPool(int queueSize, int numberOfThreads) {

    	if((queueSize < 1) || (numberOfThreads < 1))
    		throw new IllegalArgumentException("queueSize or numberOfThreads is less than 1 !");

    	this.numberOfThreads = numberOfThreads;

    	queue = new LinkedList<Runnable>();
    	myThreads = new threadPoolTask[numberOfThreads];
    	for (int i = 0; i < numberOfThreads; i++) {
        	myThreads[i] = new threadPoolTask();
        }

    	isRun = false;//set start flag false
    }

    /**
     * Starts threads.
     *
     * @throws IllegalStateException if threads has been already started.
     */
    public void start() {
    	if(isRun == true)
    		throw new IllegalStateException("threads has been already started !");
    	else {
    		isRun = true;
    		for (int i = 0; i < numberOfThreads; i++) {
    			myThreads[i].start();
    		}
    	}
    }

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException if threads has not been started.
	 */
    public void stop() {
    	if(isRun == false)
    		throw new IllegalStateException("threads has not been started !");
    	else {
    		isRun =false;

    		for (int i = 0; i < numberOfThreads; i++) {
    			while(myThreads[i].isAlive()){
    				//wait for terminations
    			}
            }
    	}
    }

	/**
	 * Executes the specified Runnable object, using a thread in the pool.
	 * run() method will be invoked in the thread. If the queue is full, then
	 * this method invocation will be blocked until the queue is not full.
	 *
	 * @param runnable Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException if runnable is null.
	 * @throws IllegalStateException if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		if(runnable == null)
			throw new NullPointerException("runnable param is null !");
		if(isRun == false)
			throw new IllegalStateException("threads has not been started !");

		synchronized(queue) {
			queue.addLast(runnable);
			queue.notifyAll();
		}
	}

	/**
	 * Simple counter task which counts the number of invocation of run() method.
	 */
	private class threadPoolTask extends Thread {

		@Override
		public void run() {
			Runnable r;

			outside: while (true) {
				//if(!isRun) continue;
				synchronized(queue) {
					while (queue.isEmpty()) {
						if(!isRun) break outside;//stop関数が呼ばれたら、outsideのwhile文を抜ける
						try {
							queue.wait(10);//10msec wait
						} catch (InterruptedException ignored) {
					    //何もしない
						}
					}
					r = (Runnable) queue.removeFirst();
				}
				// If we don't catch RuntimeException, // the pool could leak threads
				try {
					r.run();
				}
				catch (RuntimeException e) {
					// You might want to log something here
				}
				if(!isRun) break outside;//stop関数が呼ばれたら、outsideのwhile文を抜ける
			}
		}
	}
}
