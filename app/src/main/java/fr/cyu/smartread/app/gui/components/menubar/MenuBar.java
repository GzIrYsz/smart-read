package fr.cyu.smartread.app.gui.components.menubar;

import fr.cyu.smartread.app.gui.components.menubar.components.FileMenu;
import fr.cyu.smartread.app.gui.components.menubar.components.HelpMenu;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        super();
        init();
    }

    protected void init() {
        add(new FileMenu());
        add(new HelpMenu());
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("test" + MenuBar.class.getSimpleName());

        MenuBar menuBar = new MenuBar();

        jf.setJMenuBar(menuBar);

        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
