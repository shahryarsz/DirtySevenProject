package com.company;

public class PrizeCard extends SpecialCard{
    public PrizeCard(String value, String color) {
        super(value, color);
    }

    @Override
    public void act(Game game) {
        game.setHasPrize(true);
    }
}
