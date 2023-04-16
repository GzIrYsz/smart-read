package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public abstract class AbstractInitializer {
    int shape;

    public AbstractInitializer(int shape) {
        this.shape = shape;
    }

    public abstract AbstractInitializer init(DMatrixRMaj matrix);
}
