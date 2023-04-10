package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.dense.row.RandomMatrices_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

import java.util.Random;

public class RandomNormal extends AbstractInitializer {
    private double stdev;
    private final SimpleOperations_DDRM simpleOps = new SimpleOperations_DDRM();

    public RandomNormal(int shape) {
        this(shape, 0.05);
    }

    public RandomNormal(int shape, double stdev) {
        super(shape);
        this.stdev = stdev;
    }

    @Override
    public AbstractInitializer init(DMatrixRMaj matrix) {
        int colCount = matrix.getNumCols();
        int startRow = 0;

        for (int i = 0; i < colCount; i++) {
            DMatrixRMaj currentCol = CommonOps_DDRM.extractColumn(matrix, i, null);
            RandomMatrices_DDRM.fillGaussian(currentCol, 0.0, this.stdev, new Random());
            simpleOps.setColumn(matrix, i, startRow, currentCol.getData());
        }
        return this;
    }
}
