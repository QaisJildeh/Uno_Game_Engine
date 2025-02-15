import java.util.Objects;

public abstract class Card {
    private String color;
    private int cardValue;

    public Card(String color, int cardValue){
        this.color = color;
        this.cardValue = cardValue;
    }

    public Card(Card card){
        this.color = card.getCardColor();
        this.cardValue =  card.getCardValue();
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public String getCardColor(){
        return color;
    }

    public int getCardValue(){
        return cardValue;
    }

    public abstract void getCardDetails();

    @Override
    public int hashCode() {
        return Objects.hash(cardValue, color);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(!(obj instanceof Card)){
            return false;
        }

        Card card = (Card) obj;
        return this.cardValue == card.cardValue && this.color.equals(card.color);
    }

    @Override
    public String toString(){
        return getCardValue() + getCardColor();
    }
}