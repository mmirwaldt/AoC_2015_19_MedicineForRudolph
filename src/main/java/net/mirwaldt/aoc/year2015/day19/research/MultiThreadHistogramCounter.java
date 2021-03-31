package net.mirwaldt.aoc.year2015.day19.research;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadHistogramCounter implements HistogramCounter {
    private final Map<Thread, AtomicInteger> histogramOfSuccessCounters = new ConcurrentHashMap<>();
    private final Map<Thread, AtomicInteger> histogramOfFailureCounters = new ConcurrentHashMap<>();
    private final AtomicInteger totalCountCache = new AtomicInteger();

    @Override
    public void incrementSuccessCounter() {
        histogramOfSuccessCounters.computeIfAbsent(Thread.currentThread(), k -> new AtomicInteger()).incrementAndGet();
    }

    @Override
    public void incrementFailureCounter() {
        histogramOfFailureCounters.computeIfAbsent(Thread.currentThread(), k -> new AtomicInteger()).incrementAndGet();
    }

    @Override
    public int getSuccessCount() {
        return histogramOfSuccessCounters.computeIfAbsent(Thread.currentThread(), k -> new AtomicInteger()).get();
    }

    @Override
    public int getFailureCount() {
        return histogramOfFailureCounters.computeIfAbsent(Thread.currentThread(), k -> new AtomicInteger()).get();
    }

    @Override
    public int getTotalCount() {
        int newTotalCount = getTotalCountForAllThreads();
        if(totalCountCache.getAndSet(newTotalCount) != newTotalCount && newTotalCount % 100_000 == 0) {
            System.out.println(newTotalCount);
        }
        return getSuccessCount() + getFailureCount();
    }

    public int getTotalCountForAllThreads() {
        return histogramOfSuccessCounters.values().stream().mapToInt(AtomicInteger::get).sum()
                + histogramOfFailureCounters.values().stream().mapToInt(AtomicInteger::get).sum();
    }
}
