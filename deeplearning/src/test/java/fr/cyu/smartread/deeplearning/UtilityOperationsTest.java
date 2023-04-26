package fr.cyu.smartread.deeplearning;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilityOperationsTest {
    @Test
    void shouldReturn0ifArrayIsEmpty() {
        double[] array = {};
        double result = UtilityOperations.sum(array);

        assertEquals(0, result);
    }

    @Test
    void shouldReturn20ifArrayHas10and10() {
        double[] array = {10, 10};
        double result = UtilityOperations.sum(array);

        assertEquals(20, result);
    }

    @Test
    void shouldGetExceptionIfArrayIsEmpty() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double[] array = {};

            UtilityOperations.mean(array);
        });

        assertEquals("Array is empty", thrown.getMessage());
    }

    @Test
    void shouldReturnRightResultIfArrayHas10and10and5() {
        double rightResult = 8.333333333333334;
        double[] array = {10, 10, 5};
        double result = UtilityOperations.mean(array);

        assertEquals(rightResult, result);
    }

    @Test
    void shouldGetRightSubListFromList() {
        int start = 0;
        int end = 5;
        ArrayList<Integer> arrayList =  createArrayListForTest();

        List<Integer> rightSubList = Arrays.asList(0, 1, 2, 3, 4);
        List<Integer> subList = UtilityOperations.sublist(arrayList, start, end);

        assertListEqual(rightSubList, subList);
    }

    @Test
    void shouldGetRightSubListFromList2() {
        int start = 1;
        int end = 5;
        ArrayList<Integer> arrayList =  createArrayListForTest();

        List<Integer> rightSubList = Arrays.asList(1, 2, 3, 4);
        List<Integer> subList = UtilityOperations.sublist(arrayList, start, end);

        assertListEqual(rightSubList, subList);
    }

    @Test
    void shouldGetTheEndOFListWhenIndicesExceedOriginalListSize() {
        int start = 5;
        int end = 20;
        ArrayList<Integer> arrayList =  createArrayListForTest();

        List<Integer> rightSubList = Arrays.asList(5, 6, 7, 8, 9);
        List<Integer> subList = UtilityOperations.sublist(arrayList, start, end);
        System.out.println(rightSubList);
        System.out.println(subList);

        assertListEqual(rightSubList, subList);
    }

    private static ArrayList<Integer> createArrayListForTest() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        return arrayList;
    }

    private static<T> void assertListEqual(List<T> expected, List<T> actual) {
        assertEquals(expected.size(), actual.size());

        Iterator<T> iteratorActual = actual.iterator();
        for (T element: expected) {
            assertEquals(element, iteratorActual.next());
        }
    }
}
