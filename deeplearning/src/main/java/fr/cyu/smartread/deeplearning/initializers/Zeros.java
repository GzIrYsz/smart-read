package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public class Zeros implements InitializerInterface {
    private static final long serialVersionUID = -4431939167394019122L;

    @Override
    public InitializerInterface init(DMatrixRMaj matrix) {
        matrix.fill(0);
        return this;
    }
}
