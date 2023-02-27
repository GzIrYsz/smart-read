package fr.cyu.smartread.app.gui.components.card.body;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardBodyController extends MouseAdapter {
    CardBodyView drawingZone;

    public CardBodyController(CardBodyView drawingZone) {
        this.drawingZone = drawingZone;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawingZone.setX1(e.getX());
        drawingZone.setY1(e.getY());

        drawingZone.paintImage();
        drawingZone.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawingZone.setX1(e.getX());
        drawingZone.setY1(e.getY());

        drawingZone.paintImage();
        drawingZone.repaint();
    }
}
