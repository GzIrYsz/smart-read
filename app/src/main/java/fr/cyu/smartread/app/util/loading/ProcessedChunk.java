package fr.cyu.smartread.app.util.loading;

import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

public class ProcessedChunk {
    private ArrayList<DMatrixRMaj> processedImages;
    private ArrayList<String> labels;

    public ProcessedChunk(int chunkSize) {
        processedImages = new ArrayList<>(chunkSize);
        labels = new ArrayList<>(chunkSize);
    }

    public void addProcessedImage(DMatrixRMaj processedImage, String label) {
        processedImages.add(processedImage);
        labels.add(label);
    }

    public ArrayList<DMatrixRMaj> getProcessedImages() {
        return processedImages;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }
}
