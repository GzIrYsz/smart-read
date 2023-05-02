package fr.cyu.smartread.deeplearning;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import scala.Array;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UtilityOperationsMatrix {
    public static boolean areShapeEqual(@NotNull DMatrixRMaj matrix1, @NotNull DMatrixRMaj matrix2) {
        int[] predShape = {matrix1.getNumRows(), matrix1.getNumCols()};
        int[] labelShape = {matrix2.getNumRows(), matrix2.getNumCols()};

        return predShape[0] == labelShape[0] && predShape[1] == labelShape[1];
    }

    public static double meanOneRow(@NotNull DMatrixRMaj row) throws NotARowException {
        if(!hasJustOneRow(row))
            throw new NotARowException(row);

        return UtilityOperations.mean(row.getData());
    }

    private static boolean hasJustOneRow(@NotNull DMatrixRMaj matrix) {
        return matrix.getNumRows() == 1;
    }

    public static DMatrixRMaj clip(@NotNull DMatrixRMaj matrix, double min, double max) {
        double[] values = matrix.getData();

        if (values.length == 0)
            return matrix;

        double[] newValues = (double[]) Array.copyOf(values, values.length);

        for (int i = 0; i < newValues.length; i++) {
            double value = newValues[i];

            if (value > max)
                newValues[i] = max;

            if (value < min)
                newValues[i] = min;
        }

        return new DMatrixRMaj(matrix.getNumRows(), matrix.getNumCols(), true, newValues);
    }

    public static DMatrixRMaj ones(int row, int columns) {
        if (row == 0 || columns == 0)
            throw new IllegalArgumentException(String.format("rows or columns must not be 0, current value row: %d, column: %d", row, columns));

        DMatrixRMaj ones = new DMatrixRMaj(row, columns);
        ones.fill(1.0);

        return ones;
    }

    public static DMatrixRMaj ones(DMatrixRMaj matrix) {
        return ones(matrix.getNumRows(), matrix.getNumCols());
    }

    public static DMatrixRMaj zeros(int row, int columns) {
        if (row == 0 || columns == 0)
            throw new IllegalArgumentException(String.format("rows or columns must not be 0, current value row: %d, column: %d", row, columns));

        DMatrixRMaj zero = new DMatrixRMaj(row, columns);
        zero.fill(0.0);

        return zero;
    }

    public static DMatrixRMaj scale(double k, @NotNull DMatrixRMaj matrix) {
        DMatrixRMaj result = new DMatrixRMaj(matrix);
        CommonOps_DDRM.scale(k, matrix, result);
        return result;
    }

    public static DMatrixRMaj mask(float probabilities, @NotNull DMatrixRMaj originalMatrix) {
        int row = originalMatrix.getNumRows();
        int columns = originalMatrix.getNumCols();

        return createMask(probabilities, row, columns);
    }

    public static DMatrixRMaj mask(float probabilities, int rows, int columns) {
        return createMask(probabilities, rows, columns);
    }

    private static DMatrixRMaj createMask(float probabilities, int row, int columns) {
        DMatrixRMaj mask = ones(row, columns);
        double[] maskData = mask.getData();

        for (int i = 0; i < maskData.length; i++) {
            double maskValue = Math.random() >= 1 - probabilities ? 0 : 1;
            mask.set(i, maskValue);
        }

        return mask;
    }

    public static DMatrixRMaj duplicateRow(@NotNull DMatrixRMaj row, int numberOfRowExpected) throws NotARowException{
        if(!hasJustOneRow(row))
            throw new NotARowException(row);

        final int rowColumns = row.getNumCols();
        final int numberOfElemInResult = rowColumns * numberOfRowExpected;

        double[] entireMatrixMaskData = new double[numberOfElemInResult];

        for (int i = 0; i < numberOfElemInResult; i = i + rowColumns) {
            for (int j = 0; j < rowColumns; j++) {
                double value = row.get(j);
                entireMatrixMaskData[i + j] = value;
            }
        }

        return new DMatrixRMaj(numberOfRowExpected, rowColumns, true, entireMatrixMaskData);
    }

    public static DMatrixRMaj createMatrixFromListRowByRow(@NotNull List<DMatrixRMaj> batch) {
        DMatrixRMaj[] batchMatrix = batch.toArray(new DMatrixRMaj[0]);

        return CommonOps_DDRM.concatRowsMulti(batchMatrix);
    }
}
