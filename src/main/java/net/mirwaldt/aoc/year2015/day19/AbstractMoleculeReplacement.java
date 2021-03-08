package net.mirwaldt.aoc.year2015.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstractMoleculeReplacement implements MoleculeReplacement {
    protected final SortedMap<String, List<String>> replacements = new TreeMap<>();

    @Override
    public void addReplacement(String inputMolecule, String outputMolecule) {
        replacements.computeIfAbsent(inputMolecule, key -> new ArrayList<>()).add(outputMolecule);
    }
}
