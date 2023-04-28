package fr.cyu.smartread.deeplearning.optimizers;

import fr.cyu.smartread.deeplearning.IncompatibleShapeException;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

public interface OptimizerInterface {

    ArrayList<ArrayList<DMatrixRMaj>> step(DMatrixRMaj batchX, DMatrixRMaj batchY) throws NoTrainingComputationsPerformedException, IncompatibleShapeException;
}
