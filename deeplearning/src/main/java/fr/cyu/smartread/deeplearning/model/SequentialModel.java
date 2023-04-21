package fr.cyu.smartread.deeplearning.model;

import fr.cyu.smartread.deeplearning.gradient.GradientComputerAbstract;
import fr.cyu.smartread.deeplearning.layers.AbstractLayer;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;
import java.util.Arrays;

public class SequentialModel extends ModelAbstract{
    ArrayList<AbstractLayer> layers;

    public SequentialModel(GradientComputerAbstract gradientComputerAbstract, ArrayList<AbstractLayer> layers) {
        super(gradientComputerAbstract);
        this.layers = layers;
    }

    public SequentialModel(GradientComputerAbstract gradientComputerAbstract, AbstractLayer... layers) {
        this(gradientComputerAbstract, new ArrayList<>(Arrays.asList(layers)));
    }

    @Override
    public DMatrixRMaj predict(DMatrixRMaj X) {
        DMatrixRMaj lastAction = X;

        for (AbstractLayer layer: layers) {
            lastAction = layer.compute(lastAction);
        }

        return lastAction;
    }

    @Override
    public DMatrixRMaj computeTrain(DMatrixRMaj X) {
        DMatrixRMaj lastAction = X;

        for (AbstractLayer layer: layers) {
            lastAction = layer.trainingCompute(lastAction);
        }

        return lastAction;
    }

    @Override
    public ArrayList<ArrayList<DMatrixRMaj>> getLayersParams() {
        ArrayList<ArrayList<DMatrixRMaj>> layerParams = new ArrayList<>();

        for (AbstractLayer layer: layers) {
            layerParams.add(layer.getParams());
        }

        return layerParams;
    }

    @Override
    void saveModel(String path) {
        // TODO hehe Thomas
    }

    public ArrayList<AbstractLayer> getLayers() {
        return layers;
    }
}
