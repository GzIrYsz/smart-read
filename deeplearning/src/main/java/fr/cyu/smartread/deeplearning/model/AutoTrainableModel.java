package fr.cyu.smartread.deeplearning.model;

import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.metrics.AbstractMetric;
import fr.cyu.smartread.deeplearning.optimizers.OptimizerInterface;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

public interface AutoTrainableModel {
    void fit(ArrayList<DMatrixRMaj> xTrainBatch, ArrayList<DMatrixRMaj> yTrainBatch, ArrayList<DMatrixRMaj> xTestBatch, ArrayList<DMatrixRMaj> yTestBatch, OptimizerInterface optimizer, AbstractLoss loss, ArrayList<AbstractMetric> metrics, int epoch, int batch_size);
}
