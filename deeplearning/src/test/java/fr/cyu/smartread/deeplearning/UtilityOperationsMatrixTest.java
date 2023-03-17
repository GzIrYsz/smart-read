package fr.cyu.smartread.deeplearning;

import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        NotARowException thrown = Assertions.assertThrows(NotARowException.class, () -> {
            UtilityOperationsMatrix.meanOneRow(matrix);
        });

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
}
