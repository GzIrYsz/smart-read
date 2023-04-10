package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public class Zeros extends AbstractInitializer {
    public Zeros(int shape) {
        super(shape);
    }

    @Override
    public AbstractInitializer init(DMatrixRMaj matrix) {
        return this;
    }
}
