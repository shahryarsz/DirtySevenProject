package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * game class for creating a game
 * @author shahryarsz
 * @version 1.1
 */
public class Game {
    /**
     * fields of the game
     */
    private ArrayList<Player> players;
    private ArrayList<Card> cards;
    private Storage storage;
    private boolean clockwise;
    private boolean winner;
    private boolean hasPrize;
    private Card mainCard;
    protected int turn;
    protected String mainColor;
    protected int punish;
    protected Card lastCard;

    /**
     * constructor for creating a game with players
     * @param players1 players array list
     */
    public Game(ArrayList<Player> players1){
        punish=2;
        turn=0;
        hasPrize=false;
        winner = true;
        clockwise = true;
        //adding players
        players = new ArrayList<>();
        players.addAll(players1);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("black");
        colors.add("blue");
        colors.add("green");
        colors.add("red");
        cards = new ArrayList<>();
        //adding normal cards
        for (String color : colors){
            NormalCard normalCard1 = new NormalCard("3" , color);
            NormalCard normalCard2 = new NormalCard("4" , color);
            NormalCard normalCard3 = new NormalCard("5" , color);
            NormalCard normalCard4 = new NormalCard("6" , color);
            NormalCard normalCard5 = new NormalCard("9" , color);
            NormalCard normalCard6 = new NormalCard("C" , color);
            NormalCard normalCard7 = new NormalCard("D" , color);
            cards.add(normalCard1);
            cards.add(normalCard2);
            cards.add(normalCard3);
            cards.add(normalCard4);
            cards.add(normalCard5);
            cards.add(normalCard6);
            cards.add(normalCard7);
            //adding special cards
            SpecialCard specialCard1 = new SevenPunishCard("7" , color);
            SpecialCard specialCard2 = new GiveCard("2" , color);
            SpecialCard specialCard3 = new BossCard("B" , color);
            SpecialCard specialCard4 = new SkipCard("A" , color);
            SpecialCard specialCard5 = new ReverseCard("10" , color);
            SpecialCard specialCard6 = new PrizeCard("8" , color);
            cards.add(specialCard1);
            cards.add(specialCard2);
            cards.add(specialCard3);
            cards.add(specialCard4);
            cards.add(specialCard5);
            cards.add(specialCard6);
        }
        //giving cards to the players
        Random random = new Random();
        for (Player player : players){
            for (int i=0;i<7;i++){
                int x = random.nextInt(cards.size());
                player.playerCards.add(cards.get(x));
                cards.remove(x);
            }
        }
        //adding other cards to storage
        storage = new Storage();
        int size = cards.size();
        for (int i = 0 ; i< size ; i++){
            int index = random.nextInt(cards.size());
            storage.storeCards.add(cards.get(index));
            cards.remove(index);
        }
        //saving last card
        lastCard = storage.storeCards.get(storage.storeCards.size()-1);
        //choosing first main card
        if (storage.storeCards.get(0) instanceof SpecialCard){
            storage.storeCards.add(storage.storeCards.get(0));
            storage.storeCards.remove(0);
        }
        mainCard = storage.storeCards.get(0);
        mainColor = mainCard.color;
        storage.storeCards.remove(0);
    }

    /**
     * updating storage cards
     * @param storage the storage
     */
    public void updateStorage(Storage storage){
        Random random = new Random();
        ArrayList<Card> tmp = new ArrayList<>();
        int size = storage.storeCards.size();
        for (int i=0 ; i<size ; i++){
            int index = random.nextInt(storage.storeCards.size());
            tmp.add(storage.storeCards.get(index));
            storage.storeCards.remove(index);
        }
        storage.storeCards.addAll(tmp);
    }

    /**
     * getting the storage
     * @return storage
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * letting previous player to play
     * @param player current player
     */
    public void setCanPlay(Player player){
        int index=0;
        for (Player p : players){
            if (p.name.equals(player.name)){
                if (clockwise){
                    if (index==0){
                        players.get(players.size()-1).canPlay=true;
                    }else {
                        players.get(index-1).canPlay = true;
                    }
                }else {
                    if (index==players.size()-1){
                        players.get(0).canPlay = true;
                    }else {
                        players.get(index+1).canPlay = true;
                    }
                }
                return;
            }
            index++;
        }
    }

