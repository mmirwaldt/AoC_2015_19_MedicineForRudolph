package net.mirwaldt.aoc.year2015.day19.research;

public class DefaultHistogramCounter implements HistogramCounter {
    private int histogramOfSuccessCounter = 0;
    private int histogramOfFailureCounter = 0;
    int lastCount = -1;

    @Override
    public void incrementSuccessCounter() {
        histogramOfSuccessCounter++;
    }

    @Override
    public void incrementFailureCounter() {
        histogramOfFailureCounter++;
    }

    @Override
    public int getSuccessCount() {
        return histogramOfSuccessCounter;
    }

    @Override
    public int getFailureCount() {
        return histogramOfFailureCounter;
    }

    @Override
    public int getTotalCount() {
        int totalCount = getSuccessCount() + getFailureCount();
        if(lastCount != totalCount && totalCount % 10_000 == 0) {
            System.out.println(totalCount);
            lastCount = totalCount;
        }
        return totalCount;
    }
}
