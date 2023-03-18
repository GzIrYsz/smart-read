package fr.cyu.smartread.deeplearning.loss;

import fr.cyu.smartread.deeplearning.activation.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;

public abstract class AbstractLoss {
    private Double lastLoss;

    public abstract double compute(DMatrixRMaj yPrediction, DMatrixRMaj yLabel) throws IncompatibleShapeException;
    public double trainingCompute(DMatrixRMaj yPrediction, DMatrixRMaj yLabel) throws IncompatibleShapeException{
        double result = compute(yPrediction, yLabel);
        setLastLoss(result);

        return result;
    }

    public Double get_DZ_DX_derivative() throws NoTrainingComputationsPerformedException {
        if (lastLoss == null)
            throw new NoTrainingComputationsPerformedException();

        return compute_DZ_DX_derivative();
    }

    public abstract Double compute_DZ_DX_derivative();
    public Double getLastLoss() {
        return lastLoss;
    }
    public void setLastLoss(double lastLoss) {
        this.lastLoss = lastLoss;
    }
}
