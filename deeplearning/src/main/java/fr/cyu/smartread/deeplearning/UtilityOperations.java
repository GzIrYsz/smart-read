package fr.cyu.smartread.deeplearning;

import org.ejml.data.DMatrixRMaj;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UtilityOperations {
    public static double sum(@NotNull double[] array) {
        double sum = 0;

        for (double value : array) {
            sum += value;
        }

        return sum;
    }

    public static double mean(@NotNull double[] array) throws IllegalArgumentException {
        if(array.length == 0)
            throw new IllegalArgumentException("Array is empty");

        return sum(array) / array.length;
    }

    public static<T> List<T> sublist(List<T> list, int start, int end) {
        return list.subList(Math.max(0, start), Math.min(list.size(), end));
    }

    public static int argmax(DMatrixRMaj matrix) {
        double[] vector = matrix.getData();

        int maxIndex = 0;
        for (int i = 1; i < vector.length; i++) {
            if (vector[i] > vector[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
