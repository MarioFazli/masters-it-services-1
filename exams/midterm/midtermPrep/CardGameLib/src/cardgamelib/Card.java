package cardgamelib;

public class Card {
    private FaceOfCards face; // сила на карта
    private SuitOfCards suit;

    public FaceOfCards getFace() {
        return face;
    }

    public void setFace(FaceOfCards face) {
        this.face = face;
    }

    public SuitOfCards getSuit() {
        return suit;
    }

    public void setSuit(SuitOfCards suit) {
        this.suit = suit;
    }

    public Card(FaceOfCards face, SuitOfCards suit){
        setFace(face);
        setSuit(suit);
    }

    @Override
    public String toString(){
        return String.format("%s of %s", getFace().getFace(), getSuit().getSuit());
    }
}
