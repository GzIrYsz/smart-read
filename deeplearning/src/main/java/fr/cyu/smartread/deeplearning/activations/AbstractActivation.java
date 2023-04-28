package fr.cyu.smartread.deeplearning.activations;

import org.ejml.data.DMatrixRMaj;

public abstract class AbstractActivation {
    private DMatrixRMaj lastActivation;
   public abstract DMatrixRMaj compute(DMatrixRMaj Z);
    public DMatrixRMaj trainingCompute(DMatrixRMaj Z) {
        DMatrixRMaj activation = compute(Z);
        setLastActivation(activation);

        return activation;
    }
    public DMatrixRMaj get_DA_DZ_derivative() throws NoTrainingComputationsPerformedException {
        if (lastActivation == null)
            throw new NoTrainingComputationsPerformedException();
        
        return compute_DA_DZ_derivative();
    }
    
    public abstract DMatrixRMaj compute_DA_DZ_derivative();
    public DMatrixRMaj getLastActivation() {
        return lastActivation;
    }
    public void setLastActivation(DMatrixRMaj lastActivation) {
        this.lastActivation = lastActivation;
    }
}
