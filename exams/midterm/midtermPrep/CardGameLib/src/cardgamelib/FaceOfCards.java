package cardgamelib;

public enum FaceOfCards {
    ACE("Ace"),
    DEUCE("Deuce"),
    THREE("Three"),
    FOUR("Four"),
    FIVE("Five"),
    SIX("Six"),
    SEVEN("Seven"),
    EIGHT("Eight"),
    NINE("Nine"),
    TEN("Ten"),
    JACK("Jack"),
    QUEEN("Queen"),
    KING("King");

    private final String face;

    FaceOfCards(String face){
        this.face = face;
    }

    public String getFace(){
        return this.face;
    }


}
