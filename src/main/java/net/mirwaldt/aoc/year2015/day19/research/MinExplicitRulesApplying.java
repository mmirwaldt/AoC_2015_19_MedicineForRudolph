package net.mirwaldt.aoc.year2015.day19.research;

import net.mirwaldt.aoc.year2015.day19.ReorderingReverseReplacement;

import java.util.*;

public class MinExplicitRulesApplying extends ReorderingReverseReplacement {
    private final Map<List<String>, Map<List<String>, Integer>> defaultRelations = new LinkedHashMap<>();
    private final Map<List<String>, Map<List<String>, Integer>> relations = new LinkedHashMap<>();
    private final Map<List<String>, Map<List<String>, Integer>> transitiveRelations = new LinkedHashMap<>();

    private final TransitiveHistogram transitiveHistogramOfSuccess;
    private final NavigableMap<Integer, Set<List<List<String>>>> implicitRulesInRemaining = new TreeMap<>();

    private List<List<String>> remaining;

    private int histogramCount = 0;
    private int explicitRulesCount = 0;

    public MinExplicitRulesApplying(TransitiveHistogram transitiveHistogramOfSuccess) {
        this.transitiveHistogramOfSuccess = transitiveHistogramOfSuccess;
    }

    @Override
    public int reverseReplaceToE(String favouriteMolecule, int rounds) {
        return super.reverseReplaceToE(favouriteMolecule, rounds);
    }

    @Override
    protected void reorderReverseReplacementsRules() {
        histogramCount++;
        explicitRulesCount = 0;
        findRelations();
        final List<List<String>> remaining = applyExplicitRelations();
//        findImplicitRules(remaining);
    }

    private List<List<String>> applyExplicitRelations() {
        remaining = new ArrayList<>(orderedReverseReplacements);
        remaining.removeAll(transitiveRelations.keySet());
        orderedReverseReplacements.removeAll(remaining);
        sortReverseReplacementsOrdered();
        orderedReverseReplacements.addAll(remaining);
        return remaining;
    }

    private void findImplicitRules(List<List<String>> remaining) {
        implicitRulesInRemaining.clear();
        final NavigableMap<Integer, Set<List<List<String>>>> reversedHistogram =
                transitiveHistogramOfSuccess.getTransitiveHistogramOfSuccess();
        for(Map.Entry<Integer, Set<List<List<String>>>> entry : reversedHistogram.entrySet()) {
            final Set<List<List<String>>> rules = entry.getValue();
            for (List<List<String>> rule : rules) {
                final List<String> firstReplacement = new ArrayList<>(rule.get(0));
                final List<String> secondReplacement = new ArrayList<>(rule.get(1));
                Collections.reverse(firstReplacement);
                Collections.reverse(secondReplacement);
                final int indexOfFirstReplacement = remaining.indexOf(firstReplacement);
                final int indexOfSecondReplacement = remaining.indexOf(secondReplacement);
                if(-1 < indexOfFirstReplacement
                        && -1 < indexOfSecondReplacement
                        && indexOfFirstReplacement < indexOfSecondReplacement) {
                    implicitRulesInRemaining.computeIfAbsent(entry.getKey(), k -> new HashSet<>()).add(rule);
                }
            }
        }
    }

    private void sortReverseReplacementsOrdered() {
        orderedReverseReplacements.sort((left, right) -> {
            int result = transitiveRelations
                    .getOrDefault(left, Collections.emptyMap())
                    .getOrDefault(right, 0);
            if (result == 0) {
                throw new AssertionError("Cannot compare left=" + left + ", right=" + right);
            } else {
                return result;
            }
        });
    }

    private void findRelations() {
        findIntransitiveRelations();
        findTransitiveRelations();
    }

