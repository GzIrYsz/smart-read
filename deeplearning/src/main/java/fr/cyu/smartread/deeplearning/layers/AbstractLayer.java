package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

public abstract class AbstractLayer {
    private DMatrixRMaj lastFeed;
    public abstract DMatrixRMaj rawCompute(DMatrixRMaj X);
    public abstract DMatrixRMaj computeActivation(DMatrixRMaj Z);
    public abstract DMatrixRMaj trainingComputeActivation(DMatrixRMaj Z);

    public DMatrixRMaj compute(DMatrixRMaj X) {
        DMatrixRMaj Z = rawCompute(X);

        return computeActivation(Z);
    }

    public DMatrixRMaj trainingCompute(DMatrixRMaj X) {
        DMatrixRMaj Z = rawCompute(X);
        DMatrixRMaj A = trainingComputeActivation(Z);
        setLastFeed(X);

        return A;
    }

    public ArrayList<DMatrixRMaj> get_DZ_DParams_derivative() throws NoTrainingComputationsPerformedException {
        if (lastFeed == null)
            throw new NoTrainingComputationsPerformedException();

        return compute_DZ_DParams_derivative();
    }

    public abstract ArrayList<DMatrixRMaj> compute_DZ_DParams_derivative();
    public abstract DMatrixRMaj get_DA_DZ_derivative() throws NoTrainingComputationsPerformedException;
    public abstract DMatrixRMaj get_DZ_DA_derivative() throws NoTrainingComputationsPerformedException;
    public abstract ArrayList<DMatrixRMaj> computeGradientWithDZ(DMatrixRMaj DZ) throws NoTrainingComputationsPerformedException;
    public DMatrixRMaj getLastFeed() {
        return lastFeed;
    }
    protected void setLastFeed(DMatrixRMaj lastFeed) {
        this.lastFeed = lastFeed;
    }
    public abstract int getTrainableParamsNumber();
    abstract public ArrayList<DMatrixRMaj> getTrainableParams();
    public void setTrainableParams(ArrayList<DMatrixRMaj> params) {
        if (NotTheRightNumberOfParameters(params.size()))
            throw new IllegalArgumentException("the number of parameters in the array is not equivalent to the number of parameters in the layer");
        setterTrainableParams(params);
    }
    public abstract void setterTrainableParams(ArrayList<DMatrixRMaj> params);

    private boolean NotTheRightNumberOfParameters(int nbParams) {
        return nbParams != getTrainableParamsNumber();
    }
}


