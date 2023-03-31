package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.activation.NoTrainingComputationsPerformedException;
import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractLayerTest {

    private AbstractLayer layer;

    @BeforeEach
    public void setUp() {
        layer = new Dropout(0.2F);
    }

    @Test
    void shouldGetAnAssertionIfNoTrainingComputationPerformed() {
        NoTrainingComputationsPerformedException thrown = Assertions.assertThrows(NoTrainingComputationsPerformedException.class, () -> {
            layer.get_DZ_DParams_derivative();
        });

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
}