    private void findIntransitiveRelations() {
        int lastKey = Integer.MAX_VALUE;
        NavigableMap<Integer, Set<List<List<String>>>> reversedHistogramView =
                transitiveHistogramOfSuccess.getTransitiveHistogramOfSuccess();

        defaultRelations.clear();
        for (int i = 0; i < orderedReverseReplacements.size(); i++) {
            final List<String> firstReverseReplacement = orderedReverseReplacements.get(i);
            for (int j = i + 1; j < orderedReverseReplacements.size(); j++) {
                final List<String> secondReverseReplacement = orderedReverseReplacements.get(j);
                defaultRelations.computeIfAbsent(firstReverseReplacement, key -> new LinkedHashMap<>())
                        .put(secondReverseReplacement, -1);
                defaultRelations.computeIfAbsent(secondReverseReplacement, key -> new LinkedHashMap<>())
                        .put(firstReverseReplacement, +1);
            }
        }

        relations.clear();
        for (int i = 0; i < histogramCount; i++) {
            reversedHistogramView =
                    reversedHistogramView.subMap(0, true, lastKey, false);
            for (List<List<String>> rule : reversedHistogramView.lastEntry().getValue()) {
                final List<String> firstReplacement = new ArrayList<>(rule.get(0));
                final List<String> secondReplacement = new ArrayList<>(rule.get(1));
                Collections.reverse(firstReplacement);
                Collections.reverse(secondReplacement);
                relations.computeIfAbsent(firstReplacement, key -> new LinkedHashMap<>())
                        .put(secondReplacement, -1);
                relations.computeIfAbsent(secondReplacement, key -> new LinkedHashMap<>())
                        .put(firstReplacement, +1);
                explicitRulesCount++;
            }
            lastKey = reversedHistogramView.lastKey();
        }

        final List<List<String>> keys = new ArrayList<>(relations.keySet());
        for (int i = 0; i < keys.size(); i++) {
            List<String> left = keys.get(i);
            for (int j = i + 1; j < keys.size(); j++) {
                List<String> right = keys.get(j);
                if(!left.equals(right)) {
                    int leftRightResult = relations.getOrDefault(left, Collections.emptyMap())
                            .getOrDefault(right, 0);
                    if(leftRightResult == 0) {
                        relations.computeIfAbsent(left, key -> new HashMap<>())
                                .put(right, defaultRelations.get(left).get(right));
                        relations.computeIfAbsent(right, key -> new HashMap<>())
                                .put(left, defaultRelations.get(right).get(left));
                    }
                }
            }
        }
    }

    private void findTransitiveRelations() {
        transitiveRelations.clear();
        for (List<String> key : relations.keySet()) {
            List<List<String>> firstKeys = new ArrayList<>();
            firstKeys.add(key);
            addTransitions(firstKeys, key, +1);
            addTransitions(firstKeys, key, -1);
        }
    }

    @Override
    protected int replaceAllToE(int rounds) {
        final int counter = super.replaceAllToE(rounds);
        if(!isNotOnlyE()) {
            System.out.println("histogramCount=" + histogramCount);
            System.out.println("explicitRulesCount=" + explicitRulesCount);
//            System.out.println("implicitRulesInRemaining.size()=" + implicitRulesInRemaining.size());
//            System.out.println("rulesInRemaining=" + rulesInRemaining.toString()
//                    .replace("]]],","]]],\n"));
        }
        return counter;
    }

    public List<List<String>> getReverseReplacementsOrdered() {
        return orderedReverseReplacements;
    }

    private void addTransitions(List<List<String>> firstKeys, List<String> key, int sign) {
        Map<List<String>, Integer> targets = relations.getOrDefault(key, Collections.emptyMap());
        for (Map.Entry<List<String>, Integer> entry : targets.entrySet()) {
            if (sign == entry.getValue() && !firstKeys.contains(entry.getKey())) {
                for (List<String> firstKey : firstKeys) {
                    transitiveRelations.computeIfAbsent(firstKey, k -> new LinkedHashMap<>())
                            .put(entry.getKey(), entry.getValue());
                }
                firstKeys.add(entry.getKey());
                addTransitions(firstKeys, entry.getKey(), sign);
                firstKeys.remove(entry.getKey());
            }
        }
    }
}
