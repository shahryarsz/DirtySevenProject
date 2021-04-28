package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game {

    private ArrayList<Player> players;
    private ArrayList<Card> cards;
    private Storage storage;

    public Game(ArrayList<Player> players1){
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
            if (color.equals("black")){
                SpecialCard specialCard1 = new FourPunishCard("7" , color);
                cards.add(specialCard1);
            }else {
                SpecialCard specialCard1 = new TwoPunishCard("7" , color);
                cards.add(specialCard1);
            }
            SpecialCard specialCard2 = new GiveCard("2" , color);
            SpecialCard specialCard3 = new BossCard("B" , color);
            SpecialCard specialCard4 = new SkipCard("A" , color);
            SpecialCard specialCard5 = new ReverseCard("10" , color);
            SpecialCard specialCard6 = new PrizeCard("8" , color);
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
        //adding other carts to storage
        storage = new Storage();
        storage.storeCards.addAll(cards);
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void colorPrint(String color , String text){
        if (color.equals("black"))
            System.out.println(ANSI_BLACK + text + ANSI_RESET);
        else if (color.equals("blue"))
            System.out.println(ANSI_BLUE + text + ANSI_RESET);
        else if (color.equals("red"))
            System.out.println(ANSI_RED + text + ANSI_RESET);
        else
            System.out.println(ANSI_GREEN + text + ANSI_RESET);
    }

//    public void printPlayerCards(){
//        for (Player player : players){
//            System.out.println(player.name+":\n");
//            for (Card card : player.playerCards){
//                colorPrint(card.color, card.value);
//            }
//        }
//    }
//    public void printStorageCart(){
//        for (Card card : storage.storeCards){
//            colorPrint(card.color, card.value);
//        }
//    }





}