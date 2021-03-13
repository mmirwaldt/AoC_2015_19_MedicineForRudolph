package net.mirwaldt.aoc.year2015.day19;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MoleculeReplacementMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final PartOneMoleculeReplacement partOneMoleculeReplacement = new PartOneBruteForceMoleculeReplacement();
        addReplacements(lines, partOneMoleculeReplacement);
        final String favouriteMolecule = lines.get(44);

        System.out.println(partOneMoleculeReplacement.replace(favouriteMolecule).size()); // result: 509

        final ShufflingReverseMoleculeReplacement moleculeReplacement =
                new ShufflingReverseMoleculeReplacement();
        addReplacements(lines, moleculeReplacement);

        System.out.println(moleculeReplacement.reverseReplaceToE(favouriteMolecule, 220)); // result: 195
    }

    private static void addReplacements(List<String> lines, MoleculeReplacement moleculeReplacement) {
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                break;
            } else {
                String[] moleculeReplacementTokens = line.split(" ");
                moleculeReplacement.addReplacement(moleculeReplacementTokens[0], moleculeReplacementTokens[2]);
            }
        }
    }
}
