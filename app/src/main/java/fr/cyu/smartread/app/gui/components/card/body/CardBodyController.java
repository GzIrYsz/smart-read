package fr.cyu.smartread.app.gui.components.card.body;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardBodyController extends MouseAdapter {
    private CardBodyView drawingZone;

    public CardBodyController(CardBodyView drawingZone) {
        this.drawingZone = drawingZone;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawingZone.addLine();
        drawingZone.getCurrentLine().addDot(new Point(e.getX(), e.getY()));
        drawingZone.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawingZone.getCurrentLine().addDot(new Point(e.getX(), e.getY()));
        drawingZone.repaint();
    }
}
