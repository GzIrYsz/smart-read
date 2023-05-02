package fr.cyu.smartread.deeplearning.gradient;

import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.activations.Relu;
import fr.cyu.smartread.deeplearning.activations.Softmax;
import fr.cyu.smartread.deeplearning.initializers.Ones;
import fr.cyu.smartread.deeplearning.layers.Dense;
import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.losses.CategoricalCrossentropy;
import fr.cyu.smartread.deeplearning.model.AbstractModel;
import fr.cyu.smartread.deeplearning.model.SequentialModel;
import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SequentialGradientComputerTestModel2 {
    @Test
    void testGradForTwoLayerOf3Neurons() throws NoTrainingComputationsPerformedException {
        AbstractModel model = new SequentialModel(
                new Dense(2, new Relu(), new Ones()),
                new Dense(3, new Softmax(), new Ones())
        );

        AbstractLoss loss = new CategoricalCrossentropy();

        GradientComputerAbstract gradientComputer = model.getGradientComputer();

        DMatrixRMaj xMatrix = new DMatrixRMaj(new double[][]{
                {0.7,0.3, 0.},
                {0.7,0.3, 0.},
        });

        DMatrixRMaj yLabel = new DMatrixRMaj(new double[][]{
                {1., 0., 0.},
                {1., 0., 0.},
        });

        DMatrixRMaj yPredMatrix = model.computeTrain(xMatrix);
        loss.trainingCompute(yPredMatrix, yLabel);


        ArrayList<ArrayList<DMatrixRMaj>> rightResult = getRightResultForOneLayer3Neurons();
        ArrayList<ArrayList<DMatrixRMaj>> result = gradientComputer.computeGradients(loss);

        //assertSameGradient(rightResult, result);
    }

    private ArrayList<ArrayList<DMatrixRMaj>> getRightResultForOneLayer3Neurons() {
        ArrayList<ArrayList<DMatrixRMaj>> modelGrads = new ArrayList<>();

        ArrayList<DMatrixRMaj> layerGrads = new ArrayList<>();

        DMatrixRMaj weightsGrads = new DMatrixRMaj(new double[][]{
                {-0.46666664,  0.23333333,  0.23333333},
                { 0.06666668, -0.43333328,  0.36666667},
                { 0.06666667, -0.13333333,  0.06666667}
        });

        DMatrixRMaj biasGrads = new DMatrixRMaj(new double[][]{
                {-0.33333328},
                {-0.33333328},
                {0.6666667},
        });

        layerGrads.add(weightsGrads);
        layerGrads.add(biasGrads);


        modelGrads.add(layerGrads);

        return modelGrads;
        //TODO le problème de conversion de double en float
    }

    private static void assertSameGradient(ArrayList<ArrayList<DMatrixRMaj>> expectedGrads, ArrayList<ArrayList<DMatrixRMaj>> resultGrads) {
        for (int i = 0; i < expectedGrads.size(); i++) {
            for (int j = 0; j < expectedGrads.get(i).size(); j++) {
                DMatrixRMaj expectedGradsMatrix = expectedGrads.get(i).get(j);
                DMatrixRMaj resultGradsMatrix = resultGrads.get(i).get(j);

                EjmlUnitTests.assertEquals(expectedGradsMatrix, resultGradsMatrix, 2);
            }
        }
    }
}
