package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public class GlorotNormal extends RandomNormal {
    @Override
    public InitializerInterface init(DMatrixRMaj matrix) {
        int fanIn = matrix.getNumRows();
        int fanOut = matrix.getNumCols();
        double stDev = Math.sqrt(2.0 / (fanIn + fanOut));
        return super.init(matrix, stDev);
    }
}
