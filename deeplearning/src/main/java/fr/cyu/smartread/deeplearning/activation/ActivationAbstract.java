package fr.cyu.smartread.deeplearning.activation;

import org.ejml.data.DMatrixRMaj;

public abstract class ActivationAbstract {
    private DMatrixRMaj lastActivation;
   public abstract DMatrixRMaj compute(DMatrixRMaj Z);
    public abstract DMatrixRMaj trainingCompute(DMatrixRMaj Z);
    public abstract DMatrixRMaj get_DA_DZ_derivative();
    public DMatrixRMaj getLastActivation() {
        return lastActivation;
    }

    public void setLastActivation(DMatrixRMaj lastActivation) {
        this.lastActivation = lastActivation;
    }
}
