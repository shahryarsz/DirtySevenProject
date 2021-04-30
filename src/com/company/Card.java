package com.company;

public abstract class Card {
    protected String value;
    protected String color;
    protected int prize;

    public Card(String value , String color){
        this.value = value;
        this.color = color;
    }

    public abstract void act(Game game);



}
