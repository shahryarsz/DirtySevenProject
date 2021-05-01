package com.company;

import java.util.ArrayList;

/**
 * class for creating a player
 * @author shahryarsz
 * @version 1.1
 */
public abstract class Player {
    /**
     * player fields
     */
    protected ArrayList<Card> playerCards;
    protected String name;
    protected boolean canPlay;
    protected boolean isPlaying;
    protected boolean hasPunish;

    /**
     * constructor for player
     * @param name name of the player
     */
    public Player(String name) {
        this.name = name;
        playerCards = new ArrayList<>();
        canPlay=true;
        isPlaying=false;
        hasPunish=false;
    }

    /**
     * overriding act method for players actions in a game
     * @param game the game
     */
    public abstract void act(Game game);

    /**
     * color codes
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * color a text
     * @param color the color
     * @param text the text
     * @return color text
     */
    public String colorString(String color , String text){
        if (color.equals("black"))
            return ANSI_BLACK + text + ANSI_RESET;
        else if (color.equals("blue"))
            return ANSI_BLUE + text + ANSI_RESET;
        else if (color.equals("red"))
            return ANSI_RED + text + ANSI_RESET;
        else if (color.equals("green"))
            return ANSI_GREEN + text + ANSI_RESET;
        else if (color.equals("yellow"))
            return ANSI_YELLOW + text + ANSI_RESET;
        else if (color.equals("cyan"))
            return ANSI_CYAN + text + ANSI_RESET;
        else if (color.equals("purple"))
            return ANSI_PURPLE + text + ANSI_RESET;
        else
            return ANSI_WHITE + text + ANSI_RESET;
    }

    /**
     * getting a card by its index
     * @param index the index
     * @return the card
     */
    public Card getCard(int index){
        int i = 0;
        for (Card card : playerCards){
            if (i==index-1)
                return card;
            i++;
        }
        return null;
    }

    /**
     * removing a card from player cards lists
     * @param card the card
     */
    public void removeCard(Card card){
        playerCards.remove(card);
    }

    /**
     * choosing a card from player cards list
     * @param index cards index
     * @return card
     */
    public Card chooseCard(int index) {
        int i = 0;
        for (Card card : playerCards) {
            if (i == index-1) {
                return card;
            }
            i++;
        }
        return null;
    }

    /**
     * check if we can play or not
     * @param mainCard game's main card
     * @param mainColor game's main color
     * @return if we can play : false else : true
     */
    public boolean cantPlay(Card mainCard , String mainColor){
        if (!hasPunish) {
            boolean checkBoss = false;
            for (Card card : this.playerCards) {
                if (card instanceof BossCard) {
                    checkBoss = true;
                }
            }
            for (Card card : playerCards) {
                if (card.value.equals(mainCard.value) || card.color.equals(mainColor) || checkBoss)
                    return false;
            }
        }else {
            for (Card card : this.playerCards){
                if (card instanceof SevenPunishCard){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * grabbing a card from storage
     * @param storage the storage
     */
    public void grabStorage(Storage storage){
        //adding to the cards
        playerCards.add(storage.storeCards.get(0));
        //removing from storage
        storage.storeCards.remove(0);
    }

    /**
     * showing player score
     * @return player score
     */
    public int playerScore(){
        int score=0;
        for (Card card : playerCards){
            score += card.prize;
        }
        return score;
    }

    /**
     * showing cards of a player
     */
    public void showCards(){
        if (this instanceof Bot){
            System.out.println(colorString("yellow" , this.name+" has "+this.playerCards.size()+" cards"));
            return;
        }
        int index=0;
        for (Card card : playerCards){
            if (index==playerCards.size()-1){
                System.out.print(colorString(card.color , "┍━━━━━┑"));
            }else {
                System.out.print(colorString(card.color , "┍━━━━━"));
            }
            index++;
        }
        System.out.println();
        index=0;
        for (Card card : playerCards){
            if (index==playerCards.size()-1){
                System.out.print(colorString(card.color ,"|     |" ));
            }else {
                System.out.print(colorString(card.color, "|     "));
            }
            index++;
        }
        System.out.println();
        index=0;
        for (Card card : playerCards){
            if (index==playerCards.size()-1){
                System.out.print(colorString(card.color , card.value.length()==2?"| "+card.value +"  |":"| "+card.value+"   |"));
            }else {
                System.out.print(colorString(card.color , card.value.length()==2?"| "+card.value +"  ":"| "+card.value+"   "));
            }
            index++;
        }
        System.out.println();
        index=0;
        for (Card card : playerCards){
            if (index==playerCards.size()-1){
                System.out.print(colorString(card.color ,"|     |" ));
            }else {
                System.out.print(colorString(card.color, "|     "));
            }
            index++;
        }
        System.out.println();
        index=0;
        for (Card card : playerCards){
            if (index==playerCards.size()-1){
                System.out.print(colorString(card.color , "┕━━━━━┙"));
            }else {
                System.out.print(colorString(card.color , "┕━━━━━"));
            }
            index++;
        }
        System.out.println();
        index=1;
        for (int i=1;i<=playerCards.size();i++){
            System.out.print(colorString("yellow" , "   "+i+"  "));
        }
    }
}
