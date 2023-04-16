package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

public interface InitializerInterface {
    public InitializerInterface init(DMatrixRMaj matrix);
}
