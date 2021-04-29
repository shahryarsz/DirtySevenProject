package com.company;

public class NormalCard extends Card {
    public NormalCard(String value , String color){
        super(value , color);
        if (value.equals("C"))
            super.prize=12;
        else if (value.equals("D"))
            super.prize=13;
        else
            super.prize = Integer.parseInt(value);
    }

}
