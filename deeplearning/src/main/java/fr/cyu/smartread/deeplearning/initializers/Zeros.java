package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public class Zeros implements InitializerInterface {
    @Override
    public InitializerInterface init(DMatrixRMaj matrix) {
        matrix.fill(0);
        return this;
    }
}
