package fr.cyu.smartread.deeplearning.layers;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.AbstractActivation;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.activations.Relu;
import fr.cyu.smartread.deeplearning.initializers.GlorotUniform;
import fr.cyu.smartread.deeplearning.initializers.InitializerInterface;
import fr.cyu.smartread.deeplearning.initializers.Zeros;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

import java.util.ArrayList;

public class Dense extends AbstractLayer {
    private boolean isInit = false;
    private final int nbNeurons;
    private final AbstractActivation activation;
    private final InitializerInterface weightsInitializer;
    private final InitializerInterface biasInitializer;
    private DMatrixRMaj bias;
    private DMatrixRMaj weights;
    private final SimpleOperations_DDRM simpleOps = new SimpleOperations_DDRM();


    public Dense(int nbNeurons) {
        this(nbNeurons, new Relu());
    }

    public Dense(int nbNeurons, AbstractActivation activation) {
        this(nbNeurons, activation, new GlorotUniform());
    }

    public Dense(int nbNeurons, InitializerInterface weightsInitializer) {
        this(nbNeurons, new Relu(), weightsInitializer);
    }

    public Dense(int nbNeurons, AbstractActivation activation, InitializerInterface weightsInitializer) {
        this.nbNeurons = nbNeurons;
        this.activation = activation;
        this.weightsInitializer = weightsInitializer;
        this.biasInitializer = new Zeros();
    }

    private void initLayer(DMatrixRMaj matrix) {
        bias = new DMatrixRMaj(new double[this.nbNeurons][1]);
        weights = new DMatrixRMaj(new double[matrix.getNumCols()][this.nbNeurons]);
        biasInitializer.init(this.bias);
        weightsInitializer.init(this.weights);
        isInit = true;
    }

    @Override
    public DMatrixRMaj rawCompute(DMatrixRMaj X) {
        if (!isInit) {
            initLayer(X);
        }

        int startRow = 0;
        DMatrixRMaj out = CommonOps_DDRM.mult(X, this.weights, null);
        CommonOps_DDRM.transpose(out);

        for (int i = 0; i < out.getNumCols(); i++) {
            DMatrixRMaj currentCol = CommonOps_DDRM.extractColumn(out, i, null);
            currentCol = CommonOps_DDRM.add(currentCol, bias, null);
            simpleOps.setColumn(out, i, startRow, currentCol.getData());
        }

        CommonOps_DDRM.transpose(out);
        return activation.compute(out);
    }

    @Override
    public DMatrixRMaj computeActivation(DMatrixRMaj Z) {
        return activation.compute(Z);
    }

    @Override
    public DMatrixRMaj trainingComputeActivation(DMatrixRMaj Z) {
        return activation.trainingCompute(Z);
    }

    @Override
    public DMatrixRMaj get_DZ_DA_derivative() {
        return getWeights();
    }

    @Override
    public DMatrixRMaj get_DA_DZ_derivative() throws NoTrainingComputationsPerformedException {
        return activation.get_DA_DZ_derivative();
    }

    @Override
    public ArrayList<DMatrixRMaj> compute_DZ_DParams_derivative() {
        ArrayList<DMatrixRMaj> derivatives = new ArrayList<>(2);
        DMatrixRMaj biasDerivative = UtilityOperationsMatrix.ones(nbNeurons, 1);

        derivatives.add(getLastFeed());
        derivatives.add(biasDerivative);
        return derivatives;
    }

    @Override
    public ArrayList<DMatrixRMaj> getParams() {
        ArrayList<DMatrixRMaj> params = new ArrayList<>();
        params.add(getWeights());
        params.add(getBias());

        return params;
    }

    public DMatrixRMaj getBias() throws IllegalStateException{
        if (!isInit)
            throw new IllegalStateException("Please use model.predict to initialize the parameters");
        return bias;
    }

    public DMatrixRMaj getWeights() throws IllegalStateException{
        if (!isInit)
            throw new IllegalStateException("Please use model.predict to initialize the parameters");
        return weights;
    }
}
