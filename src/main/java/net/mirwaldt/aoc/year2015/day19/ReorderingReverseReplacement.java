package net.mirwaldt.aoc.year2015.day19;

public abstract class ReorderingReverseReplacement extends AbstractReverseMoleculeReplacement {
    @Override
    protected int replaceAndCount(String favouriteMolecule, int rounds) {
        int counter = 0;
        while(isNotOnlyE()) {
            reset(favouriteMolecule);
            reorderReverseReplacementsRules();
            prepare();
            counter = replaceAllToE(rounds);
        }
        return counter;
    }

    protected abstract void reorderReverseReplacementsRules();
}
