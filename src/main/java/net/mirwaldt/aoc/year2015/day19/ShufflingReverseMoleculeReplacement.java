package net.mirwaldt.aoc.year2015.day19;

import java.util.*;

public class ShufflingReverseMoleculeReplacement
        extends AbstractMoleculeReplacement
        implements PartTwoReverseMoleculeReplacement {

    private final Map<String, String> reverseReplacements = new LinkedHashMap<>();
    private StringBuilder replacedFavouriteMolecule;

    @Override
    public int reverseReplaceToE(String favouriteMolecule, int rounds) {
        List<List<String>> reverseReplacementsShuffled = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : replacements.entrySet()) {
            for (String outputMolecule : entry.getValue()) {
                reverseReplacementsShuffled.add(Arrays.asList(outputMolecule, entry.getKey()));
            }
        }

        replacedFavouriteMolecule = new StringBuilder(favouriteMolecule.length());
        replacedFavouriteMolecule.append(favouriteMolecule);
        int counter = 0;
        while(isNotOnlyE()) {
            replacedFavouriteMolecule.delete(0, replacedFavouriteMolecule.length());
            replacedFavouriteMolecule.append(favouriteMolecule);

            Collections.shuffle(reverseReplacementsShuffled); // That's the trick! No idea why that works!
            reverseReplacements.clear();
            for (List<String> reverseReplacement : reverseReplacementsShuffled) {
                reverseReplacements.put(reverseReplacement.get(0), reverseReplacement.get(1));
            }

            counter = 0;
            for (int i = 0; i < rounds; i++) {
                for(Map.Entry<String, String> reverseReplacementEntry : reverseReplacements.entrySet()) {
                    final String outputMolecule = reverseReplacementEntry.getKey();
                    final String inputMolecule = reverseReplacementEntry.getValue();
                    int index = replacedFavouriteMolecule.indexOf(outputMolecule);
                    while(-1 < index) {
                        replacedFavouriteMolecule.replace(index, index + outputMolecule.length(), inputMolecule);
                        index = replacedFavouriteMolecule.indexOf(outputMolecule);
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    private boolean isNotOnlyE() {
        return 1 < replacedFavouriteMolecule.length() && !replacedFavouriteMolecule.toString().equals("e");
    }
}
