package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

import java.util.ArrayList;

public class Dropout extends AbstractLayer {
    private static final long serialVersionUID = 8241839062757443669L;
    private final float probabilities;
    private DMatrixRMaj currentMask;

    private DMatrixRMaj lastRawComputedResult = null;
    private final static DMatrixRMaj scalarOneMatrix = UtilityOperationsMatrix.ones(1, 1);

    public Dropout(float probabilities) {
        if (!isProbabilitiesInGoodRange(probabilities))
            throw new IllegalArgumentException(String.format("The probability must be located in the interval [0, 1[, but currently probabilities=%f", probabilities));
        this.probabilities = probabilities;
    }

    @Override
    public DMatrixRMaj rawCompute(DMatrixRMaj X) {
        DMatrixRMaj maskOneRow = UtilityOperationsMatrix.mask(probabilities, 1, X.getNumCols());
        DMatrixRMaj mask =  createMask(maskOneRow, X.getNumRows());
        DMatrixRMaj result = CommonOps_DDRM.elementMult(mask, X, null);

        setCurrentMask(mask);
        setLastRawComputedResult(result);

        return result;
    }

    @Override
    public DMatrixRMaj computeActivation(DMatrixRMaj Z) {
        return getLastRawComputedResult();
    }

    @Override
    public DMatrixRMaj trainingComputeActivation(DMatrixRMaj Z) {
        return computeActivation(Z);
    }

    @Override
    public DMatrixRMaj get_DA_DZ_derivative() {
        return scalarOneMatrix;
    }

    @Override
    public DMatrixRMaj get_DZ_DA_derivative() {
        return getCurrentMask();
    }

    @Override
    public int getTrainableParamsNumber() {
        return 0;
    }

    @Override
    public ArrayList<DMatrixRMaj> compute_DZ_DParams_derivative() {
        ArrayList<DMatrixRMaj> arrayDerivative = new ArrayList<>();
        arrayDerivative.add(UtilityOperationsMatrix.zeros(1, 1));

        return arrayDerivative;
    }

    @Override
    public ArrayList<DMatrixRMaj> computeGradientWithDZ(DMatrixRMaj DZ) throws NoTrainingComputationsPerformedException {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<DMatrixRMaj> getTrainableParams() {
        return new ArrayList<>();
    }

    @Override
    public void setterTrainableParams(ArrayList<DMatrixRMaj> params) {
        if (params.size() != 0)
            System.out.println("WARNING: Dropout Layer doesn't take params");
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

    private DMatrixRMaj getLastRawComputedResult() {
        if (lastRawComputedResult == null)
            throw new IllegalStateException("Make calculations before performing this operation");
        return lastRawComputedResult;
    }

    private void setLastRawComputedResult(DMatrixRMaj lastRawComputedResult) {
        this.lastRawComputedResult = lastRawComputedResult;
    }
}
