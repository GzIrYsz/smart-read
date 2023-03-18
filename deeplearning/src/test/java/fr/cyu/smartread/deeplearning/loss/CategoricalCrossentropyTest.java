package fr.cyu.smartread.deeplearning.loss;

import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CategoricalCrossentropyTest {
    final double epsilon = 1e-7;
    private AbstractLoss loss;
    @BeforeEach
    public void setUp() {
        loss = new CategoricalCrossentropy();
    }

    @Test
    void shouldReturn1IfPredEqualToLabel() throws IncompatibleShapeException {
        final double goodResult = 1.0000000494736474e-07;
        DMatrixRMaj label = new DMatrixRMaj(new double[][] {
                {0, 1, 0}
        });

        DMatrixRMaj pred = new DMatrixRMaj(new double[][] {
                {0, 1, 0}
        });

        double result = loss.compute(pred, label);
        assertEquals(goodResult, result, epsilon);
    }

    @Test
    void shouldReturnGoodResultIfPredTotallyWrong() throws IncompatibleShapeException {
        final double goodResult = 16.11809565095832;

        DMatrixRMaj label = new DMatrixRMaj(new double[][] {
                {0, 1, 0}
        });

        DMatrixRMaj pred = new DMatrixRMaj(new double[][] {
                {0, 0, 1}
        });

        double result = loss.compute(pred, label);
        assertEquals(goodResult, result, epsilon);
    }

    @Test
    void shouldReturnGoodResultIfWithThisPredAndLabel() throws IncompatibleShapeException {
        final double goodResult = 3.912023005428146;

        DMatrixRMaj label = new DMatrixRMaj(new double[][] {
                {0, 1, 0}
        });

        DMatrixRMaj pred = new DMatrixRMaj(new double[][] {
                {0.88, 0.02, 0.10}
        });

        double result = loss.compute(pred, label);
        assertEquals(goodResult, result, epsilon);
    }

    @Test
    void shouldReturnGoodResultIfWithThisPredAndLabel2() throws IncompatibleShapeException {
        final double goodResult = 0.12783337150988489;

        DMatrixRMaj label = new DMatrixRMaj(new double[][] {
                {1, 0, 0}
        });

        DMatrixRMaj pred = new DMatrixRMaj(new double[][] {
                {0.88, 0.02, 0.10}
        });

        double result = loss.compute(pred, label);
        assertEquals(goodResult, result, epsilon);
    }

    @Test
    void shouldReturnGoodResultIfWith3x2Matrix() throws IncompatibleShapeException {
        final double goodResult = 2.557997904877041;

        DMatrixRMaj label = new DMatrixRMaj(new double[][] {
                {0, 1, 0},
                {1, 0, 0}
        });

        DMatrixRMaj pred = new DMatrixRMaj(new double[][] {
                {0.88, 0.02, 0.10},
                {0.30, 0.67, 0.03}
        });

        double result = loss.compute(pred, label);
        assertEquals(goodResult, result, epsilon);
    }

    @Test
    void shouldReturnGoodResult() throws IncompatibleShapeException {
        final double goodResult = 2.557997904877041;

        DMatrixRMaj label = new DMatrixRMaj(new double[][] {
                {0, 1, 0},
                {1, 0, 0}
        });

        DMatrixRMaj pred = new DMatrixRMaj(new double[][] {
                {0.88, 0.02, 0.10},
                {0.30, 0.67, 0.03}
        });

        double result = loss.trainingCompute(pred, label);
        assertEquals(goodResult, result, epsilon);
    }

    @Test
    void shouldUpdateLastLoss() throws IncompatibleShapeException {
        DMatrixRMaj label = new DMatrixRMaj(new double[][] {
                {0, 1, 0},
                {1, 0, 0}
        });

        DMatrixRMaj pred = new DMatrixRMaj(new double[][] {
                {0.88, 0.02, 0.10},
                {0.30, 0.67, 0.03}
        });

        double result = loss.trainingCompute(pred, label);
        assertEquals(result, loss.getLastLoss(), epsilon);
    }
}
