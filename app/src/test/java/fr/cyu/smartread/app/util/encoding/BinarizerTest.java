package fr.cyu.smartread.app.util.encoding;

import org.ejml.EjmlUnitTests;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinarizerTest {
    Binarizer binarizer;

    @BeforeEach
    public void setUp() {
        binarizer = new Binarizer();
    }

    @Test
    public void shouldFitCorrectly2Classes() {
        String[] labels = new String[]{"a", "b"};
        DMatrixRMaj a_encoded = new DMatrixRMaj(new double[][]{
                {1, 0}
        });
        DMatrixRMaj b_encoded = new DMatrixRMaj(new double[][]{
                {0, 1}
        });

        binarizer.fit(labels);
        EjmlUnitTests.assertEquals(binarizer.getEncodedLabel("a"), a_encoded);
        EjmlUnitTests.assertEquals(binarizer.getEncodedLabel("b"), b_encoded);
    }

    @Test
    public void shouldFitCorrectly3Classes() {
        String[] labels = new String[]{"a", "b", "c"};
        DMatrixRMaj a_encoded = new DMatrixRMaj(new double[][]{
                {1, 0, 0}
        });
        DMatrixRMaj b_encoded = new DMatrixRMaj(new double[][]{
                {0, 1, 0}
        });
        DMatrixRMaj c_encoded = new DMatrixRMaj(new double[][]{
                {0, 0, 1}
        });

        binarizer.fit(labels);
        EjmlUnitTests.assertEquals(binarizer.getEncodedLabel("a"), a_encoded);
        EjmlUnitTests.assertEquals(binarizer.getEncodedLabel("b"), b_encoded);
        EjmlUnitTests.assertEquals(binarizer.getEncodedLabel("c"), c_encoded);
    }

    @Test
    public void shouldEncodeCorrectly3Labels() {
        String[] base = new String[]{"a", "b", "c"};
        binarizer.fit(base);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("a");
        labels.add("a");
        labels.add("b");
        labels.add("a");
        labels.add("c");

        DMatrixRMaj a_encoded = new DMatrixRMaj(new double[][]{
                {1, 0, 0}
        });
        DMatrixRMaj b_encoded = new DMatrixRMaj(new double[][]{
                {0, 1, 0}
        });
        DMatrixRMaj c_encoded = new DMatrixRMaj(new double[][]{
                {0, 0, 1}
        });
        ArrayList<DMatrixRMaj> expectedResult = new ArrayList<>(labels.size());
        expectedResult.add(a_encoded);
        expectedResult.add(a_encoded);
        expectedResult.add(b_encoded);
        expectedResult.add(a_encoded);
        expectedResult.add(c_encoded);
        ArrayList<DMatrixRMaj> result = binarizer.encode(labels);
        assertEquals(labels.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            EjmlUnitTests.assertEquals(expectedResult.get(i), result.get(i));
        }
    }

    @Test
    public void shouldDecodeCorrectly3Labels() {
        String[] base = new String[]{"a", "b", "c"};
        binarizer.fit(base);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("a");
        labels.add("a");
        labels.add("b");
        labels.add("a");
        labels.add("c");

        ArrayList<DMatrixRMaj> encoded = binarizer.encode(labels);
        ArrayList<String> result = binarizer.decode(encoded);
        assertEquals(labels.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(labels.get(i), result.get(i));
        }
    }

//    @Test
//    public void shouldDecodeCorrectly2LabelsWDifferentMatrix() {
//        String[] base = new String[]{"a", "b", "c"};
//        binarizer.fit(base);
//
//        ArrayList<String> labels = new ArrayList<>();
//        labels.add("a");
//        labels.add("a");
//        labels.add("b");
//        labels.add("a");
//        labels.add("c");
//
//        ArrayList<DMatrixRMaj> encoded = binarizer.encode(labels);
//        ArrayList<DMatrixRMaj> encodedCopy = new ArrayList<>(encoded.size());
//        for (DMatrixRMaj matrix : encoded) {
//            encodedCopy.add(matrix.copy());
//        }
//        ArrayList<String> result = binarizer.decode(encodedCopy);
//        assertEquals(labels.size(), result.size());
//        for (int i = 0; i < result.size(); i++) {
//            assertEquals(labels.get(i), result.get(i));
//        }
//    }
}