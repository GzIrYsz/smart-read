package fr.cyu.smartread.deeplearning.gradient;

import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.model.ModelAbstract;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

abstract public class GradientComputerAbstract {
    private final ModelAbstract modelAbstract;

    protected GradientComputerAbstract(ModelAbstract modelAbstract) {
        this.modelAbstract = modelAbstract;
    }

    public abstract ArrayList<ArrayList<DMatrixRMaj>> computeGradients(AbstractLoss loss) throws NoTrainingComputationsPerformedException;

    public ModelAbstract getModelAbstract() {
        return modelAbstract;
    }
}