    /**
     * setting turn
     * @param turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * getting hasPrize
     * @return if has prize:true else: false
     */
    public boolean isHasPrize() {
        return this.hasPrize;
    }

    /**
     * setting direction
     * @param b
     */
    public void setClockwise(boolean b) {
        this.clockwise = b;
    }

    /**
     * getting direction
     * @return the direction
     */
    public boolean isClockwise() {
        return clockwise;
    }

    /**
     * getting main card
     * @return main card
     */
    public Card getMainCard(){
        return this.mainCard;
    }

    /**
     * setting a card as main card
     * @param card the card
     */
    public void setMainCard(Card card) {
        this.mainCard = card;
    }

    /**
     * setting main color
     * @param mainColor the main color
     */
    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    /**
     * colors code
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    /**
     *printing with colors
     * @param color specific color
     * @param text printing text
     */
    public void colorPrint(String color , String text){
        switch (color) {
            case "black" -> System.out.print(ANSI_BLACK + text + ANSI_RESET);
            case "blue" -> System.out.print(ANSI_BLUE + text + ANSI_RESET);
            case "red" -> System.out.print(ANSI_RED + text + ANSI_RESET);
            case "green" -> System.out.print(ANSI_GREEN + text + ANSI_RESET);
            case "cyan" -> System.out.print(ANSI_CYAN + text + ANSI_RESET);
            default -> System.out.println(ANSI_PURPLE + text + ANSI_RESET);
        }
    }

    /**
     * showing score board
     */
    public void scoreBoard(){
        System.out.println("\nScore board:");
        for (Player player : players){
            colorPrint("purple" , player.name + " : " + player.playerScore());
        }
    }

    /**
     * setting the winner
     * @param b
     */
    public void setWinner(boolean b) {
        this.winner = b;
    }

    /**
     * showing the main card
     * @param card the main card
     */
    public void showMainCard(Card card){
        System.out.println("main card:");
        colorPrint(card.color , "?????????????????????");
        System.out.println();
        colorPrint(card.color ,"|     |");
        System.out.println();
        colorPrint(card.color , card.value.length()==2?"| "+card.value +"  |":"| "+card.value+"   |");
        System.out.println();
        colorPrint(card.color ,"|     |" );
        System.out.println();
        colorPrint(card.color , "?????????????????????");
        System.out.println();
        System.out.println("main color is:"+mainColor);
    }

    /**
     * check if we can play or not
     * @param playCard our card
     * @param mainCard main card
     * @return if we can : false else : true
     */
    public boolean checkPlay(Card playCard , Card mainCard){
        if (playCard instanceof BossCard){
            return false;
        }
        if (playCard.value.equals(mainCard.value) || playCard.color.equals(this.mainColor)){
            return false;
        }
        return true;
    }

    /**
     * getting players list
     * @return
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }

    /**
     * set hasprize for players turn
     * @param hasPrize
     */
    public void setHasPrize(boolean hasPrize) {
        this.hasPrize = hasPrize;
    }

    /**
     * showing status
     */
    public void showStatus(){
        int i=1;
        System.out.println();
        for (Player player : players){
            System.out.println(ANSI_CYAN+i+")"+ANSI_RESET+
                    ANSI_PURPLE+player.name+" has "+player.playerCards.size()+" cards."+ANSI_RESET);
            i++;
        }
        System.out.println();
    }

    /**
     * method for generating the game
     */
    public void gameLoop(){
        while (true){
            while (clockwise){
                while (winner) {
                    if (turn == players.size()) { turn = 0; }
                    Player player = players.get(turn);
                    setCanPlay(player);
                    if (player.canPlay){
                        showStatus();
                    }
                    player.isPlaying = true;
                    turn++;
                    player.act(this);
                    if (!clockwise)
                        break;
                    player.isPlaying = false;
                }
                if (!winner)
                    return;
            }
            while (!clockwise) {
                while (winner) {
                    if (turn == -1) { turn = players.size() - 1; }
                    Player player = players.get(turn);
                    setCanPlay(player);
                    if (player.canPlay){
                        showStatus();
                    }
                    player.isPlaying = true;
                    turn--;
                    player.act(this);
                    if (clockwise)
                        break;
                    player.isPlaying = false;
                }
                if (!winner)
                    return;
            }
        }
        }
}










