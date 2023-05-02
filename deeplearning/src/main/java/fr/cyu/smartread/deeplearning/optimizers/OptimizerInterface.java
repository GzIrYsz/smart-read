package fr.cyu.smartread.deeplearning.optimizers;

import fr.cyu.smartread.deeplearning.IncompatibleShapeException;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;

import java.io.Serializable;
import java.util.ArrayList;

public interface OptimizerInterface extends Serializable {

    //ArrayList<ArrayList<DMatrixRMaj>> step(DMatrixRMaj batchX, DMatrixRMaj batchY) throws NoTrainingComputationsPerformedException, IncompatibleShapeException;

    ArrayList<ArrayList<DMatrixRMaj>> step(DMatrixRMaj XBatch, DMatrixRMaj yBatch, Double lossValue) throws NoTrainingComputationsPerformedException, IncompatibleShapeException;
}
