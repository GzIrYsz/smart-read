package fr.cyu.smartread.app.gui.components.card;

import fr.cyu.smartread.app.gui.components.card.body.CardBodyController;
import fr.cyu.smartread.app.gui.components.card.body.CardBodyView;
import fr.cyu.smartread.app.gui.components.card.footer.CardFooterView;
import fr.cyu.smartread.app.gui.components.card.header.CardHeaderView;

import javax.swing.*;
import java.awt.*;

public class CardView extends JPanel {
    CardHeaderView cardHeaderView;
    CardBodyView cardBodyView;
    CardFooterView cardFooterView;

    public CardView() {
        super();
        init();
    }

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        cardHeaderView = new CardHeaderView();
        cardBodyView = new CardBodyView();
        cardFooterView = new CardFooterView();

        CardBodyController cardBodyController = new CardBodyController(cardBodyView);
        cardBodyView.addMouseListener(cardBodyController);
        cardBodyView.addMouseMotionListener(cardBodyController);

        add(cardHeaderView);
        add(cardBodyView);
        add(cardFooterView);
    }

    public CardHeaderView getCardHeaderView() {
        return cardHeaderView;
    }

    public CardBodyView getCardBodyView() {
        return cardBodyView;
    }

    public CardFooterView getCardFooterView() {
        return cardFooterView;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("test" + CardView.class.getSimpleName());

        Container container = jf.getContentPane();

        CardView cardView = new CardView();

        cardView.getCardHeaderView().getStat1().setText("J = 35%");
        cardView.getCardHeaderView().getStat2().setText("I = 15%");
        cardView.getCardHeaderView().getStat3().setText("L = 3%");
        cardView.getCardBodyView().setPreferredSize(new Dimension(128, 128));

        container.add(cardView);

        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
