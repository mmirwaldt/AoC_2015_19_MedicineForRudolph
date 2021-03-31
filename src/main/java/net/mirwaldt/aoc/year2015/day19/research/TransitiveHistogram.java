package net.mirwaldt.aoc.year2015.day19.research;

import java.util.List;
import java.util.NavigableMap;
import java.util.Set;

public interface TransitiveHistogram {
    void increment(List<String> firstReverseReplacement, List<String> secondReverseReplacement);
    NavigableMap<Integer, Set<List<List<String>>>> getTransitiveHistogramOfSuccess();
    int getCountForRule(List<List<String>> rule);
}
