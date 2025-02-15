import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Game {
    protected static Deck deck;
    protected static DiscardPile discardPile;
    protected List<Player> players;
    protected int currentPlayerIndex = 0;
    protected boolean skipFlag = false;
    protected boolean reverseFlag = false;
    protected boolean drawTwoFlag = false;
    protected boolean wildFlag = false;
    protected boolean drawFourFlag = false;
    protected final String[] COLORS = {"Blue", "Yellow", "Red", "Green"};

    protected Scanner scanner = new Scanner(System.in);

    public Game(){
        deck = Deck.getDeckInstance();
        discardPile = DiscardPile.getDiscardPileInstance();
        players = new ArrayList<Player>();

        initializeGame();
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public void setSkipFlag(boolean skipFlag){
        this.skipFlag = skipFlag;
    }

    public void setReverseFlag(boolean reverseFlag){
        this.reverseFlag = reverseFlag;
    }

    public void setDrawTwoFlag(boolean drawTwoFlag){
        this.drawTwoFlag = drawTwoFlag;
    }

    public void setWildFlag(boolean wildFlag){
        this.wildFlag = wildFlag;
    }

    public void setDrawFourFlag(boolean drawFourFlag){
        this.drawFourFlag = drawFourFlag;
    }

    public static Deck getDeck(){
        return deck;
    }

    public static DiscardPile getDiscardPile(){
        return discardPile;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public boolean isSkipFlag(){
        return skipFlag;
    }

    public boolean isReverseFlag(){
        return reverseFlag;
    }

    public boolean isDrawTwoFlag(){
        return drawTwoFlag;
    }

    public boolean isWildFlag(){
        return wildFlag;
    }

    public boolean isDrawFourFlag(){
        return drawFourFlag;
    }

    public int getCurrentPlayerIndex(){
        return currentPlayerIndex;
    }

    public Player getCurrentPlayer(){
        return getPlayers().get(getCurrentPlayerIndex());
    }

    public void initializeGame(){
        setPlayersNames();
        distributeCards();
        startGame();
    }

    public void setPlayersNames(){
        System.out.print("Enter the number of players (2-10): ");
        int numberOfPlayers = scanner.nextInt();
        while(numberOfPlayers < 2 || numberOfPlayers > 10){
            System.out.println("\nInvalid number of players. Try again.");
            System.out.print("Enter the number of players (2-10): ");
            numberOfPlayers = scanner.nextInt();
        }

        for(int i = 0; i < numberOfPlayers; i++){
            System.out.print("Player " + (i+1) + " Name: ");
            players.add(new Player(scanner.next()));
        }
    }

    public void distributeCards(){
        for(Player player: players){
            for(int i = 0; i < 7; i++){
                player.drawCard(deck.drawFromDeck());
            }
        }
    }

    public void startGame(){
        Card firstCard = deck.drawFromDeck();
        while(firstCard.getCardValue() < 0){
            discardPile.addToDiscardPile(firstCard);
            firstCard = deck.drawFromDeck();
        }

        discardPile.addToDiscardPile(firstCard);
        System.out.println("Game has started!");
    }

    public void updateNextPlayerIndex(){
        if(isReverseFlag()){
            if(getCurrentPlayerIndex() == 0){
                setCurrentPlayerIndex(players.size() - 1);
            }
            else{
                setCurrentPlayerIndex(getCurrentPlayerIndex()-1);
            }
        }
        else{
            setCurrentPlayerIndex((getCurrentPlayerIndex() + 1)%getPlayers().size());
        }
    }

    public boolean isGameOver(){
        return players.stream().anyMatch(Player::isHandEmpty);
    }

    public boolean isValidPlay(Card chosenCard){
        if(chosenCard == null){
            System.out.println("No card provided!");
            return false;
        }

        int chosenCardValue = chosenCard.getCardValue();
        String chosenCardColor = chosenCard.getCardColor();
        if(chosenCardValue >= -3){
            return chosenCardValue == discardPile.getLastThrownCard().getCardValue() ||
                    chosenCardColor.equals(discardPile.getLastThrownCard().getCardColor());
        }

        return true;
    }

    public void drawCards(Player player, int numberOfDraws){
        if(deck.isEmpty()){
            System.out.println("Deck is empty! Shuffling discard pile...");
            deck.refillDeck(discardPile.discardPileAsDeck());
        }

        for(int i = 0; i < numberOfDraws; i++){
            player.drawCard(deck.drawFromDeck());
        }
    }

    public void selectColor(){
        while(true){
            System.out.println("Choose a color:");
            for(int i = 0; i < COLORS.length; i++){
                System.out.println(i + "- " + COLORS[i]);
            }
            int colorOption = scanner.nextInt();

            if(colorOption < 0 || colorOption >= COLORS.length){
                System.out.println("Invalid Option");
                continue;
            }

            discardPile.getLastThrownCard().setColor(COLORS[colorOption]);
            break;
        }
    }

    public Player getNextPlayer(){
        int nextIndex;
        if(isReverseFlag()){
            if(getCurrentPlayerIndex() == 0){
                nextIndex = getPlayers().size() - 1;
            }
            else{
                nextIndex = getCurrentPlayerIndex() - 1;
            }
        }
        else{
            nextIndex = (getCurrentPlayerIndex() + 1)%getPlayers().size();
        }
        return getPlayers().get(nextIndex);
    }

    public void handleSpecialCard(Card card){
        switch(card.getCardValue()){
            case -1:
                updateNextPlayerIndex();
                break;
            case -2:
                setReverseFlag(!isReverseFlag());
                break;
            case -3:
                drawCards(getNextPlayer(), 2);
                break;
            case -4, -5:
                selectColor();
                if(card.getCardValue() == -5){
                    drawCards(getNextPlayer(), 4);
                }
                break;
        }
    }

    public void play(){
        boolean drawFlag;
        int cardSelection;
        Card chosenCard;
        while(!isGameOver()){
            Player currentPlayer = players.get(getCurrentPlayerIndex());
            System.out.println("\n<========= Turn: " + currentPlayer.getPlayerName() + " =========>\n");
            System.out.print("Discard Pile: ");
            discardPile.getLastThrownCard().getCardDetails();

            System.out.println(getPlayers().get(getCurrentPlayerIndex()).getPlayerName());
            getPlayers().get(getCurrentPlayerIndex()).printPlayerHand();

            drawFlag = false;
            cardSelection = 0;
            chosenCard = null;
            while(chosenCard == null){
                if(!drawFlag){
                    System.out.println("Choose a card index to play (-1 to draw): ");
                }
                else{
                    System.out.println("Choose a card index to play (-2 to skip): ");
                }
                cardSelection = scanner.nextInt();

                if(cardSelection == -2) break;
                if(cardSelection == -1){
                    drawCards(getCurrentPlayer(), 1);
                    continue;
                }

                chosenCard = getCurrentPlayer().chooseCard(cardSelection);
                if(chosenCard == null || !isValidPlay(chosenCard)){
                    System.out.println("Invalid card selection. Try again.");
                    chosenCard = null;
                }
            }

            if(drawFlag){
                getPlayers().get(getCurrentPlayerIndex()).printPlayerHand();
            }

            if(chosenCard != null){
                discardPile.addToDiscardPile(getCurrentPlayer().playCard(cardSelection));
                handleSpecialCard(discardPile.getLastThrownCard());
            }

            if(getCurrentPlayer().isHandEmpty()){
                System.out.println(getCurrentPlayer().getPlayerName() + " has won!");
                break;
            }

            updateNextPlayerIndex();
        }
        scanner.close();
    }
}