package fr.cyu.smartread.deeplearning.loss;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activation.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;

public abstract class AbstractLoss {
    private DMatrixRMaj lastPrediction;
    private DMatrixRMaj lastLabel;

    public double compute(DMatrixRMaj yPrediction, DMatrixRMaj yLabel) throws IncompatibleShapeException {
        if (!UtilityOperationsMatrix.areShapeEqual(yPrediction, yLabel))
            throw new IncompatibleShapeException(yPrediction, yLabel);

        return computeRaw(yPrediction, yLabel);
    }
    public abstract double computeRaw(DMatrixRMaj yPrediction, DMatrixRMaj yLabel);
    public double trainingCompute(DMatrixRMaj yPrediction, DMatrixRMaj yLabel) throws IncompatibleShapeException{
        double result = compute(yPrediction, yLabel);
        setLastPrediction(yPrediction);
        setLastLabel(yLabel);

        return result;
    }

    public DMatrixRMaj get_DJ_DA_derivative() throws NoTrainingComputationsPerformedException {
        if (lastPrediction == null || lastLabel == null)
            throw new NoTrainingComputationsPerformedException();

        return compute_DJ_DA_derivative();
    }

    public abstract DMatrixRMaj compute_DJ_DA_derivative();
    public DMatrixRMaj getLastPrediction() {
        return lastPrediction;
    }
    public void setLastPrediction(DMatrixRMaj lastPrediction) {
        this.lastPrediction = lastPrediction;
    }
    public DMatrixRMaj getLastLabel() {
        return lastLabel;
    }
    public void setLastLabel(DMatrixRMaj lastLabel) {
        this.lastLabel = lastLabel;
    }
}
