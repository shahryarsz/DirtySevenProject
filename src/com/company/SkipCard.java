package com.company;

public class SkipCard extends SpecialCard {
    public SkipCard(String value, String color) {
        super(value, color);
    }

    @Override
    public void act(Game game) {
        for (Player player : game.getPlayers()){
            if (player.isPlaying){
                game.getNextPlayer(player).canPlay=false;
            }
        }
    }
}