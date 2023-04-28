package fr.cyu.smartread.deeplearning.losses;

import fr.cyu.smartread.deeplearning.IncompatibleShapeException;
import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;

import java.io.Serializable;

public abstract class AbstractLoss implements Serializable {
    private static final long serialVersionUID = 1996703775968613670L;
    private DMatrixRMaj lastPrediction;
    private DMatrixRMaj lastLabel;

    public double compute(DMatrixRMaj yPrediction, DMatrixRMaj yLabel) throws IncompatibleShapeException {
        if (!UtilityOperationsMatrix.areShapeEqual(yPrediction, yLabel)) {
            throw new IncompatibleShapeException(yPrediction, yLabel);
        }

        return computeRaw(yPrediction, yLabel);
    }
    public abstract double computeRaw(DMatrixRMaj yPrediction, DMatrixRMaj yLabel);
    public double trainingCompute(DMatrixRMaj yPrediction, DMatrixRMaj yLabel) {
        double result;
        try {
            result = compute(yPrediction, yLabel);
        } catch (IncompatibleShapeException e) {
            throw new RuntimeException(e);
        }
        setLastPrediction(yPrediction);
        setLastLabel(yLabel);

        return result;
    }

    public DMatrixRMaj get_DJ_DA_derivative() throws NoTrainingComputationsPerformedException {
        if (lastPrediction == null || lastLabel == null) {
            throw new NoTrainingComputationsPerformedException();
        }

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
