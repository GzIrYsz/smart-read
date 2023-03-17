package fr.cyu.smartread.deeplearning.activation;

import org.ejml.data.DMatrixRMaj;

public abstract class AbstractActivation {
    private DMatrixRMaj lastActivation;
   public abstract DMatrixRMaj compute(DMatrixRMaj Z);
    public DMatrixRMaj trainingCompute(DMatrixRMaj Z) {
        DMatrixRMaj result = compute(Z);
        setLastActivation(result);

        return result;
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
