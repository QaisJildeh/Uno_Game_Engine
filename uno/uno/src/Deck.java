import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static Deck singletonDeck;
    private List<Card> deck;
    private final String[] COLORS = {"Blue", "Yellow", "Red", "Green"};

    private Deck(){
        deck = new ArrayList<Card>();
        initializeDeck();
        shuffleDeck();
    }

    public static synchronized Deck getDeckInstance(){
        if(singletonDeck == null){
            singletonDeck = new Deck();
        }
        return singletonDeck;
    }

    public List<Card> getDeckCards(){
        return deck;
    }

    public boolean isEmpty(){
        return deck.isEmpty();
    }

    private void initializeNumberedCards(){
        for(String color : COLORS){
            deck.add(new NumberedCard(color, 0));
            for(int i = 1; i < 10; i++){
                deck.add(new NumberedCard(color, i));
                deck.add(new NumberedCard(color, i));
            }
        }
    }

    private void initializeActionCards(){
        for(String color : COLORS){
            for(int i = -1; i > -4; i--){
                deck.add(new ActionCard(color, i));
                deck.add(new ActionCard(color, i));
            }
        }
    }

    private void initializeWildCards(){
        for(int i = -4; i > -6; i--){
            for(int j = 0; j < 4; j++){
                deck.add(new WildCard("Colorless", i));
            }
        }
    }

    public void initializeDeck(){
        initializeNumberedCards();
        initializeActionCards();
        initializeWildCards();
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public void refillDeck(List<Card> cards){
        if(isEmpty()){
            deck.addAll(cards);
            shuffleDeck();
            System.out.println("Deck refilled and shuffled!");
        }
        else{
            System.out.println("Deck not yet empty!");
        }
    }

    public Card drawFromDeck(){
        if(isEmpty()){
            return null;
        }
        else{
            return deck.removeLast();
        }
    }
}