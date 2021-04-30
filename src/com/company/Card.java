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


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public String colorString(String color , String text){
        if (color.equals("black"))
            return ANSI_BLACK + text + ANSI_RESET;
        else if (color.equals("blue"))
            return ANSI_BLUE + text + ANSI_RESET;
        else if (color.equals("red"))
            return ANSI_RED + text + ANSI_RESET;
        else
            return ANSI_GREEN + text + ANSI_RESET;
    }

}
