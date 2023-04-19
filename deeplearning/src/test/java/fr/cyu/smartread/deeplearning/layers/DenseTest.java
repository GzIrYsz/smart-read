package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.initializers.Ones;
import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Test;

class DenseTest {

    @Test
    void compute() {
        AbstractLayer layer = new Dense(5, new Ones());
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });

        DMatrixRMaj result = layer.compute(matrix);
        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][]{
                {6, 6, 6, 6, 6},
                {9, 9, 9, 9, 9},
                {15, 15, 15, 15, 15},
        });

        EjmlUnitTests.assertEquals(rightResult, result);
    }
}