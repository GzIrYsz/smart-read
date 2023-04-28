package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.dense.row.RandomMatrices_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

import java.util.Random;

public class RandomNormal implements InitializerInterface {
    private static final long serialVersionUID = -1816881356343348139L;
    private double stDev;
    private final SimpleOperations_DDRM simpleOps = new SimpleOperations_DDRM();

    public RandomNormal() {
        this(0.05);
    }

    public RandomNormal(double stDev) {
        this.stDev = stDev;
    }

    @Override
    public InitializerInterface init(DMatrixRMaj matrix) {
        return this.init(matrix, this.stDev);
    }

    public InitializerInterface init(DMatrixRMaj matrix, double stDev) {
        int colCount = matrix.getNumCols();
        int startRow = 0;

        for (int i = 0; i < colCount; i++) {
            DMatrixRMaj currentCol = CommonOps_DDRM.extractColumn(matrix, i, null);
            RandomMatrices_DDRM.fillGaussian(currentCol, 0.0, stDev, new Random());
            simpleOps.setColumn(matrix, i, startRow, currentCol.getData());
        }
        return this;
    }
}
