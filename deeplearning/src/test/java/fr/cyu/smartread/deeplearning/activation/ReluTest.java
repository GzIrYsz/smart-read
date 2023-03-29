package fr.cyu.smartread.deeplearning.activation;

import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReluTest {
    private AbstractActivation activation;

    @BeforeEach
    void setUp() {
        activation = new Relu();
    }

    @Test
    public void shouldReturnSameScalarIfPositiveGiven() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {6}
        });

        DMatrixRMaj result = activation.compute(matrix);

        EjmlUnitTests.assertEquals(matrix, result);
    }

    @Test
    public void shouldReturnSameMatrixIfEverythingIsPositive() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {1, 6, 9, 18, 12.8, 65.98}
        });

        DMatrixRMaj result = activation.compute(matrix);

        EjmlUnitTests.assertEquals(matrix, result);
    }

    @Test
    public void shouldReturn0IfNegativeScalarGiven() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {-5}
        });
        DMatrixRMaj nullMatrix = new DMatrixRMaj(new double[][]{
                {0}
        });

        DMatrixRMaj result = activation.compute(matrix);

        EjmlUnitTests.assertEquals(nullMatrix, result);
    }

    @Test
    public void shouldReturnNullMatrixIfNegativeMatrixIsGiven() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {-6, -1, 0, -85.6, -9}
        });
        DMatrixRMaj nullMatrix = new DMatrixRMaj(new double[][]{
                {0, 0, 0, 0, 0}
        });

        DMatrixRMaj result = activation.compute(matrix);

        EjmlUnitTests.assertEquals(nullMatrix, result);
    }

    @Test
    public void shouldReturnAccurateMatrixIfMixedMatrixIsGiven() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {5, 0, 9, 6.35, -7, -9.25, 18, 0.2, -1, 84}
        });
        DMatrixRMaj expectedResult = new DMatrixRMaj(new double[][]{
                {5, 0, 9, 6.35, 0, 0, 18, 0.2, 0, 84}
        });

        DMatrixRMaj result = activation.compute(matrix);
        
        EjmlUnitTests.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturn1ForDerivativeIfPositiveValue() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {5}
        });
        DMatrixRMaj expectedDerivative = new DMatrixRMaj(new double[][]{
                {1}
        });

        activation.trainingCompute(matrix);
        DMatrixRMaj derivative = activation.compute_DA_DZ_derivative();

        EjmlUnitTests.assertEquals(expectedDerivative, derivative);
    }

    @Test
    public void shouldReturn0ForDerivativeIfNegativeValue() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {-9.5}
        });
        DMatrixRMaj expectedDerivative = new DMatrixRMaj(new double[][]{
                {0}
        });

        activation.trainingCompute(matrix);
        DMatrixRMaj derivative = activation.compute_DA_DZ_derivative();

        EjmlUnitTests.assertEquals(expectedDerivative, derivative);
    }

    @Test
    public void shouldReturnExpectedDerivative() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {5, 6, 3.25, -1, 63.102, -7.025}
        });
        DMatrixRMaj expectedDerivative = new DMatrixRMaj(new double[][]{
                {1, 1, 1, 0, 1, 0}
        });

        activation.trainingCompute(matrix);
        DMatrixRMaj derivative = activation.compute_DA_DZ_derivative();
        EjmlUnitTests.assertEquals(expectedDerivative, derivative);
    }
}