package com.xiao.algorithms.javainternal;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadBenchmark {

	public static void main(String[] args) {
		// 1. launch 100,000 concurrent requests
		int taskCount = 100_000;

		// change to 'false' to crash jvm
		boolean useVirtualThreads = true;

		System.out.println("Starting " + taskCount + " tasks using " +
				(useVirtualThreads ? "VIRTUAL" : "PLATFORM") + " threads...");

		long startTime = System.currentTimeMillis();
		AtomicInteger completed = new AtomicInteger(0);


		// 'newVirtualThreadPerTaskExecutor' creates a fresh Virtual Thread for every task.
		// 'newCachedThreadPool' tries to create a standard Platform Thread for every task.

		try (var executor = useVirtualThreads ?
				Executors.newVirtualThreadPerTaskExecutor() :
				Executors.newCachedThreadPool()) {

			for (int i = 0; i < taskCount; i++) {
				executor.submit(() -> {
					try {
						// simulate IO blocking (database call)
						Thread.sleep(Duration.ofSeconds(2));
						completed.incrementAndGet();
					} catch (InterruptedException e) {
						// ignore
					}
				});
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("--------------------------------------------------");
		System.out.println("Success! Finished " + completed.get() + " tasks.");
		System.out.println("Total Time: " + (endTime - startTime) + "ms");
	}
}
