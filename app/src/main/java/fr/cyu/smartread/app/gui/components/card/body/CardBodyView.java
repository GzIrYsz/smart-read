package fr.cyu.smartread.app.gui.components.card.body;

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
    private ArrayList<Point> points;
    private BufferedImage bi;
    private Graphics2D g2d;

    public CardBodyView() {
        super();
        bi = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
        g2d = bi.createGraphics();
        setOpaque(true);
        setBackground(Color.WHITE);

        points = new ArrayList<>();

        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, 128, 128);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public BufferedImage getBi() {
        return bi;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("test" + CardBodyView.class.getSimpleName());
        jf.setLocation(200, 200);

        Container container = jf.getContentPane();

        CardBodyView cardBodyView = new CardBodyView();
        CardBodyController ctrl = new CardBodyController(cardBodyView);
        cardBodyView.addMouseListener(ctrl);
        cardBodyView.addMouseMotionListener(ctrl);
        cardBodyView.setPreferredSize(new Dimension(128, 128));

        container.add(cardBodyView);

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.out.println("Fermeture");
                cardBodyView.getG2d().dispose();
//                try {
//                    ImageIO.write(cardBodyView.getBi(), "png", new File("test.png"));
//                } catch (IOException e2) {
//                    e2.printStackTrace();
//                }
//                try {
//                    BufferedImage bi2 = ImageIO.read(new File("app/src/main/resources/icon-effacer.png"));
//                    for (int i = 0; i < bi2.getWidth(); i++) {
//                        for (int j = 0; j < bi2.getHeight(); j++) {
//                            if (bi2.getRGB(i, j) != 0) {
//                                System.out.print("  ");
//                            } else {
//                                System.out.print(". ");
//                            }
//                        }
//                        System.out.println("");
//                    }
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
            }
        });
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
