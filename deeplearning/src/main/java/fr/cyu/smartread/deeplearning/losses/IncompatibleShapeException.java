package fr.cyu.smartread.deeplearning.losses;

import org.ejml.data.DMatrixRMaj;

public class IncompatibleShapeException extends Exception {
    public IncompatibleShapeException(DMatrixRMaj A, DMatrixRMaj B) {
        super(String.format("The dimensions of matrices A with (%d, %d) shape and B with (%d, %d) shape are not compatible for the calculation you are performing", A.getNumRows(), A.getNumCols(), B.getNumRows(), B.getNumCols()));
    }
}
