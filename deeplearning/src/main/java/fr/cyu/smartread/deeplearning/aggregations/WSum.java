package fr.cyu.smartread.deeplearning.aggregations;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

public class WSum {
    private DMatrixRMaj kernel;
    private DMatrixRMaj bias;
    private DMatrixRMaj inputs;

    public WSum(DMatrixRMaj kernel, DMatrixRMaj inputs) {
        this.kernel = kernel;
        this.inputs = inputs;
        DMatrixRMaj bias = new DMatrixRMaj(new double[kernel.getNumRows()][1]);
        bias.fill(0.0);
        this.bias = bias;
    }

    public WSum(DMatrixRMaj kernel, DMatrixRMaj inputs, DMatrixRMaj bias) {
        this(kernel, inputs);
        this.bias = bias;
    }

    public DMatrixRMaj compute() {
        int shape = kernel.numRows;
        DMatrixRMaj result = new DMatrixRMaj(new double[shape][1]);
        CommonOps_DDRM.mult(inputs, kernel, result);
        CommonOps_DDRM.add(result, bias, result);
        CommonOps_DDRM.sumCols(result, result);
        return result;
    }
}
