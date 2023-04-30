package fr.cyu.smartread.deeplearning.gradient;

import fr.cyu.smartread.deeplearning.BroadCastingOps;
import fr.cyu.smartread.deeplearning.UtilityOperationsMatrix;
import fr.cyu.smartread.deeplearning.activations.NoTrainingComputationsPerformedException;
import fr.cyu.smartread.deeplearning.layers.AbstractLayer;
import fr.cyu.smartread.deeplearning.losses.AbstractLoss;
import fr.cyu.smartread.deeplearning.model.AbstractModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

import java.util.ArrayList;
import java.util.Collections;

public class SequentialGradientComputer extends GradientComputerAbstract {
    private static final long serialVersionUID = 4802304860755588786L;
    private static final Logger logger = LogManager.getLogger();

    public SequentialGradientComputer(AbstractModel abstractModel) {
        super(abstractModel);
    }

    @Override
    public ArrayList<ArrayList<DMatrixRMaj>> computeGradients(AbstractLoss loss) throws NoTrainingComputationsPerformedException {
        ArrayList<ArrayList<DMatrixRMaj>> gradients = new ArrayList<>();
        ArrayList<AbstractLayer> layers = getModelAbstract().getLayers();
        DMatrixRMaj DZ = UtilityOperationsMatrix.ones(1, 1);

        int nbLayers = layers.size() - 1;

        for (int i = nbLayers; i >= 0; i--) {
            AbstractLayer currentLayer = layers.get(i);

            if (i == nbLayers) {
                logger.trace("rétropopagation de l'erreur");
                DMatrixRMaj newDZ = BroadCastingOps.elementMult(loss.get_DJ_DA_derivative(), currentLayer.get_DA_DZ_derivative());
                DZ = BroadCastingOps.elementMult(DZ, newDZ);

                logger.trace("loss derivative value: " + loss.get_DJ_DA_derivative());
                logger.trace("activation derivative value: " + loss.get_DJ_DA_derivative());
            } else {
                logger.trace("rétropopagation du gradiant à travers la couche " + i);
                AbstractLayer previousLayer = layers.get(i + 1);

                logger.trace("previous layer derivative value: " + previousLayer.get_DZ_DParams_derivative());
                logger.trace("activation derivative value: " + currentLayer.get_DA_DZ_derivative());

                DMatrixRMaj newDZ = CommonOps_DDRM.multTransB(DZ, previousLayer.get_DZ_DA_derivative(), null);

                DZ = CommonOps_DDRM.elementMult(newDZ, currentLayer.get_DA_DZ_derivative(), null);
            }

            gradients.add(compute_DJ_DParams_derivative(DZ, currentLayer));
        }

        Collections.reverse(gradients);
        return gradients;
    }

    private ArrayList<DMatrixRMaj> compute_DJ_DParams_derivative(DMatrixRMaj DZ, AbstractLayer currentLayer) throws NoTrainingComputationsPerformedException {
        return currentLayer.computeGradientWithDZ(DZ);
    }
}
