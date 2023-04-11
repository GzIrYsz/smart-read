package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.dense.row.RandomMatrices_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

import java.util.Random;

public class GlorotNormal extends RandomNormal {
    public GlorotNormal(int shape, int fanIn, int fanOut) {
        super(shape, Math.sqrt(2.0 / (fanIn + fanOut)));
    }

    @Override
    public AbstractInitializer init(DMatrixRMaj matrix) {
        return super.init(matrix);
    }
}
