package net.mirwaldt.aoc.year2015.day19.research;

import net.mirwaldt.aoc.year2015.day19.ShufflingReverseReplacement;

import java.util.*;

public class ResearchingShuffling extends ShufflingReverseReplacement {
    private final int maxCountOfHistograms;
    private final HistogramCounter histogramCounter;
    private final TransitiveHistogram transitiveHistogramOfSuccess;

    public ResearchingShuffling(
            int maxCountOfHistograms,
            HistogramCounter histogramCounter,
            TransitiveHistogram transitiveHistogramOfSuccess) {
        this.maxCountOfHistograms = maxCountOfHistograms;
        this.histogramCounter = histogramCounter;
        this.transitiveHistogramOfSuccess = transitiveHistogramOfSuccess;
    }

    @Override
    protected int replaceAllToE(int rounds) {
        final int counter = super.replaceAllToE(rounds);
        if (isOnlyE()) {
            evaluateTransitive();
            histogramCounter.incrementSuccessCounter();
        } else {
            histogramCounter.incrementFailureCounter();
        }
        return counter;
    }

    private void evaluateTransitive() {
        for (int i = 0; i < orderedReverseReplacements.size(); i++) {
            final List<String> firstReverseReplacement = orderedReverseReplacements.get(i);
            for (int j = i + 1; j < orderedReverseReplacements.size(); j++) {
                final List<String> secondReverseReplacement = orderedReverseReplacements.get(j);
                transitiveHistogramOfSuccess.increment(firstReverseReplacement, secondReverseReplacement);
            }
        }
    }

    private boolean isOnlyE() {
        return !super.isNotOnlyE();
    }

    @Override
    protected boolean isNotOnlyE() {
        return histogramCounter.getTotalCount() <= maxCountOfHistograms;
    }

    @Override
    public String toString() {
        return "ResearchingShufflingReverseMoleculeReplacement{" +
                "\nhistogramOfSuccessCounter=" + histogramCounter.getSuccessCount() +
                ",\nhistogramOfFailureCounter=" + histogramCounter.getFailureCount() +
                ",\ntransitiveHistogramOfSuccess=" + this.ruleOrderHistogramToString(10) +
                '}';
    }

    public String ruleOrderHistogramToString(int maxHits) {
        final NavigableMap<Integer, Set<List<List<String>>>> reversedHistogram =
                transitiveHistogramOfSuccess.getTransitiveHistogramOfSuccess();
        final StringBuilder stringBuilder = new StringBuilder();
        int previousTopFrequency = Integer.MAX_VALUE;
        stringBuilder.append("\n");
        for (int topIndex = 0; topIndex < maxHits; topIndex++) {
            Map.Entry<Integer, Set<List<List<String>>>> entry =
                    reversedHistogram.subMap(0, true, previousTopFrequency, false).lastEntry();
            if (entry != null) {
                stringBuilder.append(entry.getKey());
                stringBuilder.append(":\n");
                for (List<List<String>> rulesEntry : entry.getValue()) {
                    stringBuilder.append(rulesEntry);
                }
                stringBuilder.append("\n");
                previousTopFrequency = entry.getKey();
            }
        }
        return stringBuilder.toString();
    }

}
