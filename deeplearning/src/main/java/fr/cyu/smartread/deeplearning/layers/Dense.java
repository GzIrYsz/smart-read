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
    private static final long serialVersionUID = 7951415276400455972L;
    private boolean isInit = false;
    private static final int PARAMS_NUMBER = 2;
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
    public int getTrainableParamsNumber() {
        return PARAMS_NUMBER;
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
    public ArrayList<DMatrixRMaj> computeGradientWithDZ(DMatrixRMaj DZ) throws NoTrainingComputationsPerformedException {
        ArrayList<DMatrixRMaj> layerGrads = new ArrayList<>();

        ArrayList<DMatrixRMaj> DZ_DParam_derivative =  get_DZ_DParams_derivative();

        DMatrixRMaj DZ_DW_derivative = CommonOps_DDRM.multTransA(DZ_DParam_derivative.get(0), DZ, null);
        DMatrixRMaj DZ_DB_derivative =  CommonOps_DDRM.sumCols(DZ, null);

        layerGrads.add(DZ_DW_derivative);
        layerGrads.add(DZ_DB_derivative);

        return layerGrads;
    }

    @Override
    public ArrayList<DMatrixRMaj> getTrainableParams() {
        ArrayList<DMatrixRMaj> params = new ArrayList<>();
        params.add(getWeights());
        params.add(getBias());

        return params;
    }

    @Override
    public void setterTrainableParams(ArrayList<DMatrixRMaj> params) {
        setWeights(params.get(0));
        setBias(params.get(1));
    }

    public DMatrixRMaj getBias() throws IllegalStateException{
        if (!isInit)
            throw new IllegalStateException("Make calculations before performing this operation");
        return bias;
    }

    public DMatrixRMaj getWeights() throws IllegalStateException{
        if (!isInit)
            throw new IllegalStateException("Make calculations before performing this operation");
        return weights;
    }

    public void setWeights(DMatrixRMaj weights) {
        if (!UtilityOperationsMatrix.areShapeEqual(getWeights(), weights))
            throw new IllegalArgumentException("Shape of weights are not equal");
        this.weights = weights;
    }

    public void setBias(DMatrixRMaj bias) {
        if (!UtilityOperationsMatrix.areShapeEqual(getBias(), bias))
            throw new IllegalArgumentException("Shape of bias are not equal");
        this.bias = bias;
    }
}
