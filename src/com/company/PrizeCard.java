package com.company;

/**
 * class for prize card ( number 8 )
 * if you playe a prize card you can play one more time
 * @author shahryarsz
 * @version 1.1
 */
public class PrizeCard extends SpecialCard{
    /**
     * constructor for prize card
     * @param value value of prize card
     * @param color color of prize card
     */
    public PrizeCard(String value, String color) {
        super(value, color);
    }

    /**
     * overriding act method for prize card action in a game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        game.setHasPrize(true);
    }
}
