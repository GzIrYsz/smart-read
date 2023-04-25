package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractLayerTest {

    private AbstractLayer layer;

    @BeforeEach
    public void setUp() {
        layer = new Dropout(0.2F);
    }

    @Test
    void shouldGetAnAssertionIfNoTrainingComputationPerformed() {
        NoTrainingComputationsPerformedException thrown = Assertions.assertThrows(NoTrainingComputationsPerformedException.class, () -> layer.get_DZ_DParams_derivative());

        assertEquals("You have not performed any training calculation, please use method trainingComputation before using this function", thrown.getMessage());
    }

    @Test
    void shouldUpdateLastActionIfTrainingComputationPerformed() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {5, 5, 5}
        });

        layer.trainingCompute(matrix);
        EjmlUnitTests.assertEquals(matrix, layer.getLastFeed());
    }

    @Test
    void shouldGetAnInsertionIfLayerParamsSizeNotEqualToNewParamsSize() {
        ArrayList<DMatrixRMaj> newParams = new ArrayList<>();
        newParams.add(UtilityOperationsMatrix.ones(4, 4));

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> layer.setTrainableParams(newParams));

        assertEquals("the number of parameters in the array is not equivalent to the number of parameters in the layer", thrown.getMessage());
    }
}