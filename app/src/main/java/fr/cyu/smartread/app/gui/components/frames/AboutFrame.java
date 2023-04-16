package fr.cyu.smartread.app.gui.components.frames;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class AboutFrame extends JFrame {
    public AboutFrame() {
        super("About");
        init();
    }

    protected void init() {
        setLayout(new BorderLayout());

        add(new JLabel("Bla bla bla"));

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
