package net.mirwaldt.aoc.year2015.day19;

import java.util.*;

public class BruteForceMoleculeReplacement implements MoleculeReplacement {
    private final SortedMap<String, List<String>> replacements = new TreeMap<>();

    @Override
    public void addReplacement(String inputMolecule, String outputMolecule) {
        replacements.computeIfAbsent(inputMolecule, key -> new ArrayList<>()).add(outputMolecule);
    }

    @Override
    public Set<String> replace(String favouriteMolecule) {
        final Set<String> result = new HashSet<>();
        final StringBuilder stringBuilder = new StringBuilder(favouriteMolecule);
        for (Map.Entry<String, List<String>> replacementEntry : replacements.entrySet()) {
            final String inputMolecule = replacementEntry.getKey();
            final List<String> outputMolecules = replacementEntry.getValue();
            for (String outputMolecule : outputMolecules) {
                int indexOfInputMolecule = stringBuilder.indexOf(inputMolecule);
                while (-1 < indexOfInputMolecule) {
                    stringBuilder.replace(
                            indexOfInputMolecule, indexOfInputMolecule + inputMolecule.length(), outputMolecule);
                    final String newMolecule = stringBuilder.toString();
                    result.add(newMolecule);
                    stringBuilder.replace(
                            indexOfInputMolecule, indexOfInputMolecule + outputMolecule.length(), inputMolecule);
                    indexOfInputMolecule = stringBuilder.indexOf(
                            inputMolecule, indexOfInputMolecule + inputMolecule.length());
                }
            }
        }
        return result;
    }
}
