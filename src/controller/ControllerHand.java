package controller;


import model.*;

import java.util.ArrayList;

public class ControllerHand {


    public ControllerHand() {

    }


    public void printCardInHand(Hand hand) {
        for (Card card : hand.getCardsHand()) {
            System.out.println(card);
        }
    }


    public void takeBribeInHand(Hand hand) {

        ArrayList<Card> cardsHand = hand.getCardsHand();

        for (Card card : cardsHand) {
            if ((card.getRank().equals(Rank.ACE)) || (card.getRank().equals(Rank.KING)) || (card.getRank().equals(Rank.QUEEN)) || (card.getRank().equals(Rank.JACK))) {
                hand.getListHighRankCard().add(card);
            } else {
                hand.getListLowRankCard().add(card);

            }
        }
    }


    public int countBidding(Hand hand) {
        return hand.getListHighRankCard().size();
    }


    public Suit separationCardInHand(Hand hand) {
        ArrayList<Card> listHighRankOfClubs = new ArrayList<>();
        ArrayList<Card> listHighRankOfDiamonds = new ArrayList<>();
        ArrayList<Card> listHighRankOfHearts = new ArrayList<>();
        ArrayList<Card> listHighRankOfSpades = new ArrayList<>();
        Suit suit = null;

        for (Card card : hand.getListHighRankCard()) {
            if (card.getSuit().equals(Suit.CLUBS)) {
                listHighRankOfClubs.add(card);
            } else if (card.getSuit().equals(Suit.DIAMONDS)) {
                listHighRankOfDiamonds.add(card);
            } else if (card.getSuit().equals(Suit.HEARTS)) {
                listHighRankOfHearts.add(card);
            } else if (card.getSuit().equals(Suit.SPADES)) {
                listHighRankOfSpades.add(card);
            }
        }

        int[] mass = new int[4];
        mass[0] = listHighRankOfClubs.size();
        mass[1] = listHighRankOfDiamonds.size();
        mass[2] = listHighRankOfHearts.size();
        mass[3] = listHighRankOfSpades.size();

        int i = 0;
        int max = 0;

        for (int j = 0; j < mass.length; j++) {
            if (mass[j] > max) {
                max = mass[j];
                i = j;
            }
        }
        switch (i) {
            case 0:
                suit = Suit.CLUBS;
                break;
            case 1:
                suit = Suit.DIAMONDS;
                break;
            case 2:
                suit = Suit.HEARTS;
                break;
            case 3:
                suit = Suit.SPADES;
                break;
        }
        return suit;
    }


    public void decisionOfThePlayer(Hand hand) {
        if ((hand.getContracts().getSuit() != separationCardInHand(hand)) && (hand.getListHighRankCard().size() < 6)) {
            hand.setPass(false);
        } else {
            hand.setPass(true);
        }
    }

    private Card getLowRankCard(Hand hand, Suit suit) {
        ArrayList<Card> list = hand.getListLowRankCard();
        int i = 0;
        int max = 0;

        for (int j = 0; j < list.size(); j++) {
            if ((list.get(j).getIdNumber() > max) && (list.get(j).getSuit().equals(suit))) {
                max = list.get(j).getIdNumber();
                i = j;
            }
        }
        return list.get(i);

    }


    private Card getHighRankCard(Hand hand, Suit suit) {
        ArrayList<Card> list = hand.getListHighRankCard();
        int i = 0;
        int max = 0;

        for (int j = 0; j < list.size(); j++) {
            if ((list.get(j).getIdNumber() > max) && (list.get(j).getSuit().equals(suit))) {
                max = list.get(j).getIdNumber();
                i = j;
            }
        }
        return list.get(i);
    }


    private Card changeCardInHandDetermineSuit(Hand hand, Suit suit) {
        Card card = null;
        if (hand.getListHighRankCard().isEmpty()) {
            card = getLowRankCard(hand, suit);
        } else {
            card = getHighRankCard(hand, suit);
        }
        return card;
    }


    public int getBiggestTrade(ArrayList<Hand> hands) {
        int i = 0;
        int max = 0;

        for (int j = 0; j < hands.size(); j++) {
            if (hands.get(j).getContracts().getWeight() > max) {
                max = hands.get(j).getContracts().getWeight();
                i = j;
            }
        }
        return i;
    }

    public Card getCardInDeck(Hand hand) {
        ArrayList<Card> cardsHand = hand.getCardsHand();
        int max = 0;
        int i = 0;
        for (int j = 0; j < cardsHand.size(); j++) {
            if (cardsHand.get(j).getRank().getRankValue() > max) {
                max = cardsHand.get(j).getRank().getRankValue();
                i = j;
            }
        }
        return cardsHand.get(i);

    }


    public Card alternativeCard(Hand hand, Suit suit) {
        Card card = null;
        for (Suit newSuit : Suit.values()) {
            card = changeCardInHandDetermineSuit(hand, newSuit);
            if (card != null) {
                break;
            }
        }
        return card;
    }


    public Card getMinCardInHandBySuit(Hand hand) {
        ArrayList<Card> cardsHand = hand.getCardsHand();

        int min = 0;
        int i = 0;
        for (int j = 0; j < cardsHand.size(); j++) {
            if ((cardsHand.get(j).getRank().getRankValue() < min) && (cardsHand.get(j).getSuit() != hand.getContracts().getSuit())) {
                min = cardsHand.get(j).getRank().getRankValue();
                i = j;
            }
        }
        return hand.getCardsHand().get(i);
    }


    public Contracts getContracts(Hand hand) {
        Suit suit = separationCardInHand(hand);
        int i = countBidding(hand);
        Contracts contracts = null;
        for (Contracts c : Contracts.values()) {
            if ((c.getRankValue() == i) || (c.getSuit().equals(suit))) {
                contracts = c;
            }
        }
        return contracts;
    }

    public void countPoint(Hand hand) {
        int rankValue = hand.getContracts().getRankValue();
        if (rankValue > hand.getCountPoint()) {
            int i = rankValue - hand.getCountPoint();
            hand.setPull(hand.getPull() + hand.getCountPoint() * hand.getContracts().getGamersPoint());
            hand.setMountain(hand.getMountain() + i * (hand.getContracts().getGamersPoint() * 2));
        } else {
            hand.setPull(hand.getPull() + hand.getCountPoint() * hand.getContracts().getGamersPoint());
        }
    }

    public void removeCard(Card card, Hand hand) {
        hand.getCardsHand().remove(card);
        hand.getListHighRankCard().remove(card);
        hand.getListLowRankCard().remove(card);
    }


}
