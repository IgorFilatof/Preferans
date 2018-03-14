package controller;

import model.Card;
import model.Hand;
import model.Rank;
import model.Suit;

import java.util.ArrayList;
import java.util.Random;


public class ControllerDeck {

    private ArrayList<Card> cardsInDeck;


    public ControllerDeck() {
        cardsInDeck = new ArrayList<>(32);
    }


    public void shuffle() {
        Random random = new Random();
        for (int i = cardsInDeck.size() - 1; i > 0; i--) {
            int peak = random.nextInt(i);
            Card randCard = cardsInDeck.get(peak);
            Card lastCard = cardsInDeck.get(i);
            cardsInDeck.set(i, randCard);
            cardsInDeck.set(peak, lastCard);
        }
    }

    public void addCardInDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cardsInDeck.add(new Card(rank, suit));
            }
        }
    }

    public void dealCards(ArrayList<Hand> hands) {

        for (int i = 0; i < 30; ) {
            for (Hand hand : hands) {
                hand.getCardsHand().add(cardsInDeck.get(i));
                i++;
                hand.getCardsHand().add(cardsInDeck.get(i));
                i++;
            }
        }
    }

    public Card addCard(int i) {
        return cardsInDeck.get(i);
    }


    public void removeAllCard(Hand hand) {
        hand.getListHighRankCard().clear();
        hand.getListLowRankCard().clear();
        hand.getCardsHand().clear();
    }
}
