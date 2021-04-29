package com.company;

import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<Card> playerCards;
    protected String name;

    public Player(String name) {
        this.name = name;
        playerCards = new ArrayList<>();
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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

    public void removeCard(Card card){
        playerCards.remove(card);
    }

    public void showCards(){
//        if (this instanceof Human) {
            int i = 1;
            for (Card card : playerCards) {
                System.out.println(i + ":" + colorString(card.color, card.value));
                i++;
            }
//        }else {
//            System.out.println(name +" has "+ playerCards.size()+" cards");
//        }
    }

    public boolean cantPlay(Card mainCard){
        for (Card card : playerCards){
            if (card.value.equals(mainCard.value) || card.color.equals(mainCard.color))
                return false;
        }
        return true;
    }

    public void grabStorage(Storage storage){
        //adding to the cards
        playerCards.add(storage.storeCards.get(0));
        //removing from storage
        storage.storeCards.remove(0);
    }






//    public void showCards(){
//        int i = 1;
//        String[] carts;
//        for (Card card : playerCards){
//            if (i==playerCards.size()){
//                carts = new String[]{ colorString(card.color, "        ┍━━━━━━━━━┑"+
//                        "        │ "+card.value+"       │\n"+
//                        "        │         │\n"+
//                        "        │         │\n"+
//                        "        │       "+card.value+" │\n"+
//                        "        ┕━━━━━━━━━┙")
//                };
//            }else {
//                carts = new String[]{ colorString(card.color, "        ┍━━━━━━━\n" +
//                        "        │ " + card.value + "     \n" +
//                        "        │       \n" +
//                        "        │       \n" +
//                        "        │       \n" +
//                        "        ┕━━━━━━━")
//                };
//            }
//            i++;
//        }
//    }
//    public void show(){
//        String[] cartsView = new String[playerCards.size()];
//        for (int i = 0 ; i<playerCards.size() ;i++){
//            if (i==playerCards.size()){
//                cartsView[i] =  colorString(playerCards.get(i).color, "        ┍━━━━━━━━━┑"+
//                        "        │ "+playerCards.get(i).value+"       │\n"+
//                        "        │         │\n"+
//                        "        │         │\n"+
//                        "        │       "+playerCards.get(i).value+" │\n"+
//                        "        ┕━━━━━━━━━┙\n");
//            }else {
//                cartsView[i] =  colorString(playerCards.get(i).color, "        ┍━━━━━━━\n" +
//                        "        │ " + playerCards.get(i).value + "     \n" +
//                        "        │       \n" +
//                        "        │       \n" +
//                        "        │       \n" +
//                        "        ┕━━━━━━━\n");
//            }
//        }
//        System.out.print(cartsView[0]+cartsView[1]);
//    }

}
