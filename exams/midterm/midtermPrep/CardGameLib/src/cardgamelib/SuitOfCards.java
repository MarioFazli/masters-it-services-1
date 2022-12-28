package cardgamelib;

public enum SuitOfCards {
    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts"),
    SPADES("Spades");

    private final String suit;

    SuitOfCards(String suit){
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
