package net.mirwaldt.aoc.year2015.day19;

import java.util.Set;

public interface MoleculeReplacement {
    void addReplacement(String inputMolecule, String outputMolecule);
    Set<String> replace(String favouriteMolecule);
}
