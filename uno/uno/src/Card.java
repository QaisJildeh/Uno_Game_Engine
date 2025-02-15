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


}
