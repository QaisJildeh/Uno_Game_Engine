import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerName;
    private List<Card> hand;

    public Player(String playerName){
        this.playerName = playerName;
        hand = new ArrayList<Card>();
    }

    public String getPlayerName(){
        return playerName;
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
            System.out.print("[" + i + "] ");
            hand.get(i).getCardDetails();
        }
    }

    public boolean isHandEmpty(){
        return hand.isEmpty();
    }
}
