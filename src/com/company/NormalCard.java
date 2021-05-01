package com.company;

/**
 * class for normal cards
 * cards with no special action
 * @author shahryarsz
 * @version 1.1
 */
public class NormalCard extends Card {
    /**
     * constructor for normal cards
     * @param value value of a normal card
     * @param color color of normal card
     */
    public NormalCard(String value , String color){
        super(value , color);
        if (value.equals("C"))
            super.prize=12;
        else if (value.equals("D"))
            super.prize=13;
        else
            super.prize = Integer.parseInt(value);
    }

    /**
     * overriding act method for normal cards in a game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        return;
    }
}
