package com.company;

public class Bot extends Player{

    public Bot(String name){
        super(name);
    }

    public Card botChoose(Card mainCard){
        for (Card card : playerCards){
            if (card.value.equals(mainCard.value) || card.color.equals(mainCard.color)){
                return card;
            }
        }
        return null;
    }



}
