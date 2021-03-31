package net.mirwaldt.aoc.year2015.day19.research;

public interface HistogramCounter {
    void incrementSuccessCounter();
    void incrementFailureCounter();
    int getSuccessCount();
    int getFailureCount();
    default int getTotalCount() {
        return getSuccessCount() + getFailureCount();
    }
}
