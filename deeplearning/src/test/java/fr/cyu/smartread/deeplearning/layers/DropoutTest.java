package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DropoutTest {

    private AbstractLayer layer;
    final private float probabilities = 0.2f;

    @BeforeEach
    public void setUp() {
        layer = new Dropout(probabilities);
    }

    @Test
    void shouldThrownAnExceptionIfProbabilitiesSuperiorTo1() {
        final float probabilities = 1;
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Dropout(probabilities));

        assertEquals(String.format("The probability must be located in the interval [0, 1[, but currently probabilities=%f", probabilities), thrown.getMessage());
    }

    @Test
    void shouldThrownAnExceptionIfProbabilitiesInferiorTo0() {
        final float probabilities = -0.1f;
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Dropout(probabilities));

        assertEquals(String.format("The probability must be located in the interval [0, 1[, but currently probabilities=%f", probabilities), thrown.getMessage());
    }

    @Test
    void shouldVerifyDeactivateNeuronsOnBatch() {
        DMatrixRMaj data = getData();

        DMatrixRMaj result = layer.rawCompute(data);
        verifyDeactivateNeuronsOnBatch(result);
        System.out.println(result);
    }

    @Test
    void shouldGetRightDerivative() {
        DMatrixRMaj data = getData();
        layer.rawCompute(data);

        DMatrixRMaj rightDerivative = UtilityOperationsMatrix.zeros(1, 1);
        DMatrixRMaj derivative = layer.compute_DZ_DParams_derivative().get(0);
        EjmlUnitTests.assertEquals(rightDerivative, derivative);
    }

    @Test
    void shouldGetRightDA_DZ_derivative() throws NoTrainingComputationsPerformedException {
        DMatrixRMaj data = getData();
        layer.rawCompute(data);

        DMatrixRMaj rightDerivative = UtilityOperationsMatrix.ones(1, 1);
        DMatrixRMaj derivative = layer.get_DA_DZ_derivative();

        EjmlUnitTests.assertEquals(rightDerivative, derivative);
    }

    @Test
    void shouldGetRightDZ_DA_derivative() throws NoTrainingComputationsPerformedException {
        DMatrixRMaj data = getData();
        layer.rawCompute(data);
        Dropout dropout = (Dropout) layer;

        DMatrixRMaj derivative = layer.get_DZ_DA_derivative();

        EjmlUnitTests.assertEquals(dropout.getCurrentMask(), derivative);
    }

    private void verifyDeactivateNeuronsOnBatch(DMatrixRMaj dropoutOutput) {
        ArrayList<Integer> indexOfDeactivateNeurons = findDeactivateNeurons(dropoutOutput);

        final int rowCounts = dropoutOutput.getNumRows();

        for (int currentRow = 0; currentRow < rowCounts; currentRow++) {
            DMatrixRMaj dropoutRow = CommonOps_DDRM.extractRow(dropoutOutput, currentRow, null);
            double[] rowData = dropoutRow.getData();

            for (Integer index: indexOfDeactivateNeurons) {
                assertEquals(0, rowData[index]);
            }
        }
    }

    private ArrayList<Integer> findDeactivateNeurons(DMatrixRMaj dropoutOutput) {
        DMatrixRMaj row = CommonOps_DDRM.extractRow(dropoutOutput, 0, null);
        ArrayList<Integer> indexDeactivateNeurons = new ArrayList<>();

        double[] rowData = row.getData();

        for (int i = 0; i < rowData.length; i++) {
            if (rowData[i] == 0)
                indexDeactivateNeurons.add(i);
        }

        return indexDeactivateNeurons;
    }

   private DMatrixRMaj getData() {
        return new DMatrixRMaj(new double[][] {
                {0.88, 0.02, 0.10, 0.23, 0.53, 0.22},
                {0.30, 0.67, 0.03, 0.23, 0.53, 0.22},
                {0.30, 0.67, 0.03, 0.23, 0.53, 0.22},
                {0.30, 0.67, 0.03, 0.23, 0.53, 0.22},
                {0.30, 0.67, 0.03, 0.23, 0.53, 0.22},
        });
   }
}