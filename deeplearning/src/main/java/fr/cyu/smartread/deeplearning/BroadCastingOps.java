package fr.cyu.smartread.deeplearning;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;

public class BroadCastingOps {
    public static DMatrixRMaj elementMult(DMatrixRMaj A, DMatrixRMaj B)  {
         if (UtilityOperationsMatrix.areShapeEqual(A, B))
             return CommonOps_DDRM.elementMult(A, B, null);
         else if (isScalar(A))
             return scalarMult(A, B);
          else if (isScalar(B))
             return scalarMult(B, A);

          throw new IllegalArgumentException("matrix are not compatible");
    }

    public static DMatrixRMaj scalarMult(DMatrixRMaj scalar, DMatrixRMaj matrix)  {
        int matrixRow = matrix.getNumRows();
        int matrixCol = matrix.getNumCols();

        if (!isScalar(scalar))
            throw new IllegalArgumentException(String.format("the given scalar is not a scalar, he is of the form (%d, %d)", scalar.getNumRows(), scalar.getNumCols()));

        DMatrixRMaj scalarMatrix = new DMatrixRMaj(matrixRow, matrixCol);
        scalarMatrix.fill(scalar.get(0, 0));

       return CommonOps_DDRM.elementMult(scalarMatrix, matrix, null);
    }

    private static boolean isScalar(DMatrixRMaj scalar) {
        int scalarRow = scalar.getNumRows();
        int scalarCol = scalar.getNumCols();

        return scalarRow == 1 && scalarCol == 1;
    }


}
