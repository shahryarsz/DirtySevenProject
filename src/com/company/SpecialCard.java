package com.company;

public abstract class SpecialCard extends Card{

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

    public abstract void act(Game game);
}
