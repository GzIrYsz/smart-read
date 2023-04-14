package fr.cyu.smartread.app.gui.components.card.body;

import fr.cyu.smartread.app.gui.components.card.CardModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CardBodyController extends MouseAdapter {
    private final CardModel cardModel;
    private final CardBodyView drawingZone;

    public CardBodyController(CardModel cardModel, CardBodyView drawingZone) {
        this.cardModel = cardModel;
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
        updateModel();
    }

    private void updateModel() {
        BufferedImage currentImage = drawingZone.getBufferedImage();
        cardModel.setDrawingZoneImg(currentImage);
    }
}
