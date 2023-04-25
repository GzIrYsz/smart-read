package fr.cyu.smartread.deeplearning.model;

import fr.cyu.smartread.deeplearning.initializers.Ones;
import fr.cyu.smartread.deeplearning.layers.AbstractLayer;
import fr.cyu.smartread.deeplearning.layers.Dense;
import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });

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
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });

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
        DMatrixRMaj matrix = new DMatrixRMaj(new double[][]{
                {1, 2, 3},
                {3, 3, 3},
                {5, 5, 5},
        });
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
}