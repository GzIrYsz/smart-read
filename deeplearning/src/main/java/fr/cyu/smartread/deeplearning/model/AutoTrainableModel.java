package fr.cyu.smartread.deeplearning.model;

import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.metrics.AbstractMetric;
import fr.cyu.smartread.deeplearning.optimizer.OptimizerInterface;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

public interface AutoTrainableModel {

    void fit(ArrayList<DMatrixRMaj> XBatch, ArrayList<DMatrixRMaj> YBatch, OptimizerInterface optimizer, AbstractLoss loss, ArrayList<AbstractMetric> metrics, int epoch, int batch_size);
}
