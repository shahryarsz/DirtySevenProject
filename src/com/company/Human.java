package com.company;

public class Human extends Player {

    public Human(String name) {
        super(name);
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



}
