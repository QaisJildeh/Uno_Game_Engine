import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private final String playerName;
    private List<Card> hand;

    public Player(String playerName){
        this.playerName = playerName;
        hand = new ArrayList<Card>();
    }

    public String getPlayerName(){
        return playerName;
    }

    public List<Card> getPlayerHand(){
        return hand;
    }

    public void drawCard(Card newCard){
        hand.add(newCard);
    }

    public Card chooseCard(int cardNumber){
        if(cardNumber < 0 || cardNumber >= hand.size()){
            System.out.println("Invalid Card Selection!");
            return null;
        }
        else{
            return hand.get(cardNumber);
        }
    }

    public Card playCard(int chosenCard){
        if(chosenCard < 0 || chosenCard >= hand.size()){
            System.out.println("Invalid Card Selection!");
            return null;
        }
        else{
            return hand.remove(chosenCard);
        }
    }

    public void printPlayerHand(){
        System.out.println(getPlayerName() + "'s" + " hand:");
        for(int i = 0; i < hand.size(); i++){
            System.out.print("[" + (i+1) + "] ");
            hand.get(i).getCardDetails();
        }
    }

    public boolean isHandEmpty(){
        return hand.isEmpty();
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, hand);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(!(obj instanceof Player)){
            return false;
        }

        Player player = (Player) obj;
        return this.playerName.equals(player.playerName) && this.hand.equals(player.hand);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nPlayer Name: " + getPlayerName());
        for(Card card : hand){
            stringBuilder.append(card.toString());
        }

        return stringBuilder.toString();
    }
}
