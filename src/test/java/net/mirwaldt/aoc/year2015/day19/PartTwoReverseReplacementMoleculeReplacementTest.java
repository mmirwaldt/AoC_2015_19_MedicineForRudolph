package net.mirwaldt.aoc.year2015.day19;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTwoReverseReplacementMoleculeReplacementTest {
    private static Stream<Arguments> moleculeReplacement() {
        return Stream.of(Arguments.of(new ShufflingReverseMoleculeReplacement()));
    }

    @ParameterizedTest
    @MethodSource("moleculeReplacement")
    void test_example(PartTwoReverseMoleculeReplacement moleculeReplacement) {
        moleculeReplacement.addReplacement("e", "H");
        moleculeReplacement.addReplacement("e", "O");
        moleculeReplacement.addReplacement("H", "HO");
        moleculeReplacement.addReplacement("H", "OH");
        moleculeReplacement.addReplacement("O", "HH");
        assertEquals(3, moleculeReplacement.reverseReplaceToE("HOH", 1));
        assertEquals(6, moleculeReplacement.reverseReplaceToE("HOHOHO", 1));
    }
}
