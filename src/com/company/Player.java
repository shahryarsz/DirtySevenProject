package com.company;

import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<Card> playerCards;
    protected String name;
    protected boolean canPlay;
    protected boolean isPlaying;
    protected boolean hasPunish;

    public Player(String name) {
        this.name = name;
        playerCards = new ArrayList<>();
        canPlay=true;
        isPlaying=false;
        hasPunish=false;
    }

    public abstract void act(Game game);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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

    public Card getCard(int index){
        int i = 0;
        for (Card card : playerCards){
            if (i==index-1)
                return card;
            i++;
        }
        return null;
    }
    public void removeCard(Card card){
        playerCards.remove(card);
    }

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

    public void grabStorage(Storage storage){
        //adding to the cards
        playerCards.add(storage.storeCards.get(0));
        //removing from storage
        storage.storeCards.remove(0);
    }

    public int playerScore(){
        int score=0;
        for (Card card : playerCards){
            score += card.prize;
        }
        return score;
    }

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
