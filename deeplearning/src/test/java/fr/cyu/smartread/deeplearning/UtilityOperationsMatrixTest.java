package fr.cyu.smartread.deeplearning;

import org.apache.commons.lang.math.IntRange;
import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityOperationsMatrixTest {

    private final double epsilon = 1e-7;
    private final double max = 1 - epsilon;
    @Test
    void shouldReturnTrueIfTwoMatrixAreEmpty() {
        DMatrixRMaj matrix1 = new DMatrixRMaj();
        DMatrixRMaj matrix2 = new DMatrixRMaj();

        boolean result = UtilityOperationsMatrix.areShapeEqual(matrix1, matrix2);
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfTwoMatrixHaveSameShape() {
        DMatrixRMaj matrix1 = new DMatrixRMaj(new double[][] {
                {1, 2, 3},
                {1, 2, 3},
        });

        DMatrixRMaj matrix2 = new DMatrixRMaj(new double[][] {
                {1, 2, 3},
                {1, 2, 3},
        });

        boolean result = UtilityOperationsMatrix.areShapeEqual(matrix1, matrix2);
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoMatrixHaveNotSameShape() {
        DMatrixRMaj matrix1 = new DMatrixRMaj(new double[][] {
                {1, 2, 3}
        });

        DMatrixRMaj matrix2 = new DMatrixRMaj(new double[][] {
                {1, 2, 3},
                {1, 2, 3},
        });

        boolean result = UtilityOperationsMatrix.areShapeEqual(matrix1, matrix2);
        assertFalse(result);
    }

    @Test
    void shouldReturnEpsilonIfMatrixValueIs0() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {0},
        });

        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][] {
                {epsilon}
        });

        DMatrixRMaj result = UtilityOperationsMatrix.clip(matrix, epsilon, max);

        EjmlUnitTests.assertEquals(rightResult, result);
    }

    @Test
    void shouldReturnMaxIfMatrixValueIs1() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {1},
        });

        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][] {
                {max}
        });

        DMatrixRMaj result = UtilityOperationsMatrix.clip(matrix, epsilon, max);

        EjmlUnitTests.assertEquals(rightResult, result);
    }

    @Test
    void shouldReturnSameMatrixIfMatrixIsEmpty() {
        DMatrixRMaj matrix = new DMatrixRMaj();

        DMatrixRMaj rightResult = new DMatrixRMaj();


        DMatrixRMaj result = UtilityOperationsMatrix.clip(matrix, epsilon, max);
        EjmlUnitTests.assertEquals(rightResult, result);
    }

    @Test
    void shouldReturnRightMatrix() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {1, 1.2, 3},
                {-3, 1.2, 4.5},
                {0, 1.2, 4.5},
        });

        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][] {
                {max, max, max},
                {epsilon, max, max},
                {epsilon, max, max},
        });

        DMatrixRMaj result = UtilityOperationsMatrix.clip(matrix, epsilon, max);
        EjmlUnitTests.assertEquals(rightResult, result);
    }

    @Test
    void shouldGetAnExceptionIfA2RowMatrixIsGiven() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {1, 1, 1},
                {1, 1, 1},
        });

        NotARowException thrown = Assertions.assertThrows(NotARowException.class, () -> UtilityOperationsMatrix.meanOneRow(matrix));

        assertEquals("the matrix entered in parameter is not a row, current number of row " + matrix.getNumRows(), thrown.getMessage());
    }

    @Test
    void shouldNotGetAnExceptionIfARowMatrixIsGiven() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {1, 1, 1},
        });

        Assertions.assertDoesNotThrow(() -> UtilityOperationsMatrix.meanOneRow(matrix));
    }

    @Test
    void shouldReturnRightResultIfARowMatrixIsGiven() {
        double rightResult = 8.3333333;

        DMatrixRMaj matrixRow = new DMatrixRMaj(new double[][] {
                {10, 10, 5},
        });

        double result = UtilityOperationsMatrix.meanOneRow(matrixRow);

        assertEquals(rightResult, result, epsilon);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfRowOrColumnsAreNull() {
        int rows = 1;
        int columns = 0;
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> UtilityOperationsMatrix.ones(rows, columns));

        String msgErrorExpected = String.format("rows or columns must not be 0, current value row: %d, column: %d", rows, columns);
        assertEquals(msgErrorExpected, thrown.getMessage());
    }

    @Test
    void shouldReturnAOnesMatrix3x3() {
        int rows = 3;
        int columns = 3;

        DMatrixRMaj onesMatrix = UtilityOperationsMatrix.ones(rows, columns);
        double[] matrixData = onesMatrix.getData();

        for (double matrixNumber : matrixData) {
            assertEquals(1, matrixNumber);
        }
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfRowOrColumnsAreNullZero() {
        int rows = 1;
        int columns = 0;
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> UtilityOperationsMatrix.zeros(rows, columns));

        String msgErrorExpected = String.format("rows or columns must not be 0, current value row: %d, column: %d", rows, columns);
        assertEquals(msgErrorExpected, thrown.getMessage());
    }

    @Test
    void shouldReturnAZeroMatrix3x3() {
        int rows = 3;
        int columns = 3;

        DMatrixRMaj zeroMatrix = UtilityOperationsMatrix.zeros(rows, columns);
        double[] matrixData = zeroMatrix.getData();

        for (double matrixNumber : matrixData) {
            assertEquals(0, matrixNumber);
        }
    }

    @Test
    void shouldReturnAZeroMatrix() {
        DMatrixRMaj rightResult = UtilityOperationsMatrix.zeros(4, 4);
        DMatrixRMaj data = UtilityOperationsMatrix.ones(4, 4);

        DMatrixRMaj result = UtilityOperationsMatrix.scale(0f, data);
        EjmlUnitTests.assertEquals(rightResult, result);
    }

    @Test
    void shouldReturnA5Matrix() {
        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][]{
                {5, 5, 5},
                {5, 5, 5},
                {5, 5, 5},
        });
        DMatrixRMaj data = UtilityOperationsMatrix.ones(3, 3);

        DMatrixRMaj result = UtilityOperationsMatrix.scale(5f, data);
        EjmlUnitTests.assertEquals(rightResult, result);
    }

    @Test
    void shouldReturnAMaskSurcharge1() {
        float probabilities = 0.3F;
        int rows = 1000;
        int columns = 1000;

        DMatrixRMaj mask = UtilityOperationsMatrix.mask(probabilities, rows, columns);

        testMask(probabilities, mask);
    }

    @Test
    void shouldReturnAMaskSurcharge2() {
        float probabilities = 0.3F;
        int rows = 1000;
        int columns = 1000;

        DMatrixRMaj matrix = new DMatrixRMaj(rows, columns);

        DMatrixRMaj mask = UtilityOperationsMatrix.mask(probabilities, matrix);

        testMask(probabilities, mask);
    }

    @Test
    void shouldThrowAnExceptionIfNotARow() {
        DMatrixRMaj row = new DMatrixRMaj(new double[][]{
                {1, 1, 1},
                {1, 1, 1},
        });

        NotARowException thrown = Assertions.assertThrows(NotARowException.class, () -> UtilityOperationsMatrix.duplicateRow(row, 3));

        assertEquals("the matrix entered in parameter is not a row, current number of row " + row.getNumRows(), thrown.getMessage());
    }

    @Test
    void shouldReturnAMatrixWith3SameRow() {
        int numberOfRowsExpected = 3;

        DMatrixRMaj row = new DMatrixRMaj(new double[][]{
                {1, 1, 1},
        });

        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        });

        DMatrixRMaj result = UtilityOperationsMatrix.duplicateRow(row, numberOfRowsExpected);

        EjmlUnitTests.assertEquals(rightResult, result);
    }

    @Test
    void shouldGet5x5WithSamePatternMatrix() {
        DMatrixRMaj row = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
        });
        DMatrixRMaj row2 = new DMatrixRMaj(new double[][]{
                {5, 2, 8},
        });

        ArrayList<DMatrixRMaj> listMatrixData = new ArrayList<>();
        listMatrixData.add(row);
        listMatrixData.add(row);
        listMatrixData.add(row2);

        DMatrixRMaj matrix = UtilityOperationsMatrix.createMatrixFromListRowByRow(listMatrixData);
        DMatrixRMaj rightMatrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {1, 2, 3},
                {5, 2, 8}
        });

        EjmlUnitTests.assertEquals(rightMatrix, matrix);
    }

    private void testMask(float probabilities, DMatrixRMaj mask) {
        int numberOf0 = howMany0InMask(mask);
        int baseValueRange = Math.round(probabilities * mask.getNumElements());
        int errorRange = baseValueRange / 50;

        IntRange rangeOf0 = new IntRange(baseValueRange - errorRange, baseValueRange + errorRange);

        if (!rangeOf0.containsInteger(numberOf0))
            fail("the number of 0 in the mask does not match with the given probability");
    }

    private int howMany0InMask(DMatrixRMaj mask) {
        int counterOf0 = 0;
        double[] maskData = mask.getData();

        for (double maskNumber : maskData) {
            if (maskNumber == 0)
                counterOf0++;
        }

       return counterOf0;
    }
}
