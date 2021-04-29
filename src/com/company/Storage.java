package com.company;

import java.util.ArrayList;

public class Storage {

    protected ArrayList<Card> storeCards;

    public Storage(){
        storeCards = new ArrayList<>();
    }

    public void addStorage(Card card){
        storeCards.add(card);
    }
}
