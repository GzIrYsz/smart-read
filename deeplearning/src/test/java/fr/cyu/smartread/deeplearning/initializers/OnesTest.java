package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Test;

class OnesTest {
    @Test
    public void shouldInitMatrixToOnes() {
        int shape = 10;
        DMatrixRMaj matrix = new DMatrixRMaj(new double[shape][5]);
        InitializerInterface initializer = new Ones();
        initializer.init(matrix);

        DMatrixRMaj result = new DMatrixRMaj(new double[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        });
        EjmlUnitTests.assertEquals(matrix, result);
    }
}