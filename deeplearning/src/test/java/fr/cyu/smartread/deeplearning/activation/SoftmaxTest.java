package fr.cyu.smartread.deeplearning.activation;

import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoftmaxTest {
    private AbstractActivation activation;
    final double epsilon = 0.0000001d;

    @BeforeEach
    public void setUp() {
       activation = new Softmax();
    }

    @Test
    void shouldReturnOneWhenWeUseScalar() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {5.2d}
        });

        DMatrixRMaj oneMatrix = new DMatrixRMaj(new double[][] {
                {1d}
        });

        DMatrixRMaj result = activation.compute(matrix);
        System.out.println(result);

        EjmlUnitTests.assertEquals(oneMatrix, result);
    }

    @Test
    void shouldReturnOneHalfOnTwoWith1x2MatrixWithSameNumber() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {5, 5}
        });
        DMatrixRMaj oneHalfMatrix = new DMatrixRMaj(new double[][] {
                {1/2d, 1/2d}
        });

        DMatrixRMaj result = activation.compute(matrix);

        EjmlUnitTests.assertEquals(oneHalfMatrix, result);
    }

    @Test
    void shouldReturnOneThirdWith1x3MatrixWithSameNumber() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {5, 5, 5}
        });
        DMatrixRMaj oneThirdMatrix = new DMatrixRMaj(new double[][] {
                {1/3d, 1/3d, 1/3d}
        });

        DMatrixRMaj result = activation.compute(matrix);

        EjmlUnitTests.assertEquals(oneThirdMatrix,result);
    }

    @Test
    void shouldReturnMatrixWithRowSumOfOne() {
        double[][] initMatrixData = getSomeData();
        DMatrixRMaj matrix = new DMatrixRMaj(initMatrixData);
        DMatrixRMaj result = activation.compute(matrix);

        assertRowSumEqualOne(result);
    }

    @Test
    void shouldLastComputeEqualToAttributeLastActivation(){
        double[][] initMatrixData = getSomeData();

        DMatrixRMaj matrix = new DMatrixRMaj(initMatrixData);
        DMatrixRMaj result = activation.trainingCompute(matrix);

        EjmlUnitTests.assertEquals(result, activation.getLastActivation());
    }

    @Test
    void shouldBeEqualToRightValueDerivativeForOnesMatrix3x3() throws NoTrainingComputationsPerformedException {
        DMatrixRMaj onesMatrix3x3 = new DMatrixRMaj(3, 3);
        CommonOps_DDRM.fill(onesMatrix3x3, 1);

        double rightResultValue = 0.22222222;
        DMatrixRMaj rightResultDerivative = new DMatrixRMaj(new double[][] {
                {rightResultValue, rightResultValue, rightResultValue},
                {rightResultValue, rightResultValue, rightResultValue},
                {rightResultValue, rightResultValue, rightResultValue}
        });

        DMatrixRMaj resultDerivative = getDerivative(onesMatrix3x3);
        EjmlUnitTests.assertEquals(rightResultDerivative, resultDerivative);
    }

    @Test
    void shouldReturnRightResultForDerivative() throws NoTrainingComputationsPerformedException {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 1, 1},
                {5, 5, 10},
                {13, 15.4, 2.3}
        });

        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][]{
                {2.22222222e-01, 2.22222222e-01, 2.22222222e-01},
                {6.60415386e-03, 6.60415386e-03, 1.31199065e-02},
                {7.62548690e-02, 7.62564322e-02, 1.87511625e-06},
        });


        DMatrixRMaj resultDerivative = getDerivative(matrix);

        EjmlUnitTests.assertEquals(rightResult, resultDerivative);
    }

    private void assertRowSumEqualOne(DMatrixRMaj matrix) {
        final int rowCounts = matrix.getNumRows();

        for (int currentRow = 0; currentRow < rowCounts; currentRow++) {
            DMatrixRMaj row = CommonOps_DDRM.extractRow(matrix, currentRow, null);

            double rowSum = CommonOps_DDRM.elementSum(row);

            assertEquals(1, rowSum, epsilon);
        }
    }

    private double[][] getSomeData() {
        return new double[][]{
                {5, 5, 5},
                {2, 4, 6},
                {3, 6, 0.5F}
        };
    }

    private DMatrixRMaj getDerivative(DMatrixRMaj matrix) throws NoTrainingComputationsPerformedException {
        activation.trainingCompute(matrix);
        return activation.get_DA_DZ_derivative();
    }
}
