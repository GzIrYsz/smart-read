package fr.cyu.smartread.deeplearning;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilityOperationsTest {
    @Test
    void shouldReturn0ifArrayIsEmpty() {
        double[] array = {};
        double result = UtilityOperations.sum(array);

        assertEquals(0, result);
    }

    @Test
    void shouldReturn20ifArrayHas10and10() {
        double[] array = {10, 10};
        double result = UtilityOperations.sum(array);

        assertEquals(20, result);
    }

    @Test
    void shouldGetExceptionIfArrayIsEmpty() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double[] array = {};

            UtilityOperations.mean(array);
        });

        assertEquals("Array is empty", thrown.getMessage());
    }

    @Test
    void shouldReturnRightResultIfArrayHas10and10and5() {
        double rightResult = 8.333333333333334;
        double[] array = {10, 10, 5};
        double result = UtilityOperations.mean(array);

        assertEquals(rightResult, result);
    }
}
