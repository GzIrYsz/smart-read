package fr.cyu.smartread.deeplearning.loss;

import fr.cyu.smartread.deeplearning.activation.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractLossTest {

    private AbstractLoss loss;

    @BeforeEach
    public void setUp() {
        loss = new CategoricalCrossentropy();
    }

    @Test
    void shouldGetAnAssertionIfNoTrainingComputationPerformed() {
        NoTrainingComputationsPerformedException thrown = Assertions.assertThrows(NoTrainingComputationsPerformedException.class, () -> {
            loss.get_DZ_DX_derivative();
        });

        assertEquals("You have not performed any training calculation, please use method trainingComputation before using this function", thrown.getMessage());
    }

    @Test
    void shouldUpdateLastActionIfTrainingComputationPerformed() throws IncompatibleShapeException {
        DMatrixRMaj yLabel = new DMatrixRMaj(new double[][] {
                {0., 1, 0.}
        });

        DMatrixRMaj yPrediction = new DMatrixRMaj(new double[][] {
                {0.50, 0.25, 0.25}
        });

        Double result = loss.trainingCompute(yPrediction, yLabel);
        assertEquals(result, loss.getLastLoss());
    }
}
