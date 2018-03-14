package model;


import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cardsHand;
    private String name;
    private boolean isPass;
    private Contracts contracts;
    private ArrayList<Card> listHighRankCard;
    private ArrayList<Card> listLowRankCard;
    private int mountain;
    private int pull;

    private Card card;
    private int countPoint;

    public int getMountain() {
        return mountain;
    }

    public void setMountain(int mountain) {
        this.mountain = mountain;
    }

    public int getPull() {
        return pull;
    }

    public void setPull(int pull) {
        this.pull = pull;
    }

    public Hand(String name) {
        cardsHand = new ArrayList(10);
        this.name = name;
        isPass = false;
        countPoint = 0;
        listHighRankCard = new ArrayList<>();
        listLowRankCard = new ArrayList<>();
        pull = 0;
        mountain = 0;

    }

    public ArrayList<Card> getCardsHand() {
        return cardsHand;
    }

    public String getName() {
        return name;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }


    public ArrayList<Card> getListHighRankCard() {
        return listHighRankCard;
    }

    public ArrayList<Card> getListLowRankCard() {
        return listLowRankCard;
    }

    public Contracts getContracts() {
        return contracts;
    }

    public void setContracts(Contracts contracts) {
        this.contracts = contracts;
    }

    public Card getCard() {
        return card;
    }

    public int getCountPoint() {
        return countPoint;
    }

    public void setCountPoint(int countPoint) {
        this.countPoint = countPoint;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return name;
    }
}
