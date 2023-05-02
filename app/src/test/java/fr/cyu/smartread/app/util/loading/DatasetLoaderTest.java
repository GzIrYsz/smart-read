package fr.cyu.smartread.app.util.loading;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import fr.cyu.smartread.app.util.Dataset;
import fr.cyu.smartread.app.util.encoding.Binarizer;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class DatasetLoaderTest {
    DatasetLoader datasetLoader;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        File testFile = new File("./src/test/resources/data/csv_test_file.csv");
        File dataset = new File("./src/main/resources/data/nist_processed_train.csv");
        String[] labels = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z"};
        CSVReader reader = new CSVReader(new FileReader(testFile));
        Binarizer binarizer = new Binarizer();
        binarizer.fit(labels);
        datasetLoader = new DatasetLoader(reader, binarizer);
    }

    @Test
    void shouldLoadDatasetCorrectly() throws IOException, CsvException, InterruptedException, TimeoutException {
        Dataset dataset = datasetLoader.loadDataset();
        ArrayList<DMatrixRMaj> images = dataset.getX();
        ArrayList<DMatrixRMaj> encodedLabels = dataset.getY();
        assertEquals(images.size(), encodedLabels.size());
        for (int i = 0; i < images.size(); i++) {
            System.out.println("Image" + i + " : " + images.get(i));
            System.out.println("Label" + i + " : " + encodedLabels.get(i));
        }
    }
}