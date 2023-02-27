package fr.cyu.smartread.app.gui.pallets.borders;

import javax.swing.border.LineBorder;
import java.awt.*;

public class Circle extends LineBorder {
    public Circle(int thickness) {
        super(Color.BLACK, thickness);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(x + getThickness(), y + getThickness(), width - 2 * getThickness(), height - 2 * getThickness());
    }
}