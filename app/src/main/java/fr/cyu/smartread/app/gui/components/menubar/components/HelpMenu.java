package fr.cyu.smartread.app.gui.components.menubar.components;

import fr.cyu.smartread.app.gui.components.frames.AboutFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpMenu extends JMenu {
    private JMenuItem aboutItem;

    public HelpMenu() {
        super("Help");
        init();
    }

    protected void init() {
        aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new AboutAction());

        add(aboutItem);
    }

    private class AboutAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AboutFrame();
        }
    }
}
