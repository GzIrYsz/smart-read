package fr.cyu.smartread.app.gui.components.card.header.components;

import javax.swing.*;
import java.awt.*;

public class MainStatisticsLabel extends JLabel {
    public  MainStatisticsLabel() {
        this("");
    }

    public MainStatisticsLabel(String text) {
        this(text, CENTER);
    }

    public MainStatisticsLabel(int horizontalAlignment, String text) {
        this(text, horizontalAlignment);
    }

    public MainStatisticsLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        init();
    }

    public void init() {
        setForeground(Color.GRAY);
        setFont(new Font("sans serif", Font.PLAIN, 20));
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("test" + MainStatisticsLabel.class.getSimpleName());
        Container container = jf.getContentPane();
        container.add(new MainStatisticsLabel("M = 53%"));
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}