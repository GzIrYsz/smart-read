package fr.cyu.smartread.app.wrappers.deeplearning;

import fr.cyu.smartread.app.util.imagetransform.ImageTransformations;
import fr.cyu.smartread.app.util.loading.EncodedLabelReader;
import fr.cyu.smartread.app.util.serialization.SerializationUtil;
import fr.cyu.smartread.deeplearning.model.AbstractModel;
import org.ejml.data.DMatrixRMaj;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class OCRDetector {
    private final AbstractModel model;
    private static final int IMG_SIZE = 41;
    private final String MODEL_SERIALIZED_PATH = "app/src/main/resources/model/model75x4.ta";
    private final String ENCODED_LABEL_CSV_PATH = "app/src/main/resources/model/label_encodage.csv";

    private final HashMap<Integer, Character> decoderLabelHM;

    public OCRDetector() throws IOException, ClassNotFoundException {
        this.model = (AbstractModel) SerializationUtil.deserialize(new File(MODEL_SERIALIZED_PATH));
        decoderLabelHM = EncodedLabelReader.getDecoderLabelFromCsv(ENCODED_LABEL_CSV_PATH);
    }

    public ArrayList<PredictedLetter> detect(BufferedImage imgLetter) {
        DMatrixRMaj matrixImg = fitImgForModel(imgLetter);
        DMatrixRMaj predictionsMatrix = model.predict(matrixImg);

        return getBestPredictions(predictionsMatrix, 10);
    }

    private DMatrixRMaj fitImgForModel(BufferedImage image) {
        BufferedImage resizedImg = ImageTransformations.resizeImage(image, IMG_SIZE, IMG_SIZE);
        return ImageTransformations.imageToMatrix(resizedImg);
    }

    private ArrayList<PredictedLetter> getBestPredictions(DMatrixRMaj predictionsMatrix, int numberOfBestPred) {
       ArrayList<PredictedLetter> bestPredictions = new ArrayList<>();

        for (int i = 0; i < numberOfBestPred; i++) {
            int indexOfBestPred = argmax(predictionsMatrix);
            char letter = decoderLabelHM.get(indexOfBestPred);
            float value = (float)  predictionsMatrix.get(indexOfBestPred);

            PredictedLetter predictedLetter = new PredictedLetter(letter, value);
            bestPredictions.add(predictedLetter);
            predictionsMatrix.set(indexOfBestPred, -1);
        }

        return bestPredictions;
    }

    private int argmax(DMatrixRMaj matrix) {
        double[] matrixData = matrix.getData();
        int indicesMax = 0;

        for (int i = 0; i < matrix.getNumElements(); i++) {
            if (matrixData[i] > matrixData[indicesMax])
                indicesMax = i;
        }

        return indicesMax;
    }
}