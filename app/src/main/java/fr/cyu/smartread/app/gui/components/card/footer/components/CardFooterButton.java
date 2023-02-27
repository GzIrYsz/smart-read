package fr.cyu.smartread.app.gui.components.card.footer.components;

import fr.cyu.smartread.app.gui.pallets.BorderPallet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

public class CardFooterButton extends JLabel {
    public CardFooterButton(String filename) {
        this(new ImageIcon(filename));
    }

    public CardFooterButton(Icon icon) {
        super(icon, CENTER);
        init();
    }

    protected void init() {
        setOpaque(true);
        setBackground(Color.GRAY);
    }

    public static void main(String[] args) {
        CardFooterButton cbutton = new CardFooterButton(new ImageIcon("app/src/main/resources/icon-effacer.png"));
        cbutton.setBorder(BorderPallet.CIRCLE);

        JFrame jf = new JFrame();
        jf.setLayout(new BorderLayout());
        jf.getContentPane().add(cbutton, CENTER);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
