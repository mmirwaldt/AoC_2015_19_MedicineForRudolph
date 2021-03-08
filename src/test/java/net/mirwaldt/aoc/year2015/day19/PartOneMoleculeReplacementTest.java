package net.mirwaldt.aoc.year2015.day19;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartOneMoleculeReplacementTest {
    private static Stream<Arguments> moleculeReplacement() {
        return Stream.of(Arguments.of(new PartOneBruteForceMoleculeReplacement()));
    }

    @ParameterizedTest
    @MethodSource("moleculeReplacement")
    void test_example(MoleculeReplacement moleculeReplacement) {
        moleculeReplacement.addReplacement("H", "HO");
        moleculeReplacement.addReplacement("H", "OH");
        moleculeReplacement.addReplacement("O", "HH");
        assertEquals(Set.of("HOOH", "HOHO", "OHOH", "HHHH"),
                moleculeReplacement.replace("HOH"));
        assertEquals(Set.of("HHHHOHO", "HOHHHHO", "HOHOHHH", "HOOHOHO", "HOHOOHO", "HOHOHOO", "OHOHOHO"),
                moleculeReplacement.replace("HOHOHO"));
    }
}
