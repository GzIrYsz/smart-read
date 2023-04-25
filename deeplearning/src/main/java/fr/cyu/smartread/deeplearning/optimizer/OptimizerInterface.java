package fr.cyu.smartread.deeplearning.optimizer;

import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

public interface OptimizerInterface {
    ArrayList<ArrayList<DMatrixRMaj>> step() throws NoTrainingComputationsPerformedException;
}
