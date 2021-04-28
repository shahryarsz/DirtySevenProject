package com.company;

import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<Card> playerCards;
    protected String name;

    public Player(String name) {
        this.name = name;
        playerCards = new ArrayList<>();
    }


}
