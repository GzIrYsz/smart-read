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
import org.ejml.dense.row.CommonOps_DDRM;

import java.util.ArrayList;
import java.util.List;

public class SequentialModelFitter {
    private static final Logger logger = LogManager.getLogger();

    public static void fit(AbstractModel model, ArrayList<DMatrixRMaj> xTrainDataset, ArrayList<DMatrixRMaj> yTrainDataset, ArrayList<DMatrixRMaj> xTestDataset, ArrayList<DMatrixRMaj> yTestDataset, OptimizerInterface optimizer, ArrayList<AbstractMetric> metrics, int epoch, int batchSize) throws IncompatibleShapeException, NoTrainingComputationsPerformedException {
        for (int i = 0; i < epoch; i++) {
            trainingEpochComputation(xTrainDataset, yTrainDataset, model, optimizer, batchSize);
            validationEpochComputation(xTestDataset, yTestDataset, model, batchSize);
            //TODO ajouter les metrics
        }
    }

    private static void trainingEpochComputation(ArrayList<DMatrixRMaj> xDataset, ArrayList<DMatrixRMaj> yDataset, AbstractModel model, OptimizerInterface optimizer, int batch_size) throws NoTrainingComputationsPerformedException, IncompatibleShapeException {
        double totalLoss = 0;

        for (int i = 0; i < xDataset.size(); i += batch_size) {
            int start = i;
            int end = i + batch_size;

            DMatrixRMaj xTrain = createBatchFromList(xDataset, start, end);
            DMatrixRMaj yTrain = createBatchFromList(yDataset, start, end);
            double lossValue = 0;

            ArrayList<ArrayList<DMatrixRMaj>> newModelParams = optimizer.step(xTrain, yTrain, lossValue);
            model.setLayersTrainableParams(newModelParams);
            totalLoss += lossValue;
        }

        double meanLoss = totalLoss / xDataset.size();

        logger.info("l'erreur totale pour cette epoch est " + meanLoss);
    }

    private static void validationEpochComputation(ArrayList<DMatrixRMaj> xTestDataset, ArrayList<DMatrixRMaj> yTestDataset, AbstractModel model, int batchSize) {
        int goodResult = 0;

        for (int i = 0; i < xTestDataset.size(); i += batchSize) {
            int start = i;
            int end = i + batchSize;

            DMatrixRMaj xTest = createBatchFromList(xTestDataset, start, end);
            DMatrixRMaj yTest = createBatchFromList(yTestDataset, start, end);

            DMatrixRMaj predictions = model.predict(xTest);

            goodResult += howManyGoodResultBatch(predictions, yTest);
        }

        logger.info(String.format("L'accuracy du modèle est de %d/%d", goodResult, xTestDataset.size()));
    }

    private static int howManyGoodResultBatch(DMatrixRMaj xBatchPred, DMatrixRMaj yBatchLabel) {
        int goodResult = 0;

        for (int currentRow = 0; currentRow < xBatchPred.getNumRows(); currentRow++) {
            DMatrixRMaj pred = CommonOps_DDRM.extractRow(xBatchPred, currentRow, null);
            DMatrixRMaj label = CommonOps_DDRM.extractRow(yBatchLabel, currentRow, null);

            if (isGoodResult(pred, label))
                goodResult++;
        }

        return goodResult;
    }

    private static boolean isGoodResult(DMatrixRMaj predictions, DMatrixRMaj label) {
        return UtilityOperations.argmax(predictions) == UtilityOperations.argmax(label);
    }

    private static DMatrixRMaj createBatchFromList(List<DMatrixRMaj> dataset, int start, int end) {
        List<DMatrixRMaj> batch = UtilityOperations.sublist(dataset, start, end);
        return UtilityOperationsMatrix.createMatrixFromListRowByRow(batch);
    }

}
