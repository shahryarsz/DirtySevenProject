package com.company;

public class Human extends Player {

    public Human(String name) {
        super(name);
    }

    public Card chooseCard(int index) {
        int i = 1;
        for (Card card : this.playerCards) {
            if (i == index) {
                this.playerCards.remove(index);
                return card;
            }
            i++;
        }
        return null;
    }
    public void showCards(Human human){
        //black box
    }
}
