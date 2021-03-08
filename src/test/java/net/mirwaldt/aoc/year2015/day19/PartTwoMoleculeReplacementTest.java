package net.mirwaldt.aoc.year2015.day19;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTwoMoleculeReplacementTest {
    private static Stream<Arguments> moleculeReplacement() {
        return Stream.of(Arguments.of(new PartTwoBruteForceMoleculeReplacement()));
    }

    @ParameterizedTest
    @MethodSource("moleculeReplacement")
    void test_example(PartTwoMoleculeReplacement moleculeReplacement) {
        moleculeReplacement.addReplacement("e", "H");
        moleculeReplacement.addReplacement("e", "O");
        moleculeReplacement.addReplacement("H", "HO");
        moleculeReplacement.addReplacement("H", "OH");
        moleculeReplacement.addReplacement("O", "HH");
        assertEquals(3, moleculeReplacement.replaceStartingFromE("HOH"));
        assertEquals(6, moleculeReplacement.replaceStartingFromE("HOHOHO"));
    }
}
