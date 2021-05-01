package com.company;

/**
 * card class for creating a card
 * @author shahryarsz
 * @version 1.1
 */
public abstract class Card {
    /**
     * card fields
     */
    protected String value;
    protected String color;
    protected int prize;

    /**
     * constructor for creating a card with value and number
     * @param value the value
     * @param color the number
     */
    public Card(String value , String color){
        this.value = value;
        this.color = color;
    }

    /**
     * an abstract method for act of the cards in a game
     * @param game the game
     */
    public abstract void act(Game game);

    /**
     * color codes
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * color a text
     * @param color the color
     * @param text the text
     * @return color text
     */
    public String colorString(String color , String text){
        if (color.equals("black"))
            return ANSI_BLACK + text + ANSI_RESET;
        else if (color.equals("blue"))
            return ANSI_BLUE + text + ANSI_RESET;
        else if (color.equals("red"))
            return ANSI_RED + text + ANSI_RESET;
        else if (color.equals("green"))
            return ANSI_GREEN + text + ANSI_RESET;
        else if (color.equals("yellow"))
            return ANSI_YELLOW + text + ANSI_RESET;
        else if (color.equals("cyan"))
            return ANSI_CYAN + text + ANSI_RESET;
        else if (color.equals("purple"))
            return ANSI_PURPLE + text + ANSI_RESET;
        else
            return ANSI_WHITE + text + ANSI_RESET;
    }

}
