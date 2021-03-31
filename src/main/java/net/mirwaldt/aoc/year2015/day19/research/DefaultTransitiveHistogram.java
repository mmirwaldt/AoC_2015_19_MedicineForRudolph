package net.mirwaldt.aoc.year2015.day19.research;

import java.util.*;

public class DefaultTransitiveHistogram extends AbstractTransitiveHistogram {
    private final Map<List<List<String>>, int[]> transitiveHistogramOfSuccess = new HashMap<>();

    @Override
    public void increment(List<String> firstReverseReplacement, List<String> secondReverseReplacement) {
        transitiveHistogramOfSuccess.computeIfAbsent(
                createRuleList(firstReverseReplacement, secondReverseReplacement), key -> new int[1])[0]++;
    }

    @Override
    public NavigableMap<Integer, Set<List<List<String>>>> getTransitiveHistogramOfSuccess() {
        return reverseMap();
    }

    public int getCountForRule(List<List<String>> rule) {
        return transitiveHistogramOfSuccess.getOrDefault(rule, new int[1])[0];
    }

    public NavigableMap<Integer, Set<List<List<String>>>> reverseMap() {
        final NavigableMap<Integer, Set<List<List<String>>>> reversedHistogram = new TreeMap<>();
        for (Map.Entry<List<List<String>>, int[]> entry : transitiveHistogramOfSuccess.entrySet()) {
            final List<List<String>> rules = entry.getKey();
            final int frequency = entry.getValue()[0];
            reversedHistogram.computeIfAbsent(frequency, key -> new HashSet<>()).add(rules);
        }
        return reversedHistogram;
    }
}
