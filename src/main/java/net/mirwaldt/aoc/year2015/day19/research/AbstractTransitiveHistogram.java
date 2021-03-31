package net.mirwaldt.aoc.year2015.day19.research;

import java.util.*;

public abstract class AbstractTransitiveHistogram implements TransitiveHistogram {
    private final Map<List<String>, List<String>> reverseMap;

    public AbstractTransitiveHistogram(Map<List<String>, List<String>> reverseMap) {
        this.reverseMap = reverseMap;
    }

    public AbstractTransitiveHistogram() {
        this(new HashMap<>());
    }

    protected List<List<String>> createRuleList(
            List<String> firstReverseReplacement, List<String> secondReverseReplacement) {
        return Arrays.asList(getReverse(firstReverseReplacement), getReverse(secondReverseReplacement));
    }

    private List<String> getReverse(List<String> reverseReplacement) {
        return reverseMap.computeIfAbsent(reverseReplacement, key -> {
            final List<String> reverseList = new ArrayList<>(reverseReplacement);
            Collections.reverse(reverseList);
            return reverseList;
        });
    }
}
