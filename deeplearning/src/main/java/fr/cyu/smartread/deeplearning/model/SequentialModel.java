package fr.cyu.smartread.deeplearning.model;

import fr.cyu.smartread.deeplearning.IncompatibleShapeException;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.fitter.SequentialModelFitter;
import fr.cyu.smartread.deeplearning.gradient.SequentialGradientComputer;
import fr.cyu.smartread.deeplearning.layers.AbstractLayer;
import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.metrics.AbstractMetric;
import fr.cyu.smartread.deeplearning.optimizers.OptimizerInterface;
import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;
import java.util.Arrays;

public class SequentialModel extends AbstractModel implements AutoTrainableModel {
    private static final long serialVersionUID = 2020670374695974120L;
    ArrayList<AbstractLayer> layers;

    public SequentialModel(ArrayList<AbstractLayer> layers) {
        setGradientComputerAbstract(new SequentialGradientComputer(this));
        this.layers = layers;
    }

    public SequentialModel(AbstractLayer... layers) {
        this(new ArrayList<>(Arrays.asList(layers)));
    }

    @Override
    public DMatrixRMaj predict(DMatrixRMaj X) {
        DMatrixRMaj lastAction = X;

        for (AbstractLayer layer: layers) {
            lastAction = layer.rawCompute(lastAction);
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
            layerParams.add(layer.getTrainableParams());
        }

        return layerParams;
    }

    @Override
    public void setLayersTrainableParams(ArrayList<ArrayList<DMatrixRMaj>> modelParams) {
        ArrayList<AbstractLayer> modelLayers = getLayers();

        if (modelLayers.size() != modelParams.size())
            throw new IllegalArgumentException("the number of parameters is not equal to the number of parameters in the model");

        for (int i = 0; i < modelLayers.size(); i++) {
            AbstractLayer layer = modelLayers.get(i);
            ArrayList<DMatrixRMaj> newLayerParams = modelParams.get(i);

            layer.setTrainableParams(newLayerParams);
        }
        //TODO A tester
    }

    @Override
    void saveModel(String path) {
        // TODO hehe Thomas
    }

    public ArrayList<AbstractLayer> getLayers() {
        return layers;
    }

    @Override
    public void fit(ArrayList<DMatrixRMaj> xBatch, ArrayList<DMatrixRMaj> yBatch, OptimizerInterface optimizer, AbstractLoss loss, ArrayList<AbstractMetric> metrics, int epoch, int batch_size) {
        try {
            SequentialModelFitter.fit(this, xBatch, yBatch, optimizer, metrics, epoch, batch_size);
        } catch (IncompatibleShapeException | NoTrainingComputationsPerformedException e) {
            throw new RuntimeException(e);
        }
    }
}
