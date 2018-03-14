package model;


public enum Rank {
    SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(37), QUEEN(65), KING(90), ACE(150);


    private int rankValue;

    Rank(int rankValue) {
        this.rankValue = rankValue;
    }

    public int getRankValue() {
        return rankValue;
    }


}
