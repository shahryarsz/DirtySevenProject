package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game {

    private ArrayList<Player> players;
    private ArrayList<Card> cards;

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
    }





}