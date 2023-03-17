package fr.cyu.smartread.deeplearning.activation;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

public class Softmax extends AbstractActivation {
    private final SimpleOperations_DDRM simpleOperation = new SimpleOperations_DDRM();
    @Override
    public DMatrixRMaj compute(DMatrixRMaj Z) {  // Vn = exp(xn) / 1 + sum(exp(x1), exp(x2), ..., exp(xn))
        DMatrixRMaj activation = new DMatrixRMaj(Z);
        final int rowCounts = Z.getNumRows();
        final int startColumn = 0;

        for (int currentRow = 0; currentRow < rowCounts; currentRow++) {
            DMatrixRMaj row = CommonOps_DDRM.extractRow(Z, currentRow, null);
            CommonOps_DDRM.elementExp(row, row);

            double rowSum = CommonOps_DDRM.elementSum(row);
            CommonOps_DDRM.divide(row, rowSum, row);

            simpleOperation.setRow(activation, currentRow, startColumn, row.getData());
        }

        return activation;
    }

    @Override
    public DMatrixRMaj get_DA_DZ_derivative() {
        DMatrixRMaj lastActivation = getLastActivation();

        DMatrixRMaj intermediateComputation = CommonOps_DDRM.subtract(1, lastActivation, null);

        return CommonOps_DDRM.elementMult(lastActivation, intermediateComputation, null);
    }

}
