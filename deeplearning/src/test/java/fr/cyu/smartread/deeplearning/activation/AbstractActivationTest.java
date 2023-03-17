package fr.cyu.smartread.deeplearning.activation;

import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractActivationTest {
    private AbstractActivation activation;

    @BeforeEach
    public void setUp() {
        activation = new Softmax();
    }

    @Test
    void shouldGetAnAssertionIfNoTrainingComputationPerformed() {
        NoTrainingComputationsPerformedException thrown = Assertions.assertThrows(NoTrainingComputationsPerformedException.class, () -> {
            activation.get_DA_DZ_derivative();
        });

        assertEquals("You have not performed any training calculation, please use method trainingComputation before using this function", thrown.getMessage());
    }

    @Test
    void shouldUpdateLastActionIfTrainingComputationPerformed() {
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][] {
                {5, 5, 5}
        });

       DMatrixRMaj result = activation.trainingCompute(matrix);
        EjmlUnitTests.assertEquals(result, activation.getLastActivation());
    }

}
