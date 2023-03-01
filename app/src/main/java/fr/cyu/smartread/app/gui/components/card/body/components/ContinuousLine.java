package fr.cyu.smartread.app.gui.components.card.body.components;

import java.awt.*;
import java.util.ArrayList;

public class ContinuousLine {
    private ArrayList<Point> dots;

    public ContinuousLine() {
        dots = new ArrayList<>();
    }

    public ContinuousLine addDot(Point dot) {
        dots.add(dot);
        return this;
    }

    public int getNbDots() {
        return dots.size();
    }

    public Point getDot(int i) {
        return dots.get(i);
    }

    public ArrayList<Point> getDots() {
        return dots;
    }
}
