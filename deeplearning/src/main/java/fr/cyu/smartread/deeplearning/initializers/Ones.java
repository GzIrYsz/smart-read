package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public class Ones extends AbstractInitializer {
    public Ones(int shape) {
        super(shape);
    }

    @Override
    public AbstractInitializer init(DMatrixRMaj matrix) {
        matrix.fill(1);
        return null;
    }
}
