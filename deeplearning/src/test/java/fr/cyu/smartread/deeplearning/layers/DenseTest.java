package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.AbstractActivation;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.activations.Softmax;
import fr.cyu.smartread.deeplearning.initializers.Ones;
import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DenseTest {

    @Test
    void compute() {
        AbstractLayer layer = new Dense(5, new Ones());
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });

        DMatrixRMaj result = layer.rawCompute(matrix);
        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][]{
                {6, 6, 6, 6, 6},
                {9, 9, 9, 9, 9},
                {15, 15, 15, 15, 15},
        });

        EjmlUnitTests.assertEquals(rightResult, result);
    }

    @Test
    public void testGet_DA_DZ_derivative() throws NoTrainingComputationsPerformedException {
        AbstractActivation activation = new Softmax();
        AbstractLayer layer = new Dense(5, activation);

        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });

        layer.trainingCompute(matrix);

        System.out.println(activation.get_DA_DZ_derivative());
        System.out.println(layer.get_DA_DZ_derivative());
        EjmlUnitTests.assertEquals(activation.get_DA_DZ_derivative(), layer.get_DA_DZ_derivative());
    }

    @Test
    public void testGet_DZ_DA_derivative() {
        Dense dense = new Dense(5);
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });

        dense.rawCompute(matrix);
        EjmlUnitTests.assertEquals(dense.getWeights(), dense.get_DZ_DA_derivative());

    }

    @Test
    void testComputeActivation() {
        AbstractActivation activation = new Softmax();
        AbstractLayer layer = new Dense(5, activation);
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });

        EjmlUnitTests.assertEquals(activation.compute(matrix), layer.computeActivation(matrix));
    }

    @Test
    void testTrainingComputeActivation() throws NoTrainingComputationsPerformedException {
        AbstractActivation activation = new Softmax();
        AbstractLayer layer = new Dense(5, activation);
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });

        activation.trainingCompute(matrix);
        layer.trainingCompute(matrix);


        EjmlUnitTests.assertEquals(activation.get_DA_DZ_derivative(), layer.get_DA_DZ_derivative());
    }

    @Test
    void testCompute_DZ_DParams_derivative() throws NoTrainingComputationsPerformedException {
        AbstractActivation activation = new Softmax();
        int nbNeurons = 5;
        Dense layer = new Dense(nbNeurons, activation);
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });


        layer.trainingCompute(matrix);

        ArrayList<DMatrixRMaj> derivatives = layer.get_DZ_DParams_derivative();
        DMatrixRMaj biasDerivatives = UtilityOperationsMatrix.ones(nbNeurons, 1);

        EjmlUnitTests.assertEquals(layer.getLastFeed(), derivatives.get(0));
        EjmlUnitTests.assertEquals(biasDerivatives, derivatives.get(1));
    }

    @Test
    void testCompute_DZ_DA_derivative() {
        Dense layer = new Dense(5, new Softmax());
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });

        layer.trainingCompute(matrix);
        EjmlUnitTests.assertEquals(layer.getWeights(), layer.get_DZ_DA_derivative());
    }


    @Test
    void testGeBias() {
        Dense layer = new Dense(5, new Softmax());

        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, layer::getBias);

        assertEquals("Make calculations before performing this operation", thrown.getMessage());
    }

    @Test
    void testGetWeights() {
        Dense layer = new Dense(5, new Softmax());

        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, layer::getWeights);

        assertEquals("Make calculations before performing this operation", thrown.getMessage());
    }
}