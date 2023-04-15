package fr.cyu.smartread.app.gui;

import javax.swing.*;
import java.awt.*;

public class GUITestUtility {
    public static void launchTest(Component component) {
        JFrame jf = new JFrame("test" + Component.class.getSimpleName());
        jf.add(component);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
