package fr.cyu.smartread.deeplearning.losses;

import fr.cyu.smartread.deeplearning.UtilityOperations;
import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

public class CategoricalCrossentropy extends AbstractLoss {

    private static final long serialVersionUID = 7329358764693563549L;
    final private double epsilon = 1e-7;
    final private double max = 1 - epsilon;
    @Override
    public double computeRaw(DMatrixRMaj yPredBatch, DMatrixRMaj yLabelBatch) throws IllegalArgumentException {
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
     public DMatrixRMaj compute_DJ_DA_derivative() {
         final DMatrixRMaj lastPrediction = getLastPrediction();
         final DMatrixRMaj lastLabel = getLastLabel();

         DMatrixRMaj oneMinusLabel = CommonOps_DDRM.subtract(1., lastLabel, null);
         DMatrixRMaj oneMinusPred = CommonOps_DDRM.subtract(1., lastPrediction, null);

         DMatrixRMaj labelDividePred = CommonOps_DDRM.elementDiv(lastLabel, lastPrediction, null);
         DMatrixRMaj oneMinusLabelDivideOneMinusPred = CommonOps_DDRM.elementDiv(oneMinusLabel, oneMinusPred, null);

         return CommonOps_DDRM.subtract(labelDividePred, oneMinusLabelDivideOneMinusPred, null);
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
