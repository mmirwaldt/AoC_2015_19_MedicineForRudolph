package net.mirwaldt.aoc.year2015.day19;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ShufflingReverseReplacement extends ReorderingReverseReplacement {
    private final Random random = ThreadLocalRandom.current();

    protected void reorderReverseReplacementsRules() {
        Collections.shuffle(orderedReverseReplacements, random); // That's the trick!
    }
}
