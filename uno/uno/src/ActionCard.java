public class ActionCard extends Card{
    private final String cardType;
    private String actionType;

    public ActionCard(String color, int cardValue){
        super(color, cardValue);
        cardType = "Action Card";

        if(cardValue == -1){
            actionType = "Skip";
        }
        else if(cardValue == -2){
            actionType = "Reverse";
        }
        else if(cardValue == -3){
            actionType = "Draw Two";
        }
    }

    public String getActionType(){
        return actionType;
    }

    @Override
    public void getCardDetails(){
        System.out.println(cardType + " ===> [" + getActionType() + ", " + getCardColor() + "]");
    }
}