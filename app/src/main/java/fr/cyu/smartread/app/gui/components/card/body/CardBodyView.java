package fr.cyu.smartread.app.gui.components.card.body;

import fr.cyu.smartread.app.gui.components.card.body.components.ContinuousLine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CardBodyView extends JPanel {
    private static final int DRAWINGZONE_SIZE = 128;

    private ArrayList<ContinuousLine> continuousLines;
    private int currentLineIndex = 0;
    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;

    public CardBodyView() {
        super();
        init();
    }

    protected void init() {
        continuousLines = new ArrayList<>();
        bufferedImage = new BufferedImage(DRAWINGZONE_SIZE, DRAWINGZONE_SIZE, BufferedImage.TYPE_INT_ARGB);

        graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.BLACK);
        graphics2D.setBackground(Color.WHITE);
        graphics2D.clearRect(0, 0, DRAWINGZONE_SIZE, DRAWINGZONE_SIZE);

        setOpaque(true);
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < continuousLines.size(); i++) {
            ContinuousLine currentLine = continuousLines.get(i);
            for (int j = 0; j < currentLine.getNbDots() - 1; j++) {
                Point p1 = currentLine.getDot(j);
                Point p2 = currentLine.getDot(j + 1);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                graphics2D.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    public CardBodyView addLine() {
        continuousLines.add(new ContinuousLine());
        currentLineIndex = continuousLines.size() - 1;
        return this;
    }

    public Graphics2D getGraphics2D() {
        return graphics2D;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public ContinuousLine getCurrentLine() {
        return continuousLines.get(currentLineIndex);
    }

    public int getCurrentLineIndex() {
        return currentLineIndex;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("test" + CardBodyView.class.getSimpleName());
        jf.setLocation(1920 / 2, 1080 / 2);

        Container container = jf.getContentPane();

        CardBodyView cardBodyView = new CardBodyView();
        CardBodyController ctrl = new CardBodyController(cardBodyView);
        cardBodyView.addMouseListener(ctrl);
        cardBodyView.addMouseMotionListener(ctrl);
        cardBodyView.setPreferredSize(new Dimension(DRAWINGZONE_SIZE, DRAWINGZONE_SIZE));

        container.add(cardBodyView);

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.out.println("Fermeture");
                cardBodyView.getGraphics().dispose();
                try {
                    ImageIO.write(cardBodyView.getBufferedImage(), "png", new File("test.png"));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
