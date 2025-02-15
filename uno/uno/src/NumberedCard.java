public class NumberedCard extends Card{
    private final String cardType;

    public NumberedCard(String color, int cardValue){
        super(color, cardValue);
        cardType = "Numbered Card";
    }

    public NumberedCard(Card card){
        super(card);
        cardType = "Numbered Card";
    }

    @Override
    public void getCardDetails(){
        System.out.println(cardType + " ===> [" + getCardValue() + ", " + getCardColor() + "]");
    }
}