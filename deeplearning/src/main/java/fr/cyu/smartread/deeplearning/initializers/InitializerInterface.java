package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;

import java.io.Serializable;

public interface InitializerInterface extends Serializable {
    public InitializerInterface init(DMatrixRMaj matrix);
}
