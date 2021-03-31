package net.mirwaldt.aoc.year2015.day19.research;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static net.mirwaldt.aoc.year2015.day19.MoleculeReplacementMain.addReplacements;

/**
 * This class enables to use some threads to find and check working orders of the replacements
 */
public class MultiThreadResearchMoleculeReplacementMain {
    private static final int ROUNDS = 4;
    public static void main(String[] args) throws IOException, InterruptedException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final String favouriteMolecule = lines.get(44);

        final TransitiveHistogram transitiveHistogram = new MultiThreadTransitiveHistogram();
        final HistogramCounter histogramCounter = new MultiThreadHistogramCounter();

        final Thread[] threads = new Thread[8];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                collectAndShowStatistics(lines, favouriteMolecule, transitiveHistogram, histogramCounter);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        final MinExplicitRulesApplying minExplicitRulesApplying =
                new MinExplicitRulesApplying(transitiveHistogram);
        addReplacements(lines, minExplicitRulesApplying);
        System.out.println(minExplicitRulesApplying.reverseReplaceToE(favouriteMolecule, ROUNDS));

        final KeepOrderReverseReplacement keepOrderReverseReplacement = new KeepOrderReverseReplacement();
        keepOrderReverseReplacement.setReverseReplacementsOrdered(
                minExplicitRulesApplying.getReverseReplacementsOrdered());
        System.out.println(keepOrderReverseReplacement.reverseReplaceToE(favouriteMolecule, ROUNDS));
    }

    private static void collectAndShowStatistics(
            List<String> lines,
            String favouriteMolecule,
            TransitiveHistogram transitiveHistogram,
            HistogramCounter histogramCounter) {
        final ResearchingShuffling replacement =
                new ResearchingShuffling(10_000_000, histogramCounter, transitiveHistogram);
        addReplacements(lines, replacement);
        replacement.reverseReplaceToE(favouriteMolecule, ROUNDS);
    }
}
