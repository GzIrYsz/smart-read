package fr.cyu.smartread.app.gui.components.card.header;

import fr.cyu.smartread.app.gui.components.card.events.EventStatisticUpdate;
import fr.cyu.smartread.app.gui.components.card.header.components.MainStatisticsLabel;
import fr.cyu.smartread.app.gui.components.card.header.components.SecondaryStatisticsLabel;
import fr.cyu.smartread.app.gui.observer.EventType;
import fr.cyu.smartread.app.gui.observer.Observer;
import fr.cyu.smartread.app.wrappers.deeplearning.PredictedLetter;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CardHeaderView extends JPanel implements Observer {
    private final static String STAT_STR_TEMPLATE = "%c = %s%%";
    private final static DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("00.#");
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

    @Override
    public void update(EventType eventType, Object data) {
        if (eventType.getEventName().equals(EventStatisticUpdate.EVENT_NAME)) {
            updateStats((ArrayList<PredictedLetter>) data);
        }
    }

    private void updateStats(ArrayList<PredictedLetter> data) {
        setStat(data.get(0), getStat1());
        setStat(data.get(1), getStat2());
        setStat(data.get(2), getStat3());
    }

    private void setStat(PredictedLetter predictedLetter, JLabel statisticsLabel) {
        String newStatistics = formatStrForStat(predictedLetter);
        statisticsLabel.setText(newStatistics);
    }

    private String formatStrForStat(PredictedLetter predictedLetter) {
        String formattedDecimal = DECIMAL_FORMATTER.format(predictedLetter.getPercentOfConfidence());
        return String.format(STAT_STR_TEMPLATE, predictedLetter.getLetter(), formattedDecimal);
    }
}
