package cards;

import cardgamelib.Card;
import cardgamelib.FaceOfCards;
import cardgamelib.SuitOfCards;

import java.util.*;

public class DeckOfCards {
    public List<Card> getCards() {
        return cards;
    }

    private List<Card> cards;
    private int currentCard;

    public DeckOfCards() {
        currentCard = 0;
        cards = new ArrayList<Card>();
        FaceOfCards[] faces = FaceOfCards.values();
        SuitOfCards[] suits = SuitOfCards.values();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {

                Card card = new Card(faces[j], suits[i]);
                cards.add(card);
            }
        }
    }

    public void shuffleCards() {
        currentCard = 0;
        Random random = new Random();
        for (int i = 0; i < 52; i++) {
            int rand = random.nextInt(52);
            Card card1 = cards.get(i);
            Card card2 = cards.get(rand);
            cards.set(rand, card1);
            cards.set(i, card2);
        }
    }

    public Card[] dealFive() {
        Card[] five = new Card[5];
        for (int i = 0; i < 5; i++) {
            if (currentCard + i < 52) {
                five[i] = cards.get(currentCard);
                currentCard++;
            } else {
                five[i] = null;
            }
        }
        return five;
    }

    public static void sortByFaceCards(List<Card> list) {
        list.sort(Comparator.comparing(Card::getFace));
        for(Card card: list){
            System.out.println(card.toString());
        }
    }

    public void printBySuits() {
        List<Card> sortedCards = new ArrayList<>(cards);
        sortedCards.sort(Comparator.comparing(Card::getSuit));
        int k = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println(SuitOfCards.values()[i] + ": ");
            for (int j = k; j < k + 13; j++) {
                System.out.println(sortedCards.get(j).getFace() + " " + sortedCards.get(j).getSuit());
            }
            System.out.println("\n");
            k += 13;
        }
    }

    public List<FaceOfCards> getDistinctFaces(Card[] cards){
        Arrays.sort(cards, Comparator.comparing(Card::getFace));
        List<FaceOfCards> faces = new ArrayList<>();

        for(Card card: cards){
            if(!faces.contains(card.getFace())){
                faces.add(card.getFace());
            }
        }
        return faces;
    }

    public static void print(List<Card> cards){
        for(Card card : cards){
            if (card != null) {
                System.out.println(card.toString());
            } else {
                System.out.println("null");
            }
        }
    }

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();

        System.out.println("a)\n");
        deck.shuffleCards();
        print(deck.getCards());
        System.out.println("\n");

        System.out.println("b)\n");
        Card[] five = deck.dealFive();
        print(Arrays.asList(five));
        System.out.println("\n");

        System.out.println("c)\n");
        List<Card> sortedCards = new ArrayList<>(deck.getCards());
        sortByFaceCards(sortedCards);
        System.out.println("\n");

        System.out.println("d)\n");
        deck.printBySuits();
        System.out.println("\n");

        System.out.println("e)\n");
        print(Arrays.asList(five));
        List<FaceOfCards> faces = deck.getDistinctFaces(five);
        for(FaceOfCards face: faces){
            System.out.println(face);
        }

    }
}
