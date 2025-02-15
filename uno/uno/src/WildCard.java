public class WildCard extends Card{
    private final String cardType;
    private String wildType;

    public WildCard(String color, int cardValue){
        super(color, cardValue);
        cardType = "Wild Card";

        if(cardValue == -4){
            wildType = "Wild";
        }
        else if(cardValue == -5){
            wildType = "Wild Draw Four";
        }
    }

    public String getWildCardType(){
        return wildType;
    }

    @Override
    public void getCardDetails(){
        System.out.println(cardType + " ===> [" + getWildCardType() + ", " + getCardColor() + "]");
    }
}