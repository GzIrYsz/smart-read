package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.dense.row.RandomMatrices_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

import java.util.Random;

public class RandomUniform extends AbstractInitializer {
    private double minVal;
    private double maxVal;
    private final SimpleOperations_DDRM simpleOps = new SimpleOperations_DDRM();

    public RandomUniform(int shape) {
        this(shape, -0.05, 0.05);
    }

    public RandomUniform(int shape, double minVal, double maxVal) {
        super(shape);
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    public AbstractInitializer init(DMatrixRMaj matrix) {
        int colCount = matrix.getNumCols();
        int startRow = 0;

        for (int i = 0; i < colCount; i++) {
            DMatrixRMaj currentCol = CommonOps_DDRM.extractColumn(matrix, i, null);
            RandomMatrices_DDRM.fillUniform(currentCol, minVal, maxVal, new Random());
            simpleOps.setColumn(matrix, i, startRow, currentCol.getData());
        }
        return this;
    }
}
