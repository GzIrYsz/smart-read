package fr.cyu.smartread.app.gui.components.menubar.components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenu extends JMenu {
    private JMenuItem exitItem;
    public FileMenu() {
        super("File");
        init();
    }

    protected void init() {
        exitItem = new JMenuItem("Quit");
        exitItem.addActionListener(new ExitAction());

        add(exitItem);
    }

    private class ExitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}