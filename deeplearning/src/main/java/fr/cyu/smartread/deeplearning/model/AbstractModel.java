package fr.cyu.smartread.deeplearning.model;

import fr.cyu.smartread.deeplearning.gradient.GradientComputerAbstract;
import fr.cyu.smartread.deeplearning.layers.AbstractLayer;
import org.ejml.data.DMatrixRMaj;

import java.io.*;
import java.util.ArrayList;

abstract public class AbstractModel implements Serializable {
    private static final long serialVersionUID = 8334919965997770290L;
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

    abstract public ArrayList<AbstractLayer> getLayers();
}
