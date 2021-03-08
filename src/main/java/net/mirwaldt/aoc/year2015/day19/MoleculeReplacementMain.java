package net.mirwaldt.aoc.year2015.day19;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MoleculeReplacementMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final PartOneMoleculeReplacement moleculeReplacement = new PartOneBruteForceMoleculeReplacement();
        int lineCounter = 0;
        for (String line : lines) {
            if(line.trim().isEmpty()) {
                break;
            } else {
                String[] moleculeReplacementTokens = line.split(" ");
                moleculeReplacement.addReplacement(moleculeReplacementTokens[0], moleculeReplacementTokens[2]);
            }
            lineCounter++;
        }

        final String favouriteMolecule = lines.get(lineCounter + 1);
        System.out.println(moleculeReplacement.replace(favouriteMolecule).size()); // result: 509
    }
}
