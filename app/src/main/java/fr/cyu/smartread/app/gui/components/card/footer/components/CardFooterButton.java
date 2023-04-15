package fr.cyu.smartread.app.gui.components.card.footer.components;

import fr.cyu.smartread.app.gui.pallets.BorderPallet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class CardFooterButton extends JLabel {
    private static final Dimension preferredSize = new Dimension(45, 45);
    public CardFooterButton(String filename) {
        this(new ImageIcon(filename));
        setPreferredSize(preferredSize);
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
        CardFooterButton cbutton = new CardFooterButton(new ImageIcon("app/src/main/resources/icons/icon-effacer.png"));
        cbutton.setBorder(BorderPallet.CIRCLE);

        JFrame jf = new JFrame();
        jf.setLayout(new BorderLayout());
        jf.getContentPane().add(cbutton, CENTER);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
