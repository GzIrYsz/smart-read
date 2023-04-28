package fr.cyu.smartread.deeplearning.model;

import fr.cyu.smartread.deeplearning.gradient.GradientComputerAbstract;
import fr.cyu.smartread.deeplearning.layers.AbstractLayer;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

abstract public class AbstractModel {
    private GradientComputerAbstract gradientComputerAbstract;

    abstract public DMatrixRMaj predict(DMatrixRMaj X);
    abstract public DMatrixRMaj computeTrain(DMatrixRMaj X);
    public GradientComputerAbstract getGradientComputer() {
        return gradientComputerAbstract;
    }

    public void setGradientComputerAbstract(GradientComputerAbstract gradientComputerAbstract) {
        this.gradientComputerAbstract = gradientComputerAbstract;
    }

    abstract public ArrayList<ArrayList<DMatrixRMaj>> getLayersParams();
    public abstract void setLayersTrainableParams(ArrayList<ArrayList<DMatrixRMaj>> modelParams);

    abstract void saveModel(String path);

    abstract public ArrayList<AbstractLayer> getLayers();
}
