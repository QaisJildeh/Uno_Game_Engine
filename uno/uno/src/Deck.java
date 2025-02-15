import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Deck {
    private static Deck singletonDeck;
    private List<Card> unoDeck;
    private final String[] COLORS = {"Blue", "Yellow", "Red", "Green"};

    private Deck(){
        unoDeck = new ArrayList<Card>();
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
        return unoDeck;
    }

    public boolean isEmpty(){
        return unoDeck.isEmpty();
    }

    private void initializeNumberedCards(){
        for(String color : COLORS){
            unoDeck.add(new NumberedCard(color, 0));
            for(int i = 1; i < 10; i++){
                unoDeck.add(new NumberedCard(color, i));
                unoDeck.add(new NumberedCard(color, i));
            }
        }
    }

    private void initializeActionCards(){
        for(String color : COLORS){
            for(int i = -1; i > -4; i--){
                unoDeck.add(new ActionCard(color, i));
                unoDeck.add(new ActionCard(color, i));
            }
        }
    }

    private void initializeWildCards(){
        for(int i = -4; i > -6; i--){
            for(int j = 0; j < 4; j++){
                unoDeck.add(new WildCard("Colorless", i));
            }
        }
    }

    public void initializeDeck(){
        initializeNumberedCards();
        initializeActionCards();
        initializeWildCards();
    }

    public void shuffleDeck(){
        Collections.shuffle(unoDeck);
    }

    public void refillDeck(List<Card> cards){
        if(isEmpty()){
            unoDeck.addAll(cards);
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
            return unoDeck.removeLast();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(unoDeck);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(!(obj instanceof Deck)){
            return false;
        }

        Deck deck = (Deck) obj;
        return this.unoDeck.equals(deck);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n#UNO Deck:");
        for(Card card : unoDeck){
            stringBuilder.append(card.toString());
        }

        return stringBuilder.toString();
    }
}