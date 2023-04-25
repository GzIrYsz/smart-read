package fr.cyu.smartread.deeplearning.optimizer;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.model.AbstractModel;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

import java.util.ArrayList;

public class GradientDescentOptimizer implements OptimizerInterface {
    private final double alpha;
    private final AbstractLoss loss;
    private final AbstractModel model;

    public GradientDescentOptimizer(double alpha, AbstractLoss loss, AbstractModel model) {
        this.alpha = alpha;
        this.loss = loss;
        this.model = model;
    }

    @Override
    public ArrayList<ArrayList<DMatrixRMaj>> step() throws NoTrainingComputationsPerformedException {
        ArrayList<ArrayList<DMatrixRMaj>> modelGradients = model.getGradientComputerAbstract().computeGradients(loss);
        ArrayList<ArrayList<DMatrixRMaj>> modelParams = model.getLayersParams();
        ArrayList<ArrayList<DMatrixRMaj>> newModelParams = new ArrayList<>();

        for (int i = 0; i < modelParams.size(); i++) {
            ArrayList<DMatrixRMaj> params = modelParams.get(i);
            ArrayList<DMatrixRMaj> paramsGradients = modelGradients.get(i);

            ArrayList<DMatrixRMaj> newLayerParams = computeNewParams(params, paramsGradients, alpha);
            newModelParams.add(newLayerParams);
        }

        return newModelParams;
    }

    private ArrayList<DMatrixRMaj> computeNewParams(ArrayList<DMatrixRMaj> params, ArrayList<DMatrixRMaj> gradients, double alpha) {
        ArrayList<DMatrixRMaj> newLayerParam = new ArrayList<>();

        for (int i = 0; i < params.size(); i++) {
            DMatrixRMaj gradient = gradients.get(i);
            DMatrixRMaj gradsTimesAlpha= UtilityOperationsMatrix.scale(alpha, gradient);

            DMatrixRMaj param = params.get(i);

            DMatrixRMaj previousParamMinusGradsTimesAlpha = CommonOps_DDRM.subtract(param, gradsTimesAlpha, null);
            newLayerParam.add(previousParamMinusGradsTimesAlpha);
        }

        return newLayerParam;
    }
}
