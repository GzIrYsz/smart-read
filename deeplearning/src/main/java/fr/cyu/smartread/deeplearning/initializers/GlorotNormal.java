package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public class GlorotNormal extends RandomNormal {
    private static final long serialVersionUID = -6649467534539318886L;

    @Override
    public InitializerInterface init(DMatrixRMaj matrix) {
        int fanIn = matrix.getNumRows();
        int fanOut = matrix.getNumCols();
        double stDev = Math.sqrt(2.0 / (fanIn + fanOut));
        return super.init(matrix, stDev);
    }
}
