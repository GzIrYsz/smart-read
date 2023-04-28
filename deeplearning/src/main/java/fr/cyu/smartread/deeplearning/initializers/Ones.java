package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public class Ones implements InitializerInterface {
    private static final long serialVersionUID = 2941017044554452558L;

    @Override
    public InitializerInterface init(DMatrixRMaj matrix) {
        matrix.fill(1);
        return this;
    }
}
