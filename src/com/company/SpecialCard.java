package com.company;

/**
 * class for special card
 * the cards with an special actions
 * @author shahryarsz
 * @version 1.1
 */
public abstract class SpecialCard extends Card{
    /**
     * special cards constructor
     * @param value value of special card
     * @param color color of special card
     */
    public SpecialCard(String value , String color){
        super(value , color);
        if (this instanceof SevenPunishCard){
            if (this.color.equals("black"))
                this.prize=15;
            else
                this.prize=10;
        } else if (this instanceof GiveCard)
            this.prize=2;
        else if (this instanceof PrizeCard)
            this.prize=8;
        else if (this instanceof ReverseCard)
            this.prize=10;
        else if (this instanceof SkipCard)
            this.prize=11;
        else
            this.prize=12;
    }

    /**
     * overriding act method for special cards in a game
     * @param game the game
     */
    public abstract void act(Game game);
}
