package fr.cyu.smartread.app.gui.components.card.body;

import fr.cyu.smartread.app.gui.components.card.CardModel;
import fr.cyu.smartread.app.gui.components.card.body.components.ContinuousLine;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CardBodyView extends JPanel {
    public static final int DRAWING_ZONE_SIZE_WIDTH = 270;
    public static final int DRAWING_ZONE_SIZE_HEIGHT = 250;
    private ArrayList<ContinuousLine> continuousLines;
    private int currentLineIndex = 0;
    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;
    private final BasicStroke stroke = new BasicStroke(6.0f);
    public static final BufferedImage blankImg = getBlankBufferedImg();

    public CardBodyView() {
        super();
        setPreferredSize(new Dimension(DRAWING_ZONE_SIZE_WIDTH, DRAWING_ZONE_SIZE_HEIGHT));
        init();
    }

    protected void init() {
        continuousLines = new ArrayList<>();
        bufferedImage = getBlankBufferedImg();

        graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.BLACK);
        graphics2D.setBackground(Color.WHITE);
        graphics2D.setStroke(stroke);
        graphics2D.clearRect(0, 0, DRAWING_ZONE_SIZE_WIDTH, DRAWING_ZONE_SIZE_HEIGHT);

        setOpaque(true);
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(stroke);

        for (ContinuousLine currentLine : continuousLines) {
            for (int j = 0; j < currentLine.getNbDots() - 1; j++) {
                Point p1 = currentLine.getDot(j);
                Point p2 = currentLine.getDot(j + 1);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                graphics2D.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    public CardBodyView addLine() {
        continuousLines.add(new ContinuousLine());
        currentLineIndex = continuousLines.size() - 1;
        return this;
    }

    public void clearDrawingZone() {
        init();
        this.repaint();
    }

    private static BufferedImage getBlankBufferedImg() {
        return new BufferedImage(DRAWING_ZONE_SIZE_WIDTH, DRAWING_ZONE_SIZE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
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
        CardBodyController ctrl = new CardBodyController(new CardModel(),cardBodyView);
        cardBodyView.addMouseListener(ctrl);
        cardBodyView.addMouseMotionListener(ctrl);

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
