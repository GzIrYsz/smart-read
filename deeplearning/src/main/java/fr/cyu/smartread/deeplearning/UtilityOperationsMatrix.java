package fr.cyu.smartread.deeplearning;

import org.ejml.data.DMatrixRMaj;
import scala.Array;

import javax.validation.constraints.NotNull;

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

    private static boolean hasJustOneRow(DMatrixRMaj matrix) {
        return matrix.getNumRows() == 1;
    }

    public static DMatrixRMaj clip(DMatrixRMaj matrix, double min, double max) {
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
}
