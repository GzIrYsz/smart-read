package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public class GlorotUniform extends RandomUniform {
    @Override
    public InitializerInterface init(DMatrixRMaj matrix) {
        int fanIn = matrix.getNumRows();
        int fanOut = matrix.getNumCols();
        double limit = Math.sqrt(6.0 / (fanIn + fanOut));
        return super.init(matrix, -limit, limit);
    }
}
