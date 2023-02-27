package fr.cyu.smartread.app.gui.components.card.header.components;

import javax.swing.*;
import java.awt.*;

public class SecondaryStatisticsLabel extends JLabel {
    public SecondaryStatisticsLabel() {
        this("");
    }

    public SecondaryStatisticsLabel(String text) {
        this(text, CENTER);
    }

    public SecondaryStatisticsLabel(int horizontalAlignment, String text) {
        this(text, horizontalAlignment);
    }

    public SecondaryStatisticsLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        init();
    }

    public void init() {
        setForeground(Color.LIGHT_GRAY);
        setFont(new Font("sans serif", Font.PLAIN, 15));
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("test" + SecondaryStatisticsLabel.class.getSimpleName());
        Container container = jf.getContentPane();
        container.add(new SecondaryStatisticsLabel("J = 16%"));
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
