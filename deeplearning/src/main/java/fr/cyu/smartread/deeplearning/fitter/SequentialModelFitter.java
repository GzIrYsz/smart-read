package fr.cyu.smartread.deeplearning.fitter;

import fr.cyu.smartread.deeplearning.IncompatibleShapeException;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.metrics.AbstractMetric;
import fr.cyu.smartread.deeplearning.model.AbstractModel;
import fr.cyu.smartread.deeplearning.optimizer.OptimizerInterface;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

import java.util.ArrayList;
import java.util.List;

public class SequentialModelFitter {
    public static void fit(AbstractModel model, ArrayList<DMatrixRMaj> xDataset, ArrayList<DMatrixRMaj> yDataset, OptimizerInterface optimizer, ArrayList<AbstractMetric> metrics, int epoch, int batch_size) throws IncompatibleShapeException, NoTrainingComputationsPerformedException {
        for (int i = 0; i < epoch; i++) {
            epochComputation(xDataset, yDataset, model, optimizer, batch_size);
            //TODO ajouter les metrics
        }
    }

    private static void epochComputation(ArrayList<DMatrixRMaj> xDataset, ArrayList<DMatrixRMaj> yDataset, AbstractModel model, OptimizerInterface optimizer, int batch_size) throws NoTrainingComputationsPerformedException, IncompatibleShapeException {
        for (int i = 0; i < xDataset.size(); i += batch_size) {
            int start = i;
            int end = i + batch_size;

            DMatrixRMaj XBatchMatrix = createBatchFromList(xDataset, start , end);
            DMatrixRMaj YBatchMatrix = createBatchFromList(yDataset, start, end);

            ArrayList<ArrayList<DMatrixRMaj>> newModelParams = optimizer.step(XBatchMatrix, YBatchMatrix);
            model.setLayersTrainableParams(newModelParams);
        }
    }

    private static DMatrixRMaj createBatchFromList(List<DMatrixRMaj> dataset, int start, int end) {
        List<DMatrixRMaj> batch = sublist(dataset, start, end);
        return createOneMatrixBatch(batch);
    }

    private static DMatrixRMaj createOneMatrixBatch(List<DMatrixRMaj> batch) {
        DMatrixRMaj batchMatrix = new DMatrixRMaj();

        for (DMatrixRMaj matrix: batch) {
            CommonOps_DDRM.concatRows(batchMatrix, matrix, batchMatrix);
        }

        return batchMatrix;
    }

    public static<T> List<T> sublist(List<T> list, int start, int end) {
        return list.subList(Math.max(0, start), Math.min(list.size(), end + 1));
    }
}
