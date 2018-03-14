package model;

public enum Contracts {
    SIX_SPADE(6, Suit.SPADES, 1, 2, 4), SEVEN_SPADE(7, Suit.SPADES, 5, 4, 2), EIGHT_SPADES(8, Suit.SPADES, 9, 6, 1), NINE_SPADES(9, Suit.SPADES, 13, 8, 1), TEN_SPADES(10, Suit.SPADES, 17, 10, 1),

    SIX_CLUBS(6, Suit.CLUBS, 2, 2, 4), SEVEN_CLUBS(7, Suit.CLUBS, 6, 4, 2), EIGHT_CLUBS(8, Suit.CLUBS, 10, 6, 1), NINE_CLUBS(9, Suit.CLUBS, 14, 8, 1), TEN_CLUBS(10, Suit.CLUBS, 18, 10, 1),

    SIX_DIAMOND(6, Suit.DIAMONDS, 3, 2, 4), SEVEN_DIAMONDS(7, Suit.DIAMONDS, 7, 4, 2), EIGHT_DIAMONDS(8, Suit.DIAMONDS, 11, 6, 1), NINE_DIAMONDS(9, Suit.DIAMONDS, 15, 8, 1), TEN_DIAMONDS(10, Suit.DIAMONDS, 19, 10, 1),

    SIX_HEARTS(6, Suit.HEARTS, 4, 2, 4), SEVEN_HEARTS(7, Suit.HEARTS, 8, 4, 2), EIGHT_HEARTS(8, Suit.HEARTS, 12, 6, 1), NINE_HEARTS(9, Suit.HEARTS, 16, 8, 1), TEN_HEARTS(10, Suit.HEARTS, 20, 10, 1);


    private int rankValue;
    private Suit suit;
    private int weight;
    private int gamersPoint;
    private int numberBribes;

    Contracts(int rankValue, Suit suit, int weight, int gamersPoint, int numberBribes) {
        this.rankValue = rankValue;
        this.suit = suit;
        this.weight = weight;
        this.gamersPoint = gamersPoint;
        this.numberBribes = numberBribes;
    }

    public int getRankValue() {
        return rankValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getWeight() {
        return weight;
    }

    public int getGamersPoint() {
        return gamersPoint;
    }

    public int getNumberBribes() {
        return numberBribes;
    }
}
