package fr.cyu.smartread.deeplearning.model;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.initializers.Ones;
import fr.cyu.smartread.deeplearning.layers.AbstractLayer;
import fr.cyu.smartread.deeplearning.layers.Dense;
import fr.cyu.smartread.deeplearning.layers.Dropout;
import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SequentialModelTest {
    private AbstractModel model;
    private AbstractLayer dense1;
    private AbstractLayer dense2;

    @BeforeEach
    void setUp() {
        dense1 = new Dense(5, new Ones());
        dense2 = new Dense(5, new Ones());

        ArrayList<AbstractLayer> layers = new ArrayList<>();
        layers.add(dense1);
        layers.add(dense2);

        model = new SequentialModel(null, layers);
    }

    @Test
    void predict() {
        DMatrixRMaj matrix = getData();

        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][]{
                {30, 30, 30, 30, 30},
                {45, 45, 45, 45, 45},
                {75, 75, 75, 75, 75}
        });

        DMatrixRMaj result = model.predict(matrix);
        EjmlUnitTests.assertEquals(rightResult, result);
    }

    @Test
    void computeTrain() {
        DMatrixRMaj matrix = getData();

        DMatrixRMaj rightResult = new DMatrixRMaj(new double[][]{
                {6, 6, 6, 6, 6},
                {9, 9, 9, 9, 9},
                {15, 15, 15, 15, 15}
        });

        model.computeTrain(matrix);
        DMatrixRMaj dense1LastFeed = model.getLayers().get(1).getLastFeed();
        EjmlUnitTests.assertEquals(rightResult, dense1LastFeed);
    }

    @Test
    void getParams() {
        DMatrixRMaj matrix = getData();
        model.predict(matrix);

        int i = 0;
        for (ArrayList<DMatrixRMaj> layerParam: model.getLayersParams()) {
            if (i == 0) {
                ArrayList<DMatrixRMaj> rightResult = dense1.getTrainableParams();
                EjmlUnitTests.assertEquals(rightResult.get(0), layerParam.get(0));
                EjmlUnitTests.assertEquals(rightResult.get(1), layerParam.get(1));
            } else {
                ArrayList<DMatrixRMaj> rightResult = dense2.getTrainableParams();
                EjmlUnitTests.assertEquals(rightResult.get(0), layerParam.get(0));
                EjmlUnitTests.assertEquals(rightResult.get(1), layerParam.get(1));
            }
            i++;
        }
    }

    @Test
    void testSetTrainableParams() {
        dense1 = new Dense(5);
        dense2 = new Dense(5);
        Dropout dropout = new Dropout(0.2f);

        ArrayList<AbstractLayer> layers = new ArrayList<>();
        layers.add(dense1);
        layers.add(dense2);
        layers.add(dropout);

        model = new SequentialModel(null, layers);
        model.predict(getData());

        ArrayList<ArrayList<DMatrixRMaj>> newModelParams = initialiseModelParams();
        model.setLayersTrainableParams(newModelParams);

        assertGoodParamModel(model, newModelParams);
    }

    @Test
    void testAssertSetTrainableParams() {
        dense1 = new Dense(5);
        dense2 = new Dense(5);

        ArrayList<AbstractLayer> layers = new ArrayList<>();
        layers.add(dense1);
        layers.add(dense2);

        model = new SequentialModel(null, layers);
        model.predict(getData());

        ArrayList<ArrayList<DMatrixRMaj>> newModelParams = initialiseModelParams();


        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> model.setLayersTrainableParams(newModelParams));

        assertEquals("the number of parameters is not equal to the number of parameters in the model", thrown.getMessage());
    }

    private static ArrayList<ArrayList<DMatrixRMaj>> initialiseModelParams() {

        ArrayList<ArrayList<DMatrixRMaj>> newModelParams = new ArrayList<>();
        ArrayList<DMatrixRMaj> dense1Params = new ArrayList<>();
        dense1Params.add(UtilityOperationsMatrix.ones(3, 5));
        dense1Params.add(UtilityOperationsMatrix.ones(5, 1));

        ArrayList<DMatrixRMaj> dense2Params = new ArrayList<>();
        dense2Params.add(UtilityOperationsMatrix.ones(5, 5));
        dense2Params.add(UtilityOperationsMatrix.ones(5, 1));

        ArrayList<DMatrixRMaj> dropoutParam = new ArrayList<>();

        newModelParams.add(dense1Params);
        newModelParams.add(dense2Params);
        newModelParams.add(dropoutParam);

        return newModelParams;
    }

    private static DMatrixRMaj getData() {
        return new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });
    }

    private static void assertGoodParamModel(AbstractModel model, ArrayList<ArrayList<DMatrixRMaj>> goodModelParams) {
        ArrayList<ArrayList<DMatrixRMaj>> modelParams = model.getLayersParams();

        for (int i = 0; i < modelParams.size(); i++) {
            ArrayList<DMatrixRMaj> layerParams = modelParams.get(i);
            ArrayList<DMatrixRMaj> goodLayerParams = goodModelParams.get(i);

            for (int j = 0; j < layerParams.size(); j++) {
                DMatrixRMaj param = layerParams.get(j);
                DMatrixRMaj goodParams = goodLayerParams.get(j);

                if (goodParams == null) {
                    assertNull(param);
                } else {
                    EjmlUnitTests.assertEquals(goodParams, param);
                }
            }
        }

    }

}










