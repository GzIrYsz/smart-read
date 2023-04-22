package fr.cyu.smartread.app.util.encoding;

import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Binarizer {
    HashMap<String, DMatrixRMaj> encoded;
    HashMap<DMatrixRMaj, String> decoded;

    public Binarizer fit(String[] labels) {
        return this.fit(Arrays.asList(labels));
    }

    public Binarizer fit(List<String> labels) {
        int labelsCount = labels.size();
        initAttributes(labelsCount);
        for (int i = 0; i < labelsCount; i++) {
            DMatrixRMaj encodedLabel = new DMatrixRMaj(new double[1][labelsCount]);
            encodedLabel.fill(0.0);
            encodedLabel.set(0, i, 1.0);
            encoded.put(labels.get(i), encodedLabel);
            decoded.put(encodedLabel, labels.get(i));
        }
        return this;
    }

    public ArrayList<DMatrixRMaj> encode(ArrayList<String> labels) {
        ArrayList<DMatrixRMaj> encoded = new ArrayList<>(labels.size());
        for (String label : labels) {
            encoded.add(getEncodedLabel(label));
        }
        return encoded;
    }

    public ArrayList<String> decode(ArrayList<DMatrixRMaj> labels) {
        ArrayList<String> decoded = new ArrayList<>(labels.size());
        for (DMatrixRMaj label : labels) {
            decoded.add(getDecodedLabel(label));
        }
        return decoded;
    }

    private void initAttributes(int labelCount) {
        this.encoded = new HashMap<>(labelCount);
        this.decoded = new HashMap<>(labelCount);
    }

    public DMatrixRMaj getEncodedLabel(String label) {
        return encoded.get(label);
    }

    public String getDecodedLabel(DMatrixRMaj label) {
        return decoded.get(label);
    }

    public HashMap<String, DMatrixRMaj> getEncoded() {
        return encoded;
    }

    public void setEncoded(HashMap<String, DMatrixRMaj> encoded) {
        this.encoded = encoded;
    }

    public HashMap<DMatrixRMaj, String> getDecoded() {
        return decoded;
    }

    public void setDecoded(HashMap<DMatrixRMaj, String> decoded) {
        this.decoded = decoded;
    }
}