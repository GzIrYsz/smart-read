package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public class Ones implements InitializerInterface {
    @Override
    public InitializerInterface init(DMatrixRMaj matrix) {
        matrix.fill(1);
        return this;
    }
}
