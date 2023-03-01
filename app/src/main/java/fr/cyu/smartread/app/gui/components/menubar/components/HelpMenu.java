package fr.cyu.smartread.app.gui.components.menubar.components;

import fr.cyu.smartread.app.gui.components.frames.AboutFrame;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpMenu extends JMenu {
    private JMenuItem about;

    public HelpMenu() {
        super("Help");
        init();
    }

    protected void init() {
        about = new JMenuItem("About");
        about.addActionListener(new AboutAction());

        add(about);
    }

    private class AboutAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AboutFrame();
        }
    }
}
