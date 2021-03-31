package net.mirwaldt.aoc.year2015.day19.research;

import net.mirwaldt.aoc.year2015.day19.AbstractReverseMoleculeReplacement;

import java.util.List;

public class KeepOrderReverseReplacement extends AbstractReverseMoleculeReplacement {
    @Override
    public int reverseReplaceToE(String favouriteMolecule, int rounds) {
        return super.reverseReplaceToE(favouriteMolecule, rounds);
    }

    @Override
    protected void resetOrderedReverseReplacements() {
        // do not recreate reverseReplacementsOrdered
    }

    public void setReverseReplacementsOrdered(List<List<String>> reverseReplacementsOrdered) {
        this.orderedReverseReplacements = reverseReplacementsOrdered;
    }
}
