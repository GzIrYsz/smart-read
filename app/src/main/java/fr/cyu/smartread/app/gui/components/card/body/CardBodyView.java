package fr.cyu.smartread.app.gui.components.card.body;

import fr.cyu.smartread.app.gui.components.card.footer.CardFooterView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardBodyView extends JPanel {
    private int x1, y1, x2, y2;
    private BufferedImage bi;
    Graphics2D g2d;

    public CardBodyView() {
        super();
        bi = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
        g2d = bi.createGraphics();
        //setOpaque(true);
        //setBackground(Color.WHITE);

        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, 256, 256);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        add(new JLabel(new ImageIcon(bi)));
        //g.setColor(Color.BLACK);
        //g.fillRect(x1, y1, 2, 2);
    }

    public void paintImage() {
        g2d.fillRect(x1, y1, 2, 2);
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public BufferedImage getBi() {
        return bi;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("test" + CardBodyView.class.getSimpleName());
        jf.setLocation(200, 200);

        Container container = jf.getContentPane();

        CardBodyView cardBodyView = new CardBodyView();
        CardBodyController ctrl = new CardBodyController(cardBodyView);
        cardBodyView.addMouseListener(ctrl);
        cardBodyView.addMouseMotionListener(ctrl);
        cardBodyView.setPreferredSize(new Dimension(256, 256));

        container.add(cardBodyView);

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.out.println("Fermeture");
                cardBodyView.getG2d().dispose();
                try {
                    ImageIO.write(cardBodyView.getBi(), "png", new File("test.png"));
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
