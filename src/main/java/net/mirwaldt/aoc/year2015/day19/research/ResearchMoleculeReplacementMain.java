package net.mirwaldt.aoc.year2015.day19.research;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static net.mirwaldt.aoc.year2015.day19.MoleculeReplacementMain.addReplacements;

public class ResearchMoleculeReplacementMain {
    private static final int ROUNDS = 4;
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final String favouriteMolecule = lines.get(44);

        final TransitiveHistogram transitiveHistogram = new DefaultTransitiveHistogram();
        final HistogramCounter histogramCounter = new DefaultHistogramCounter();
        collectAndShowStatistics(lines, favouriteMolecule, transitiveHistogram, histogramCounter);

        final MinExplicitRulesApplying minExplicitRulesApplying =
                new MinExplicitRulesApplying(transitiveHistogram);
        addReplacements(lines, minExplicitRulesApplying);
        System.out.println(minExplicitRulesApplying.reverseReplaceToE(favouriteMolecule, ROUNDS));

//        writeToTxtFile(favouriteMolecule, minExplicitRulesApplying);

        final KeepOrderReverseReplacement keepOrderReverseReplacement = new KeepOrderReverseReplacement();
        keepOrderReverseReplacement.setReverseReplacementsOrdered(
                minExplicitRulesApplying.getReverseReplacementsOrdered());
        System.out.println(keepOrderReverseReplacement.reverseReplaceToE(favouriteMolecule, ROUNDS));
    }

    private static void writeToTxtFile(
            String favouriteMolecule,
            MinExplicitRulesApplying minExplicitRulesApplying)
            throws IOException {
        try(OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("right_order.txt")))) {
            final List<List<String>> reverseReplacements =
                    minExplicitRulesApplying.getReverseReplacementsOrdered();
            for(List<String> reverseReplacement : reverseReplacements) {
                outputStreamWriter.append(reverseReplacement.get(1))
                        .append(" => ")
                        .append(reverseReplacement.get(0))
                        .append("\n");
            }
            outputStreamWriter.append("\n").append(favouriteMolecule).append("\n");
        }
    }

    private static void collectAndShowStatistics(
            List<String> lines,
            String favouriteMolecule,
            TransitiveHistogram transitiveHistogram,
            HistogramCounter histogramCounter) {
        final ResearchingShuffling replacement =
                new ResearchingShuffling(1_000_000, histogramCounter, transitiveHistogram);
        addReplacements(lines, replacement);
        replacement.reverseReplaceToE(favouriteMolecule, ROUNDS);
        System.out.println(replacement.toString());
    }
}
