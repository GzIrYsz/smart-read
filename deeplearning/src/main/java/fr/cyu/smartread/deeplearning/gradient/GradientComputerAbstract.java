package fr.cyu.smartread.deeplearning.gradient;

import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.model.AbstractModel;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

abstract public class GradientComputerAbstract {
    private final AbstractModel abstractModel;

    protected GradientComputerAbstract(AbstractModel abstractModel) {
        this.abstractModel = abstractModel;
    }

    public abstract ArrayList<ArrayList<DMatrixRMaj>> computeGradients(AbstractLoss loss) throws NoTrainingComputationsPerformedException;

    public AbstractModel getModelAbstract() {
        return abstractModel;
    }
}