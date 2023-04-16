package fr.cyu.smartread.deeplearning;

import org.ejml.data.DMatrixRMaj;

public class NotARowException extends IllegalArgumentException {
    public NotARowException(DMatrixRMaj row) {
        super(String.format("the matrix entered in parameter is not a row, current number of row %d", row.getNumRows()));
    }
}
