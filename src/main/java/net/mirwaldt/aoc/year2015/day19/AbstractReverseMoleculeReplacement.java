package net.mirwaldt.aoc.year2015.day19;

import java.util.*;

public abstract class AbstractReverseMoleculeReplacement
        extends AbstractMoleculeReplacement
        implements PartTwoReverseMoleculeReplacement {
    /**
     * a map where replacements are reversed, e.g. CaSi -> Si from Si => CaSi
     */
    protected final Map<String, String> reverseReplacements = new LinkedHashMap<>();
    /**
     * a list of all reversed replacements as lists
     * where the first element is the output molecule and the second element the input molecule.
     * E.g. Si => CaSi and Th => ThCa become [[CaSi, Si], [ThCa, Th]]
     * (reversed because we apply reverse replacements)
     */
    protected List<List<String>> orderedReverseReplacements = new ArrayList<>();
    protected StringBuilder replacedFavouriteMolecule;

    @Override
    public int reverseReplaceToE(String favouriteMolecule, int rounds) {
        replacedFavouriteMolecule = new StringBuilder(favouriteMolecule.length());
        resetOrderedReverseReplacements();
        prepare();
        reset(favouriteMolecule);
        return replaceAndCount(favouriteMolecule, rounds);
    }

    protected int replaceAndCount(String favouriteMolecule, int rounds) {
        return replaceAllToE(rounds);
    }

    protected int replaceAllToE(int rounds) {
        int counter = 0;
        for (int i = 0; i < rounds && isNotOnlyE(); i++) {
            counter = replaceAll(counter);
        }
        return counter;
    }

    protected void prepare() {
        reverseReplacements.clear();
        for (List<String> reverseReplacement : orderedReverseReplacements) {
            reverseReplacements.put(reverseReplacement.get(0), reverseReplacement.get(1));
        }
    }

    protected void resetOrderedReverseReplacements() {
        orderedReverseReplacements.clear();
        for(Map.Entry<String, List<String>> entry : replacements.entrySet()) {
            final String inputMolecule = entry.getKey();
            for (String outputMolecule : entry.getValue()) {
                orderedReverseReplacements.add(Arrays.asList(outputMolecule, inputMolecule));
            }
        }
    }

    protected int replaceAll(int counter) {
        for(Map.Entry<String, String> reverseReplacementEntry : reverseReplacements.entrySet()) {
            counter = replaceAll(counter, reverseReplacementEntry);
        }
        return counter;
    }

    protected int replaceAll(int counter, Map.Entry<String, String> reverseReplacementEntry) {
        final String outputMolecule = reverseReplacementEntry.getKey();
        final String inputMolecule = reverseReplacementEntry.getValue();
        int index = replacedFavouriteMolecule.indexOf(outputMolecule);
        while(-1 < index) {
            replacedFavouriteMolecule.replace(index, index + outputMolecule.length(), inputMolecule);
            index = replacedFavouriteMolecule.indexOf(outputMolecule);
            counter++;
        }
        return counter;
    }

    protected boolean isNotOnlyE() {
        return !replacedFavouriteMolecule.toString().equals("e");
    }

    protected void reset(String favouriteMolecule) {
        replacedFavouriteMolecule.delete(0, replacedFavouriteMolecule.length());
        replacedFavouriteMolecule.append(favouriteMolecule);
    }
}
