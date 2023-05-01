package fr.cyu.smartread.app.training;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import fr.cyu.smartread.app.util.Dataset;
import fr.cyu.smartread.app.util.encoding.Binarizer;
import fr.cyu.smartread.app.util.loading.DatasetLoader;
import fr.cyu.smartread.deeplearning.activations.Relu;
import fr.cyu.smartread.deeplearning.layers.Dense;
import fr.cyu.smartread.deeplearning.losses.CategoricalCrossentropy;
import fr.cyu.smartread.deeplearning.model.SequentialModel;
import fr.cyu.smartread.deeplearning.optimizers.GradientDescentOptimizer;
import org.ejml.data.DMatrixRMaj;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class TrainingModel1 {
    private static final String X_TRAIN = "xTrain";
    private static final String Y_TRAIN = "yTrain";
    private static final String X_TEST = "xTest";
    private static final String Y_TEST = "yTest";
    private static final String PATH_TRAIN = "app/src/main/resources/data/nist_processed_train.csv";
    private static final String PATH_TEST = "app/src/main/resources/data/nist_processed_test.csv";
    private static final String[] labels = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    private static final double gamma = 0.9;

    public static void main(String[] args) {
        CategoricalCrossentropy loss = new CategoricalCrossentropy();
        SequentialModel model = new SequentialModel(
                new Dense(200, new Relu()),
                new Dense(200, new Relu()),
                new Dense(200, new Relu()),
                new Dense(200, new Relu()),
                new Dense(52, new Relu())
        );

        HashMap<String, ArrayList<DMatrixRMaj>> datasetTrainTest = TrainingModel1.loadDataset(PATH_TRAIN, PATH_TEST);

        ArrayList<DMatrixRMaj> xTrain = datasetTrainTest.get(X_TRAIN);
        ArrayList<DMatrixRMaj> yTrain = datasetTrainTest.get(Y_TRAIN);

        ArrayList<DMatrixRMaj> xTest = datasetTrainTest.get(X_TEST);
        ArrayList<DMatrixRMaj> yTest = datasetTrainTest.get(Y_TEST);

        model.fit(xTrain, yTrain, xTest, yTest, new GradientDescentOptimizer(gamma, loss, model), loss, null, 2, 0);
    }
    
    public static HashMap<String, ArrayList<DMatrixRMaj>> loadDataset(String pathTrain, String pathTest) {
        /* TRAIN */

        DatasetLoader datasetTrainLoader;

        File trainDataset = new File(pathTrain);
        CSVReader trainReader = initCSVReader(trainDataset);

        Binarizer binarizer = new Binarizer();
        binarizer.fit(labels);
        datasetTrainLoader = new DatasetLoader(trainReader, binarizer);

        Dataset dsTrain = fillDataset(datasetTrainLoader);

        ArrayList<DMatrixRMaj> imagesTrain = dsTrain.getX();
        ArrayList<DMatrixRMaj> encodedLabelsTrain = dsTrain.getY();

        HashMap<String, ArrayList<DMatrixRMaj>> datasetTrainTest = new HashMap<String, ArrayList<DMatrixRMaj>>();
        datasetTrainTest.put(X_TRAIN, imagesTrain);
        datasetTrainTest.put(Y_TRAIN, encodedLabelsTrain);

        /* TEST */

        DatasetLoader datasetTestLoader;

        File testDataset = new File(pathTest);
        CSVReader testReader = initCSVReader(testDataset);

        datasetTestLoader = new DatasetLoader(testReader, binarizer);

        Dataset dsTest = fillDataset(datasetTestLoader);

        ArrayList<DMatrixRMaj> imagesTest = dsTest.getX();
        ArrayList<DMatrixRMaj> encodedLabelsTest = dsTest.getY();

        datasetTrainTest.put(X_TEST, imagesTest);
        datasetTrainTest.put(Y_TEST, encodedLabelsTest);

        return datasetTrainTest;
    }

    private static Dataset fillDataset(DatasetLoader datasetTrainLoader) {
        Dataset ds;

        try {
            long start = System.currentTimeMillis();
            ds = datasetTrainLoader.loadDataset();
            System.out.println((System.currentTimeMillis() - start) / 1000.0);
        } catch (IOException | CsvException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        return ds;
    }

    @NotNull
    private static CSVReader initCSVReader(File dataset) {
        CSVReader reader;

        try {
            reader = new CSVReader(new FileReader(dataset));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }
}