package fr.cyu.smartread.app.gui.components.card.body;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardBodyController extends MouseAdapter {
    CardBodyView drawingZone;

    public CardBodyController(CardBodyView drawingZone) {
        this.drawingZone = drawingZone;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawingZone.getPoints().add(new Point(e.getX(), e.getY()));
        drawingZone.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawingZone.getPoints().add(new Point(e.getX(), e.getY()));
        drawingZone.repaint();
    }
}
