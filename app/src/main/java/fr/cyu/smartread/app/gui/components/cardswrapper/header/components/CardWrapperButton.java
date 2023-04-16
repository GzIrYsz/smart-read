package fr.cyu.smartread.app.gui.components.cardswrapper.header.components;

import fr.cyu.smartread.app.gui.GUITestUtility;
import fr.cyu.smartread.app.gui.pallets.BorderPallet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class CardWrapperButton extends JButton {
    public CardWrapperButton(Icon icon) {
        super(icon);
        setBorder(BorderPallet.CIRCLE);
    }

    protected void init() {
        setOpaque(true);
        setBackground(Color.GRAY);
    }

    public static void main(String[] args) {
        CardWrapperButton cardWrapperButton = new CardWrapperButton(new ImageIcon("app/src/main/resources/icons/icon-plus.png"));
        GUITestUtility.launchTest(cardWrapperButton);
    }
}
