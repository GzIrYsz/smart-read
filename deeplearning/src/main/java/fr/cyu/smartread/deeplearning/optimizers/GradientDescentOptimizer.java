package fr.cyu.smartread.deeplearning.optimizers;

import fr.cyu.smartread.deeplearning.IncompatibleShapeException;
import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.model.AbstractModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ejml.MatrixDimensionException;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.dense.row.CommonOps_MT_DDRM;

import java.util.ArrayList;

public class GradientDescentOptimizer implements OptimizerInterface {
    private static final long serialVersionUID = -7779403844463233821L;
    private static final Logger logger = LogManager.getLogger();
    private final double alpha;
    private final AbstractLoss loss;
    private final AbstractModel model;

    public GradientDescentOptimizer(double alpha, AbstractLoss loss, AbstractModel model) {
        this.alpha = alpha;
        this.loss = loss;
        this.model = model;
    }

    @Override
    public ArrayList<ArrayList<DMatrixRMaj>> step(DMatrixRMaj XBatch, DMatrixRMaj yBatch, Double lossValue) throws NoTrainingComputationsPerformedException, IncompatibleShapeException {
        DMatrixRMaj yPredictionsBatch = model.computeTrain(XBatch);
        lossValue = loss.trainingCompute(yPredictionsBatch, yBatch);

        ArrayList<ArrayList<DMatrixRMaj>> modelGradients = model.getGradientComputer().computeGradients(loss);
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
        
        if (hasNotTrainableParams(params))
            return params;

        for (int i = 0; i < params.size(); i++) {
            DMatrixRMaj gradient = gradients.get(i);
            DMatrixRMaj gradsTimesAlpha= UtilityOperationsMatrix.scale(alpha, gradient);
            CommonOps_MT_DDRM.transpose(gradsTimesAlpha);

            DMatrixRMaj param = params.get(i);

            DMatrixRMaj previousParamMinusGradsTimesAlpha;
            try {
                previousParamMinusGradsTimesAlpha = CommonOps_DDRM.subtract(param, gradsTimesAlpha, null);
            } catch (MatrixDimensionException e) {
                logger.trace("Incorrect dimensions !");
                CommonOps_DDRM.transpose(gradsTimesAlpha);
                previousParamMinusGradsTimesAlpha = CommonOps_DDRM.subtract(param, gradsTimesAlpha, null);
            }
            newLayerParam.add(previousParamMinusGradsTimesAlpha);
        }

        return newLayerParam;
    }

    private static boolean hasNotTrainableParams(ArrayList<DMatrixRMaj> params) {
        return params.size() == 0;
    }
}
