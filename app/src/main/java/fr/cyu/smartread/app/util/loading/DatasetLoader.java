package fr.cyu.smartread.app.util.loading;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import fr.cyu.smartread.app.util.Dataset;
import fr.cyu.smartread.app.util.encoding.Binarizer;
import org.ejml.data.DMatrixRMaj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DatasetLoader {
    public static int CHUNK_SIZE = 25000;
    public static int TIMEOUT_DURATION = 120;
    private static int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private CSVReader reader;
    private Binarizer binarizer;
    private ArrayList<ProcessedChunk> processedChunks;

    public DatasetLoader(CSVReader reader, Binarizer binarizer) {
        this.reader = reader;
        this.binarizer = binarizer;
    }

    public Dataset loadDataset() throws IOException, CsvException, InterruptedException, TimeoutException {
        List<Queue<String[]>> data = loadCsv();
        //Test
//        String[] line = data.get(0).peek();
//        for (int j = 0; j < 41; j++) {
//            for (int k = 0; k < 41; k++) {
//                System.out.print(line[(j*k) + 2]);
//            }
//            System.out.println();
//        }
//        System.out.println(line[1]);
        //Fin_test
        initProcessedChunks(data.size());
        Dataset dataset = new Dataset();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < data.size(); i++) {
            Queue<String[]> chunk = data.get(i);
            ProcessedChunk processedChunk = processedChunks.get(i);
            Runnable chunkProcessor = new ChunkProcessor(chunk, processedChunk);
            executor.execute(chunkProcessor);
        }
        executor.shutdown();
        boolean processingFinished = executor.awaitTermination(TIMEOUT_DURATION, TimeUnit.SECONDS);
        if (!processingFinished) {
            throw new TimeoutException("The dataset processing has timeout after " + TIMEOUT_DURATION + " seconds !");
        }
        return createDatasetFromProcessedChunks(data.size());
    }

    private Dataset createDatasetFromProcessedChunks(int imageCount) {
        Dataset dataset = new Dataset();
        ArrayList<String> labels = new ArrayList<>(imageCount);
        for (ProcessedChunk processedChunk : processedChunks) {
            dataset.getX().addAll(processedChunk.getProcessedImages());
            labels.addAll(processedChunk.getLabels());
            //Test
//            ArrayList<DMatrixRMaj> processedImages = processedChunk.getProcessedImages();
//            ArrayList<String> testLabels = processedChunk.getLabels();
//            for (int i = 0; i < processedImages.size(); i++) {
//                DMatrixRMaj currentImage = processedImages.get(i);
//                for (int j = 0; j < 41; j++) {
//                    for (int k = 0; k < 41; k++) {
//                        if (currentImage.get(0, j*k) == 1) {
//                            System.out.print("■");
//                        } else {
//                            System.out.print("□");
//                        }
//                    }
//                    System.out.println();
//                }
//                System.out.println(testLabels.get(i));
//                break;
//            }
            //Fin_test
        }
        ArrayList<DMatrixRMaj> encodedLabels = binarizer.encode(labels);
        dataset.setY(encodedLabels);
        return dataset;
    }

    private List<Queue<String[]>> loadCsv() {
        List<Queue<String[]>> chunks = new ArrayList<>();
        Iterator<String[]> it = reader.iterator();
        String[] header = it.next();
        Queue<String[]> chunk;
        while (it.hasNext()) {
            chunk = new LinkedList<>();
            for (int i = 0; i < CHUNK_SIZE && it.hasNext(); i++) {
                chunk.add(it.next());
            }
            chunks.add(chunk);
        }
        return chunks;
    }

    private void initProcessedChunks(int processedChunksCount) {
        processedChunks = new ArrayList<>(processedChunksCount);
        for (int i = 0; i < processedChunksCount; i++) {
            ProcessedChunk processedChunk = new ProcessedChunk(CHUNK_SIZE);
            processedChunks.add(processedChunk);
        }
    }

    private void splitArrayFrom2ColsTo1Col() {

    }

    public static void main(String[] args) {
        DatasetLoader datasetLoader;
        File testFile = new File("./app/src/test/resources/data/csv_test_file.csv");
        File dataset = new File("./app/src/main/resources/data/nist_processed_train.csv");
        String[] labels = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z"};
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(testFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Binarizer binarizer = new Binarizer();
        binarizer.fit(labels);
        datasetLoader = new DatasetLoader(reader, binarizer);

        Dataset ds;
        try {
            long start = System.currentTimeMillis();
            ds = datasetLoader.loadDataset();
            System.out.println((System.currentTimeMillis() - start) / 1000.0);
        } catch (IOException | CsvException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        ArrayList<DMatrixRMaj> images = ds.getX();
        ArrayList<DMatrixRMaj> encodedLabels = ds.getY();
//        for (int i = 0; i < images.size(); i++) {
//            DMatrixRMaj currentImage = images.get(i);
//            for (int j = 0; j < 41; j++) {
//                for (int k = 0; k < 41; k++) {
//                    if (currentImage.get(0, j*k) == 1) {
//                        System.out.print("■");
//                    } else {
//                        System.out.print("□");
//                    }
//                }
//                System.out.println();
//            }
//            System.out.println("Label" + i + " : " + encodedLabels.get(i));
//            System.out.println("Label" + i + " : " + binarizer.getDecodedLabel(encodedLabels.get(i)));
//        }
    }
}
