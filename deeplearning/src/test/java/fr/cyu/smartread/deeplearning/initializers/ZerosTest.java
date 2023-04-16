package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Test;

class ZerosTest {
    @Test
    public void shouldInitMatrixToZeros() {
        int shape = 10;
        DMatrixRMaj matrix = new DMatrixRMaj(new double[shape][5]);
        InitializerInterface initializer = new Zeros();
        initializer.init(matrix);

        DMatrixRMaj result = new DMatrixRMaj(new double[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        });
        EjmlUnitTests.assertEquals(matrix, result);
    }
}