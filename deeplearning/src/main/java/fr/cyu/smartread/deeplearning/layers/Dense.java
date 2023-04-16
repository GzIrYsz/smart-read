package fr.cyu.smartread.deeplearning.layers;

import com.google.crypto.tink.proto.Common;
import fr.cyu.smartread.deeplearning.activations.AbstractActivation;
import fr.cyu.smartread.deeplearning.activations.Relu;
import fr.cyu.smartread.deeplearning.initializers.InitializerInterface;
import fr.cyu.smartread.deeplearning.initializers.GlorotUniform;
import fr.cyu.smartread.deeplearning.initializers.Zeros;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

import java.util.ArrayList;

public class Dense extends AbstractLayer {
    private boolean isInit = false;
    private int shape;
    private AbstractActivation activation;
    private InitializerInterface weightsInitializer;
    private InitializerInterface biasInitializer;
    private DMatrixRMaj bias;
    private DMatrixRMaj weights;
    private DMatrixRMaj parameters;
    private SimpleOperations_DDRM simpleOps = new SimpleOperations_DDRM();

    public Dense(int shape) {
        this(shape, new Relu());
    }

    public Dense(int shape, AbstractActivation activation) {
        this(shape, activation, new GlorotUniform());
    }

    public Dense(int shape, InitializerInterface weightsInitializer) {
        this(shape, new Relu(), weightsInitializer);
    }

    public Dense(int shape, AbstractActivation activation, InitializerInterface weightsInitializer) {
        this.shape = shape;
        this.activation = activation;
        this.weightsInitializer = weightsInitializer;
        this.biasInitializer = new Zeros();
    }

    private void initLayer(DMatrixRMaj matrix) {
        this.bias = new DMatrixRMaj(new double[this.shape][1]);
        this.weights = new DMatrixRMaj(new double[matrix.getNumCols()][this.shape]);
        this.biasInitializer.init(this.bias);
        this.weightsInitializer.init(this.weights);
    }

    @Override
    public DMatrixRMaj compute(DMatrixRMaj Z) {
        if (!isInit) {
            initLayer(Z);
        }

        int startRow = 0;
        DMatrixRMaj out = new DMatrixRMaj(new double[Z.getNumRows()][this.weights.getNumCols()]);
        CommonOps_DDRM.transpose(out);
        CommonOps_DDRM.mult(Z, this.weights, out);
        for (int i = 0; i < out.getNumCols(); i++) {
            DMatrixRMaj currentCol = CommonOps_DDRM.extractColumn(out, i, null);
            currentCol = CommonOps_DDRM.add(currentCol, this.bias, null);
            this.simpleOps.setColumn(out, i, startRow, currentCol.getData());
        }
        return this.activation.compute(out);
    }

    @Override
    public ArrayList<DMatrixRMaj> compute_DZ_DParams_derivative() {
        return null;
    }

    public DMatrixRMaj getBias() {
        return bias;
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
