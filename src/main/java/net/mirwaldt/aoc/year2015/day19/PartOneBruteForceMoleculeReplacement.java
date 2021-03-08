package net.mirwaldt.aoc.year2015.day19;

import java.util.*;

public class PartOneBruteForceMoleculeReplacement extends AbstractMoleculeReplacement
        implements PartOneMoleculeReplacement {

    @Override
    public Set<String> replace(String favouriteMolecule) {
        return new InternalBruteForceMoleculeReplacement(replacements).replace(favouriteMolecule);
    }

    static class InternalBruteForceMoleculeReplacement {
        private final SortedMap<String, List<String>> replacements;
        private StringBuilder molecule;
        private Set<String> result;

        public InternalBruteForceMoleculeReplacement(SortedMap<String, List<String>> replacements) {
            this.replacements = replacements;
        }

        public Set<String> replace(String favouriteMolecule) {
            molecule = new StringBuilder(favouriteMolecule);
            result = new HashSet<>();
            for (Map.Entry<String, List<String>> replacementEntry : replacements.entrySet()) {
                final String inputMolecule = replacementEntry.getKey();
                final List<String> outputMolecules = replacementEntry.getValue();
                applyReplacements(inputMolecule, outputMolecules);
            }
            return result;
        }


        private void applyReplacements(String inputMolecule, List<String> outputMolecules) {
            for (String outputMolecule : outputMolecules) {
                int indexOfInputMolecule = findFirstInputMolecule(inputMolecule);
                while (-1 < indexOfInputMolecule) {
                    result.add(createNewMolecule(inputMolecule, outputMolecule, indexOfInputMolecule));
                    resetMolecule(inputMolecule, outputMolecule, indexOfInputMolecule);
                    indexOfInputMolecule = findNextInputMolecule(inputMolecule, indexOfInputMolecule);
                }
            }
        }

        private int findFirstInputMolecule(String inputMolecule) {
            return molecule.indexOf(inputMolecule);
        }

        private int findNextInputMolecule(String inputMolecule, int indexOfInputMolecule) {
            return molecule.indexOf(
                    inputMolecule, indexOfInputMolecule + inputMolecule.length());
        }

        private String createNewMolecule(String inputMolecule, String outputMolecule, int indexOfInputMolecule) {
            molecule.replace(
                    indexOfInputMolecule, indexOfInputMolecule + inputMolecule.length(), outputMolecule);
            return molecule.toString();
        }

        private void resetMolecule(String inputMolecule, String outputMolecule, int indexOfInputMolecule) {
            molecule.replace(
                    indexOfInputMolecule, indexOfInputMolecule + outputMolecule.length(), inputMolecule);
        }
    }
}
