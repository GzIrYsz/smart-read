package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

public abstract class AbstractLayer {
    private DMatrixRMaj lastFeed;
    public abstract DMatrixRMaj compute(DMatrixRMaj Z);
    public DMatrixRMaj trainingCompute(DMatrixRMaj Z) {
        DMatrixRMaj result = compute(Z);
        setLastFeed(Z);

        return result;
    }
    public ArrayList<DMatrixRMaj> get_DZ_DParams_derivative() throws NoTrainingComputationsPerformedException {
        if (lastFeed == null)
            throw new NoTrainingComputationsPerformedException();

        return compute_DZ_DParams_derivative();
    }

    public abstract ArrayList<DMatrixRMaj> compute_DZ_DParams_derivative();
    public DMatrixRMaj getLastFeed() {
        return lastFeed;
    }
    protected void setLastFeed(DMatrixRMaj lastFeed) {
        this.lastFeed = lastFeed;
    }

    abstract public ArrayList<DMatrixRMaj> getParams();
}


