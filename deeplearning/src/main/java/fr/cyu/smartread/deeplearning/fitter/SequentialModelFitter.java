package fr.cyu.smartread.deeplearning.fitter;

import fr.cyu.smartread.deeplearning.IncompatibleShapeException;
import fr.cyu.smartread.deeplearning.UtilityOperations;
import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.metrics.AbstractMetric;
import fr.cyu.smartread.deeplearning.model.AbstractModel;
import fr.cyu.smartread.deeplearning.optimizers.OptimizerInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;
import java.util.List;

public class SequentialModelFitter {
    private static final Logger logger = LogManager.getLogger();

    public static void fit(AbstractModel model, ArrayList<DMatrixRMaj> xTrainDataset, ArrayList<DMatrixRMaj> yTrainDataset, ArrayList<DMatrixRMaj> xTestDataset, ArrayList<DMatrixRMaj> yTestDataset, OptimizerInterface optimizer, ArrayList<AbstractMetric> metrics, int epoch, int batch_size) throws IncompatibleShapeException, NoTrainingComputationsPerformedException {
        for (int i = 0; i < epoch; i++) {
            trainingEpochComputation(xTrainDataset, yTrainDataset, model, optimizer, batch_size);
            validationEpochComputation(xTestDataset, yTestDataset, model);
            //TODO ajouter les metrics
        }
    }

    private static void trainingEpochComputation(ArrayList<DMatrixRMaj> xDataset, ArrayList<DMatrixRMaj> yDataset, AbstractModel model, OptimizerInterface optimizer, int batch_size) throws NoTrainingComputationsPerformedException, IncompatibleShapeException {
        for (int i = 0; i < xDataset.size(); i++) {
            DMatrixRMaj xTrain = xDataset.get(i);
            DMatrixRMaj yTrain = yDataset.get(i);

            ArrayList<ArrayList<DMatrixRMaj>> newModelParams = optimizer.step(xTrain, yTrain);
            model.setLayersTrainableParams(newModelParams);
        }
    }

    private static void validationEpochComputation(ArrayList<DMatrixRMaj> xTestDataset, ArrayList<DMatrixRMaj> yTestDataset, AbstractModel model) {
        int goodResult = 0;

        for (int i = 0; i < xTestDataset.size(); i++) {
            DMatrixRMaj xTest = xTestDataset.get(i);
            DMatrixRMaj yTest = yTestDataset.get(i);

            DMatrixRMaj predictions = model.predict(xTest);

            if (isGoodResult(predictions, yTest))
                goodResult++;
        }

        logger.info(String.format("L'accuracy du modèle est de %d/%d", goodResult, xTestDataset.size()));
    }

    private static boolean isGoodResult(DMatrixRMaj predictions, DMatrixRMaj label) {
        return UtilityOperations.argmax(predictions) == UtilityOperations.argmax(label);
    }

    private static DMatrixRMaj createBatchFromList(List<DMatrixRMaj> dataset, int start, int end) {
        List<DMatrixRMaj> batch = UtilityOperations.sublist(dataset, start, end);
        return UtilityOperationsMatrix.createMatrixFromListRowByRow(batch);
    }

}
