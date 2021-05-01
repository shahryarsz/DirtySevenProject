package com.company;

import java.util.ArrayList;

/**
 * class for holding storage
 * @author shahryarsz
 * @version 1.1
 */
public class Storage {
    /**
     * storage card's list
     */
    protected ArrayList<Card> storeCards;

    /**
     * constructor for creating a storage
     */
    public Storage(){
        storeCards = new ArrayList<>();
    }

    /**
     * adding a new card to the storage
     * @param card the new card
     */
    public void addStorage(Card card){
        storeCards.add(card);
    }
}
