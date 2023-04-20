package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

import java.util.ArrayList;

public class Dropout extends AbstractLayer {
    private final float probabilities;
    private DMatrixRMaj currentMask;

    public Dropout(float probabilities) {
        if (!isProbabilitiesInGoodRange(probabilities))
            throw new IllegalArgumentException(String.format("The probability must be located in the interval [0, 1[, but currently probabilities=%f", probabilities));
        this.probabilities = probabilities;
    }

    @Override
    public DMatrixRMaj compute(DMatrixRMaj Z) {
        DMatrixRMaj maskOneRow = UtilityOperationsMatrix.mask(probabilities, 1, Z.getNumCols());
        DMatrixRMaj mask =  createMask(maskOneRow, Z.getNumRows());
        setCurrentMask(mask);

        return CommonOps_DDRM.elementMult(mask, Z, null);
    }

    @Override
    public ArrayList<DMatrixRMaj> compute_DZ_DParams_derivative() {
        ArrayList<DMatrixRMaj> arrayDerivative = new ArrayList<>();
        arrayDerivative.add(getCurrentMask());

        return arrayDerivative;
    }

    @Override
    public ArrayList<DMatrixRMaj> getParams() {
        ArrayList<DMatrixRMaj> params = new ArrayList<>();
        params.add(currentMask);

        return params;
    }

    private DMatrixRMaj createMask(DMatrixRMaj oneRowMask, int numberOfRowsExpected) {
        return UtilityOperationsMatrix.duplicateRow(oneRowMask, numberOfRowsExpected);
    }

    private boolean isProbabilitiesInGoodRange(float probabilities) {
        return probabilities >= 0 && probabilities < 1;
    }

    public void setCurrentMask(DMatrixRMaj currentMask) {
        this.currentMask = currentMask;
    }

    public DMatrixRMaj getCurrentMask() {
        return currentMask;
    }
}
