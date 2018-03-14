package controller;


import model.Card;
import model.Contracts;
import model.Hand;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import java.io.IOException;
import java.util.ArrayList;


public class ControllerGame {

    private ControllerHand controllerHand;
    private ControllerDeck controllerDeck;
    private ArrayList<Card> cardInTable;
    private ArrayList<Card> cardInDumping;
    private Logger logger = Logger.getLogger("Preferance");

    public ControllerGame() {
        controllerDeck = new ControllerDeck();
        controllerHand = new ControllerHand();
        cardInTable = new ArrayList<>();
        cardInDumping = new ArrayList<>();

    }


    public void playerCoarse(ArrayList<Hand> hands) {
        for (Hand hand : hands) {
            hand.setCard(controllerHand.alternativeCard(hand, hand.getContracts().getSuit()));
        }
    }


    public void addCardInTable(ArrayList<Hand> hands) {
        for (Hand hand : hands) {
            cardInTable.add(hand.getCard());
        }
    }

    public int changeWinner(ArrayList<Hand> hands) {
        int max = 0;
        int i = 0;
        for (int j = 0; j < hands.size(); j++) {
            if (hands.get(j).getCard().getIdNumber() > max) {
                max = hands.get(j).getCard().getIdNumber();
                i = j;
            }
        }
        return i;
    }

    public void addCardInHandWhoWinInTheTrade(Hand hand) {
        hand.getCardsHand().add(cardInDumping.get(0));
        hand.getCardsHand().add(cardInDumping.get(1));
    }

    public void addCardInDumping() {
        cardInDumping.add(controllerDeck.addCard(30));
        cardInDumping.add(controllerDeck.addCard(31));
    }

    public void addCountPointWinHand(Hand hand) {
        hand.setCountPoint(hand.getCountPoint() + 1);
    }

    public void cleanTable() {
        cardInTable.clear();
    }

    public void cicle() {

        try {
            logger.addAppender(new FileAppender(new SimpleLayout(), "log.txt"));
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }


        controllerDeck.addCardInDeck();
        controllerDeck.shuffle();
        ArrayList<Hand> hands = new ArrayList<>(3);
        //  Hand[] hands = new Hand[3];

        Hand hand1 = new Hand("first");
        Hand hand2 = new Hand("second");
        Hand hand3 = new Hand("third");
        hands.add(hand1);
        hands.add(hand2);
        hands.add(hand3);

        System.out.println("В игре 3 человека");
        logger.info("В игре 3 человека");
        for (Hand hand : hands) {
            System.out.println(hand);
            logger.info(hand);
        }


//сдача карт
        controllerDeck.dealCards(hands);


        System.out.println("Карты");
        logger.info("Карты");
        System.out.println(hand1 + " :");
        logger.info(hand1);
        controllerHand.printCardInHand(hand1);
        logger.info(hand1.getCardsHand());

        System.out.println(hand2 + " :");
        logger.info(hand2);
        controllerHand.printCardInHand(hand2);
        logger.info(hand2.getCardsHand());

        System.out.println(hand3 + " :");
        logger.info(hand3);
        controllerHand.printCardInHand(hand3);
        logger.info(hand3.getCardsHand());
        System.out.println();


        addCardInDumping();
        for (Hand hand : hands) {
            controllerHand.takeBribeInHand(hand);
        }
        System.out.println("В сбросе карты");
        logger.info("В сбросе карты");
        for (Card card : cardInDumping) {
            System.out.println(card);
        }
        logger.info(cardInDumping);

        System.out.println("Торги");
        logger.info("Торги");
        hand1.setContracts(controllerHand.getContracts(hand1));
        // hand1.setContracts();
        hand2.setContracts(controllerHand.getContracts(hand2));
        hand3.setContracts(controllerHand.getContracts(hand3));

        logger.info("Участники выставилили карты");
        for (Hand hand : hands) {
            System.out.println("Участники выставилили карты " + hand + " " + hand.getContracts());
            logger.info(hand + " " + hand.getContracts());
        }


        int biggestTrade = controllerHand.getBiggestTrade(hands);
        System.out.println("Выиграл торги " + hands.get(biggestTrade));
        logger.info("Выиграл торги " + hands.get(biggestTrade));
        addCardInHandWhoWinInTheTrade(hands.get(biggestTrade));


        System.out.println("Карты в прикупе ");
        logger.info("Карты в прикупе ");
        for (Card card : cardInDumping) {
            System.out.println(card);
            logger.info(card);
        }
        Card minCard = controllerHand.getMinCardInHandBySuit(hands.get(biggestTrade));
        controllerHand.removeCard(minCard, hands.get(biggestTrade));
        Card minCard2 = controllerHand.getMinCardInHandBySuit(hands.get(biggestTrade));
        controllerHand.removeCard(minCard2, hands.get(biggestTrade));
        System.out.println("Игрок выбрасывает карту " + minCard);
        logger.info("Игрок выбрасывает карту " + minCard);
        System.out.println("Игрок выбрасывает карту " + minCard2);
        logger.info("Игрок выбрасывает карту " + minCard);
        controllerHand.takeBribeInHand(hands.get(biggestTrade));


        playerCoarse(hands);

        for (int i = 0; i < 10; i++) {

            System.out.println("Карты первого ");
            controllerHand.printCardInHand(hand1);
            logger.info("Карты первого");
            logger.info(hand1.getCardsHand());

            System.out.println("Карты второго ");
            controllerHand.printCardInHand(hand2);
            logger.info("Карты второго");
            logger.info(hand2.getCardsHand());

            System.out.println("Карты третьего ");
            controllerHand.printCardInHand(hand3);
            logger.info("Карты третьего");
            logger.info(hand3.getCardsHand());

            for (Hand hand : hands) {
                hand.setCard(controllerHand.getCardInDeck(hand));
                System.out.println(hand + " ходит картой  " + hand.getCard());
                logger.info(hand + " ходит картой  " + hand.getCard());
                controllerHand.removeCard(hand.getCard(), hand);
                controllerHand.decisionOfThePlayer(hand);
            }


            addCardInTable(hands);
            for (Card card : cardInTable) {
                System.out.println("Карты на столе " + card);
                logger.info("Карты на столе " + card);
            }

            int winner = changeWinner(hands);
            addCountPointWinHand(hands.get(winner));
            System.out.println("Выиграл " + hands.get(winner));
            logger.info("Выиграл " + hands.get(winner));
            cleanTable();

        }

        for (Hand hand : hands) {
            // System.out.println(hand + " счет " + hand.getCountPoint());
            //logger.info(hand + " счет " + hand.getCountPoint());
            controllerHand.countPoint(hand);
            System.out.println(hand + " пуля " + hand.getPull() + " гора " + hand.getMountain());
            logger.info(hand + " пуля " + hand.getPull() + " гора " + hand.getMountain());
        }
    }
}
