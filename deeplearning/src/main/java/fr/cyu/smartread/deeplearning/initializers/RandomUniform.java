package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.dense.row.RandomMatrices_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

import java.util.Random;

public class RandomUniform implements InitializerInterface {
    private static final long serialVersionUID = -5369657616217630463L;
    private double minVal;
    private double maxVal;
    private final SimpleOperations_DDRM simpleOps = new SimpleOperations_DDRM();

    public RandomUniform() {
        this(-0.05, 0.05);
    }

    public RandomUniform(double minVal, double maxVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    public InitializerInterface init(DMatrixRMaj matrix) {
        return this.init(matrix, this.minVal, this.maxVal);
    }

    public InitializerInterface init(DMatrixRMaj matrix, double minVal, double maxVal) {
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
