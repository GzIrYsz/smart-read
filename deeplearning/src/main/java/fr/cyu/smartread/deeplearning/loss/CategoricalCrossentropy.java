package fr.cyu.smartread.deeplearning.loss;

import fr.cyu.smartread.deeplearning.UtilityOperations;
import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

public class CategoricalCrossentropy extends AbstractLoss {

    final private double epsilon = 1e-7;
    final private double max = 1 - epsilon;
    @Override
    public double compute(DMatrixRMaj yPredBatch, DMatrixRMaj yLabelBatch) throws IncompatibleShapeException, IllegalArgumentException {
        if (!UtilityOperationsMatrix.areShapeEqual(yPredBatch, yLabelBatch))
            throw new IncompatibleShapeException(yPredBatch, yLabelBatch);

        DMatrixRMaj yPredClippedBatch = UtilityOperationsMatrix.clip(yPredBatch, epsilon, max);

        final int rowCounts = yPredClippedBatch.getNumRows();
        double[] batchLoss = new double[rowCounts];

        for (int currentRow = 0; currentRow < rowCounts; currentRow++) {
            DMatrixRMaj yPred = CommonOps_DDRM.extractRow(yPredClippedBatch, currentRow, null);
            DMatrixRMaj yLabel = CommonOps_DDRM.extractRow(yLabelBatch, currentRow, null);

            batchLoss[currentRow] = computeRowLoss(yPred, yLabel);
        }

        return batchLossMean(batchLoss);
    }

    @Override
    public Double compute_DZ_DX_derivative() {
        final Double lastLoss = getLastLoss();
        return lastLoss * (1 - lastLoss);
    }

    private double computeRowLoss(DMatrixRMaj yPredRow, DMatrixRMaj yLabelRow) {
        DMatrixRMaj logYPred = CommonOps_DDRM.elementLog(yPredRow, null);
        DMatrixRMaj productOfYLabelAndLogYPred = CommonOps_DDRM.elementMult(yLabelRow, logYPred, null);

        return - CommonOps_DDRM.elementSum(productOfYLabelAndLogYPred);
    }

    private double batchLossMean(double[] batchLoss) {
        return UtilityOperations.mean(batchLoss);
    }
}
