package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.activation.AbstractActivation;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

public class Dense extends AbstractLayer {
    private int neuronsNumber;
    private DMatrixRMaj biases;
    private DMatrixRMaj weights;
    private DMatrixRMaj parameters;

    public Dense(int neuronsNumber, AbstractActivation activation) {

    }

    @Override
    public DMatrixRMaj compute(DMatrixRMaj Z) {
        return null;
    }

    @Override
    public ArrayList<DMatrixRMaj> compute_DZ_DParams_derivative() {
        return null;
    }

    public DMatrixRMaj getBiases() {
        return biases;
    }

    public DMatrixRMaj getWeights() {
        return weights;
    }

    public DMatrixRMaj getParameters() {
        return parameters;
    }

    public void setParameters(DMatrixRMaj parameters) {
        this.parameters = parameters;
    }
}
