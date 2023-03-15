package fr.cyu.smartread.deeplearning.activation;

import org.ejml.data.DMatrixRMaj;

public abstract class ActivationAbstract {
    private DMatrixRMaj lastActivation;
   public abstract DMatrixRMaj compute(DMatrixRMaj Z);
    public DMatrixRMaj trainingCompute(DMatrixRMaj Z) {
        DMatrixRMaj result = compute(Z);
        setLastActivation(result);

        return result;
    }
    public abstract DMatrixRMaj get_DA_DZ_derivative();
    public DMatrixRMaj getLastActivation() {
        return lastActivation;
    }
    public void setLastActivation(DMatrixRMaj lastActivation) {
        this.lastActivation = lastActivation;
    }
}
