package fr.cyu.smartread.app.gui.components.menubar.components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileMenu extends JMenu {
    private JMenuItem exit;
    public FileMenu() {
        super("File");
        init();
    }

    protected void init() {
        exit = new JMenuItem("Quit");
        exit.addActionListener(new ExitAction());

        add(exit);
    }

    private class ExitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}