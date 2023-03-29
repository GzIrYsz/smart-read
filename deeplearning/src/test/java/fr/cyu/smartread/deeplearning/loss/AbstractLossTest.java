//package fr.cyu.smartread.deeplearning.loss;
//
//import fr.cyu.smartread.deeplearning.activation.NoTrainingComputationsPerformedException;
//import org.ejml.data.DMatrixRMaj;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AbstractLossTest {
//
//    private AbstractLoss loss;
//
//    @BeforeEach
//    public void setUp() {
//        loss = new CategoricalCrossentropy();
//    }
//
//    @Test
//    void shouldGetAnAssertionIfNoTrainingComputationPerformed() {
//        NoTrainingComputationsPerformedException thrown = Assertions.assertThrows(NoTrainingComputationsPerformedException.class, () -> {
//            loss.get_DZ_DX_derivative();
//        });
//
//        assertEquals("You have not performed any training calculation, please use method trainingComputation before using this function", thrown.getMessage());
//    }
//
//    @Test
//    void shouldUpdateLastActionIfTrainingComputationPerformed() throws IncompatibleShapeException {
//        DMatrixRMaj yLabel = new DMatrixRMaj(new double[][] {
//                {0., 1, 0.}
//        });
//
//        DMatrixRMaj yPrediction = new DMatrixRMaj(new double[][] {
//                {0.50, 0.25, 0.25}
//        });
//
//        Double result = loss.trainingCompute(yPrediction, yLabel);
//        assertEquals(result, loss.getLastLoss());
//    }
//}

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
    void shouldGetAnAssertionIfIncompatibleShapeException() {
        DMatrixRMaj label = new DMatrixRMaj(new double[][] {
                {0, 1, 0},
                {1, 0, 0},
                {1, 0, 0}
        });

        DMatrixRMaj pred = new DMatrixRMaj(new double[][] {
                {0.88, 0.02, 0.10},
                {0.30, 0.67, 0.03}
        });

        IncompatibleShapeException thrown = Assertions.assertThrows(IncompatibleShapeException.class, () -> {
            loss.compute(pred, label);
        });

        String errorMsgExpected = String.format("The dimensions of matrices A with (%d, %d) shape and B with (%d, %d) shape are not compatible for the calculation you are performing", pred.getNumRows(), pred.getNumCols(), label.getNumRows(), label.getNumCols());

        assertEquals(errorMsgExpected, thrown.getMessage());
    }
    @Test
    void shouldGetAnAssertionIfNoTrainingComputationPerformed() {
        NoTrainingComputationsPerformedException thrown = Assertions.assertThrows(NoTrainingComputationsPerformedException.class, () -> {
            loss.get_DJ_DA_derivative();
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

        loss.trainingCompute(yPrediction, yLabel);
        assertEquals(yLabel, loss.getLastLabel());
        assertEquals(yPrediction, loss.getLastPrediction());
    }
}
