package net.mirwaldt.aoc.year2015.day19.research;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class MultiThreadTransitiveHistogram extends AbstractTransitiveHistogram {
    private final Map<List<List<String>>, LongAdder> transitiveHistogramOfSuccess = new ConcurrentHashMap<>();

    public MultiThreadTransitiveHistogram() {
        super(new ConcurrentHashMap<>());
    }

    @Override
    public void increment(List<String> firstReverseReplacement, List<String> secondReverseReplacement) {
        transitiveHistogramOfSuccess.computeIfAbsent(
                createRuleList(firstReverseReplacement, secondReverseReplacement), key -> new LongAdder()).increment();
    }

    @Override
    public NavigableMap<Integer, Set<List<List<String>>>> getTransitiveHistogramOfSuccess() {
        return reverseMap();
    }

    public int getCountForRule(List<List<String>> rule) {
        return transitiveHistogramOfSuccess.getOrDefault(rule, new LongAdder()).intValue();
    }

    public NavigableMap<Integer, Set<List<List<String>>>> reverseMap() {
        final NavigableMap<Integer, Set<List<List<String>>>> reversedHistogram = new TreeMap<>();
        for (Map.Entry<List<List<String>>, LongAdder> entry : transitiveHistogramOfSuccess.entrySet()) {
            final List<List<String>> rules = entry.getKey();
            final int frequency = entry.getValue().intValue();
            reversedHistogram.computeIfAbsent(frequency, key -> new HashSet<>()).add(rules);
        }
        return reversedHistogram;
    }
}
