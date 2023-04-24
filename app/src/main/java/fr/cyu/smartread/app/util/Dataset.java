package fr.cyu.smartread.app.util;

import org.ejml.data.DMatrixRMaj;

import java.util.ArrayList;

public class Dataset {
    private ArrayList<DMatrixRMaj> x = new ArrayList<>();
    private ArrayList<DMatrixRMaj> y = new ArrayList<>();

    public void addImage(DMatrixRMaj image) {
        x.add(image);
    }

    public void addElement(DMatrixRMaj image, DMatrixRMaj label) {
        addImage(image);
        y.add(label);
    }

    public ArrayList<DMatrixRMaj> getX() {
        return x;
    }

    public ArrayList<DMatrixRMaj> getY() {
        return y;
    }

    public void setY(ArrayList<DMatrixRMaj> y) {
        this.y = y;
    }
}
