package com.company;

public class SpecialCard extends Card{

    public SpecialCard(String value , String color){
        super(value , color);
        if (this instanceof TwoPunishCard)
            this.prize=10;
        else if (this instanceof FourPunishCard)
            this.prize=15;
        else if (this instanceof GiveCard)
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
}
