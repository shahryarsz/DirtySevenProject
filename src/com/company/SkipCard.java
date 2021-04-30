package com.company;

public class SkipCard extends SpecialCard {
    public SkipCard(String value, String color) {
        super(value, color);
    }

    @Override
    public void act(Game game) {
        int index=0;
        for (Player player : game.getPlayers()){
            if (player.isPlaying){
                if (index==game.getPlayers().size()-1) {
                    index = 0;
                }else {
                    index++;
                }
                game.getPlayers().get(index).canPlay=false;
                return;
            }
            index++;
        }
    }
}