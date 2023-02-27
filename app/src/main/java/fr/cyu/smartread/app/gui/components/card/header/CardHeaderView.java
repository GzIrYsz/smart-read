package fr.cyu.smartread.app.gui.components.card.header;

import fr.cyu.smartread.app.gui.components.card.header.components.MainStatisticsLabel;
import fr.cyu.smartread.app.gui.components.card.header.components.SecondaryStatisticsLabel;

import javax.swing.*;
import java.awt.*;

public class CardHeaderView extends JPanel {
    private MainStatisticsLabel stat1;
    private SecondaryStatisticsLabel stat2;
    private SecondaryStatisticsLabel stat3;

    public CardHeaderView() {
        super();
        init();
    }

    protected void init() {
        stat1 = new MainStatisticsLabel();
        stat2 = new SecondaryStatisticsLabel();
        stat3 = new SecondaryStatisticsLabel();

        JPanel secondaryStats = new JPanel();
        secondaryStats.add(stat2);
        secondaryStats.add(stat3);

        add(stat1);
        add(secondaryStats);
    }

    public MainStatisticsLabel getStat1() {
        return stat1;
    }

    public SecondaryStatisticsLabel getStat2() {
        return stat2;
    }

    public SecondaryStatisticsLabel getStat3() {
        return stat3;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("test" + CardHeaderView.class.getSimpleName());

        Container container = jf.getContentPane();

        CardHeaderView cardHeaderView = new CardHeaderView();
        cardHeaderView.getStat1().setText("J = 53%");
        cardHeaderView.getStat2().setText("I = 23%");
        cardHeaderView.getStat3().setText("L = 19%");

        container.add(cardHeaderView);

        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
