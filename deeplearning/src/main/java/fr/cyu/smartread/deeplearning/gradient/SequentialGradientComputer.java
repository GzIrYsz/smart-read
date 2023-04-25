package fr.cyu.smartread.deeplearning.gradient;

import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.layers.AbstractLayer;
import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.model.AbstractModel;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

import java.util.ArrayList;

public class SequentialGradientComputer extends GradientComputerAbstract {

    protected SequentialGradientComputer(AbstractModel abstractModel) {
        super(abstractModel);
    }

    @Override
    public ArrayList<ArrayList<DMatrixRMaj>> computeGradients(AbstractLoss loss) throws NoTrainingComputationsPerformedException {
        ArrayList<ArrayList<DMatrixRMaj>> gradients = new ArrayList<>();
        ArrayList<AbstractLayer> layers = getModelAbstract().getLayers();
        DMatrixRMaj DZ = UtilityOperationsMatrix.ones(1, 1);

        int nbLayers = layers.size() - 1;

        for (int i = nbLayers; i > 1; i--) {
            AbstractLayer currentLayer = layers.get(i);

            if (i == nbLayers) {
                DMatrixRMaj newDZ = CommonOps_DDRM.elementMult(loss.get_DJ_DA_derivative(), currentLayer.get_DA_DZ_derivative(), null);
                DZ = CommonOps_DDRM.elementMult(DZ, newDZ, null);
            } else {
                AbstractLayer previousLayer = layers.get(i + 1);
                DMatrixRMaj newDZ = CommonOps_DDRM.elementMult(previousLayer.get_DZ_DA_derivative(), currentLayer.get_DA_DZ_derivative(), null);
                DZ = CommonOps_DDRM.elementMult(DZ, newDZ, null);
            }

            gradients.add(compute_DJ_DParams_derivative(DZ, currentLayer));
        }

        return gradients;
    }

    private ArrayList<DMatrixRMaj> compute_DJ_DParams_derivative(DMatrixRMaj DZ, AbstractLayer currentLayer) throws NoTrainingComputationsPerformedException {
        ArrayList<DMatrixRMaj> layerGrads = new ArrayList<>();

        for (DMatrixRMaj layerParam: currentLayer.get_DZ_DParams_derivative()) {
            DMatrixRMaj paramDerivative = CommonOps_DDRM.elementMult(DZ, layerParam, null);
           layerGrads.add(paramDerivative);
        }

        return layerGrads;
    }
}
