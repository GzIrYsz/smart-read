package fr.cyu.smartread.deeplearning.activation;

import org.ejml.data.DMatrixRMaj;

public class Relu extends AbstractActivation {
    @Override
    public DMatrixRMaj compute(DMatrixRMaj Z) {
        for (int i = 0; i < Z.getNumRows(); i++) {
            for (int j = 0; j < Z.getNumCols(); j++) {
                Z.set(i, j, Z.get(i, j) > 0 ? Z.get(i, j) : 0);
            }
        }
        return Z;
    }

    @Override
    public DMatrixRMaj compute_DA_DZ_derivative() {
        DMatrixRMaj lastActivation = getLastActivation();
        int lastActRowNumCount = lastActivation.getNumRows();
        int lastActColNumCount = lastActivation.getNumCols();
        DMatrixRMaj derivative = new DMatrixRMaj(lastActRowNumCount, lastActColNumCount);
        for (int i = 0; i < lastActRowNumCount; i++) {
            for (int j = 0; j < lastActColNumCount; j++) {
                derivative.set(i, j, lastActivation.get(i, j) > 0 ? 1 : 0);
            }
        }
        return derivative;
    }
}
