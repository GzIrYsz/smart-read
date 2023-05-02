package fr.cyu.smartread.app.util.loading;

import org.ejml.data.DMatrixRMaj;

import java.util.Queue;

public class ChunkProcessor implements Runnable {
    public static int LABEL_INDEX = 1;
    public static int IMAGE_START_INDEX = 2;
    private Queue<String[]> chunk;
    private ProcessedChunk processedChunk;

    public ChunkProcessor(Queue<String[]> chunk, ProcessedChunk processedChunk) {
        this.chunk = chunk;
        this.processedChunk = processedChunk;
    }

    @Override
    public void run() {
        while (!chunk.isEmpty()) {
            String[] line = chunk.remove();
            double[][] splittedLine = new double[1][line.length - IMAGE_START_INDEX];
            String label = line[LABEL_INDEX];
            for (int i = IMAGE_START_INDEX; i < line.length; i++) {
                splittedLine[0][i - IMAGE_START_INDEX] = Double.parseDouble(line[i]);
            }
            processedChunk.addProcessedImage(new DMatrixRMaj(splittedLine), label);
        }
    }
}
