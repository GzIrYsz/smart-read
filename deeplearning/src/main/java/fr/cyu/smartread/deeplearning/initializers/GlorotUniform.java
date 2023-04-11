package fr.cyu.smartread.deeplearning.initializers;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.dense.row.RandomMatrices_DDRM;
import org.ejml.simple.ops.SimpleOperations_DDRM;

import java.util.Random;

public class GlorotUniform extends AbstractInitializer {
    private int fanIn;
    private int fanOut;
    private final SimpleOperations_DDRM simpleOps = new SimpleOperations_DDRM();

    public GlorotUniform(int shape, int fanIn, int fanOut) {
        super(shape);
        this.fanIn = fanIn;
        this.fanOut = fanOut;
    }

    @Override
    public AbstractInitializer init(DMatrixRMaj matrix) {
        double limit = Math.sqrt(6.0 / (fanIn + fanOut));
        int colCount = matrix.getNumCols();
        int startRow = 0;

        for (int i = 0; i < colCount; i++) {
            DMatrixRMaj currentCol = CommonOps_DDRM.extractColumn(matrix, i, null);
            RandomMatrices_DDRM.fillUniform(currentCol, -limit, limit, new Random());
            simpleOps.setColumn(matrix, i, startRow, currentCol.getData());
        }
        return this;
    }
}
