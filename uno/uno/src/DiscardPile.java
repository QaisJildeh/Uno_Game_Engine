import java.util.ArrayList;
import java.util.List;

public class DiscardPile {
    private static DiscardPile singletonDiscardPile;
    private static List<Card> discardPile;

    private DiscardPile(){
        discardPile = new ArrayList<Card>();
    }

    public static synchronized DiscardPile getDiscardPileInstance(){
        if(singletonDiscardPile == null){
            singletonDiscardPile = new DiscardPile();
        }
        return singletonDiscardPile;
    }

    public List<Card> getDiscardPileCards() {
        return discardPile;
    }

    public boolean isEmpty(){
        return discardPile.isEmpty();
    }

    public void addToDiscardPile(Card card){
        discardPile.add(card);
    }

    public Card getLastThrownCard(){
        if(isEmpty()){
            return null;
        }
        else{
            return discardPile.getLast();
        }
    }

    public List<Card> discardPileAsDeck(){
        List<Card> newDeck = new ArrayList<>(discardPile);
        discardPile.clear();
        discardPile.add(newDeck.removeLast());
        return newDeck;
    }
}