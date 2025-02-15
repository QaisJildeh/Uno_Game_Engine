import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiscardPile {
    private static DiscardPile singletonDiscardPile;
    private List<Card> discardPile;

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

    @Override
    public int hashCode() {
        return Objects.hash(discardPile);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(!(obj instanceof DiscardPile)){
            return false;
        }

        DiscardPile pile = (DiscardPile) obj;
        return this.discardPile.equals(pile);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n#Discard Pile Cards:");
        for(Card card : discardPile){
            sb.append(card.toString());
        }

        return sb.toString();
    }
}