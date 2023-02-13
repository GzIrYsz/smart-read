package fr.cyu.smartread.spellchecking.stringmetrics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLevenshteinDistance {
    @Test
    void shouldReturn0WhenStrAreEquals() {
        StringMetricsInterface distanceComputer = new LevenshteinDistance(1, 1, 1);

        float result = distanceComputer.computeDistance("Bonjour", "Bonjour");
        assertEquals(0, result);
    }

    @Test
    void shouldReturn1InsertionWhenStrHasOneExtraLetter() {
        final int insertionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, 1, 1);

        float result = distanceComputer.computeDistance("Bonjour", "Bonjoura");
        assertEquals(insertionCost, result);
    }

    @Test
    void shouldReturn2InsertionWhenStrHasTwoExtraLetter() {
        final int insertionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, 1, 1);

        float result = distanceComputer.computeDistance("Bonjour", "Bonjourac");
        assertEquals(2 * insertionCost, result);
    }

    @Test
    void shouldReturn3InsertionWhenStrHasTwoExtraLetter() {
        final int insertionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, 1, 1);

        float result = distanceComputer.computeDistance("Bonjour", "Bonjouracd");
        assertEquals(3 * insertionCost, result);
    }

    @Test
    void shouldReturn1DeletionWhenStrHasOneLetterLess() {
        final int deletionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(1, deletionCost, 1);

        float result = distanceComputer.computeDistance("Bonjour", "Bonjou");
        assertEquals(deletionCost, result);
    }

    @Test
    void shouldReturn2DeletionWhenStrHasTwoLetterLess() {
        final int deletionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(1, deletionCost, 1);

        float result = distanceComputer.computeDistance("Bonjour", "Bonjo");
        assertEquals(2 * deletionCost, result);
    }

    @Test
    void shouldReturn3DeletionWhenStrHasThreeLetterLess() {
        final int deletionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(1, deletionCost, 1);

        float result = distanceComputer.computeDistance("Bonjour", "Bonj");
        assertEquals(3 * deletionCost, result);
    }

    @Test
    void shouldReturn1SubstitutionWhenStrHasThreeWrongLetter() {
        final int substitutionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(1, 1, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "Bonjouc");
        assertEquals(substitutionCost, result);
    }

    @Test
    void shouldReturn2SubstitutionWhenStrHasTwoWrongLetter() {
        final int substitutionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(1, 1, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "Bonjocc");
        assertEquals(2 * substitutionCost, result);
    }

    @Test
    void shouldReturn3SubstitutionWhenStrHasThreeWrongLetter() {
        final int substitutionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(1, 1, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "Bonjccc");
        assertEquals(3 * substitutionCost, result);
    }

    @Test
    void shouldReturn4WhenStrHas1InsertionAnd1Substitution() {
        final int insertionCost = 2;
        final int substitutionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, 1, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "BonCoure");
        assertEquals(substitutionCost + insertionCost, result);
    }

    @Test
    void shouldReturn6WhenStrHas1InsertionAnd2Substitution() {
        final int insertionCost = 2;
        final int substitutionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, 1, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "BonCoCre");
        assertEquals(2 * substitutionCost + insertionCost, result);
    }

    @Test
    void shouldReturn8WhenStrHas1InsertionAnd3Substitution() {
        final int insertionCost = 2;
        final int substitutionCost = 2;
        final int deletionCost = 10;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, 10, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "ConCouCe");
        assertEquals(3 * substitutionCost + insertionCost, result);
    }

    @Test
    void shouldReturn6WhenStrHas2InsertionAnd1Substitution() {
        final int insertionCost = 2;
        final int substitutionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, 1, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "Conjouree");
        assertEquals(substitutionCost + 2 * insertionCost, result);
    }

    @Test
    void shouldReturn8WhenStrHas3InsertionAnd1Substitution() {
        final int insertionCost = 2;
        final int substitutionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, 1, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "Conjoureee");
        assertEquals(substitutionCost + 3 * insertionCost, result);
    }

    @Test
    void shouldReturn12WhenStrHas3InsertionAnd3Substitution() {
        final int insertionCost = 2;
        final int substitutionCost = 2;
        final int deletionCost = 10;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, 10, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "ConCouCeee");
        assertEquals(3 * substitutionCost + 3 * insertionCost, result);
    }

    @Test
    void shouldReturn6WhenStrHas1DeletionAnd1Substitution() {
        final int deletionCost = 3;
        final int substitutionCost = 2;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(1, deletionCost, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "Conjou");
        assertEquals(substitutionCost + deletionCost, result);
    }

    @Test
    void shouldReturn15WhenStrHas3DeletionAnd3Substitution() {
        final int deletionCost = 3;
        final int substitutionCost = 2;
        final int insertionCost = 100;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, deletionCost, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "CCCj");
        assertEquals( 3 * substitutionCost + 3 * deletionCost, result);
    }

    @Test
    void shouldReturn6WhenStrHas1InsertionAnd1SubstitutionWith_3_3_20() {
        final int insertionCost = 3;
        final int deletionCost = 3;
        final int substitutionCost = 20;

        StringMetricsInterface distanceComputer = new LevenshteinDistance(insertionCost, deletionCost, substitutionCost);

        float result = distanceComputer.computeDistance("Bonjour", "BonjouC");
        assertEquals( insertionCost + deletionCost, result);
    }
}
