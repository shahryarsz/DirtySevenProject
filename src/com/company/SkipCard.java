package com.company;

/**
 * skip card ( A ) is for skip from next player
 * @author shahryarsz
 * @version 1.1
 */
public class SkipCard extends SpecialCard {
    /**
     * constructor for skip card
     * @param value value of skip card
     * @param color color of skip card
     */
    public SkipCard(String value, String color) {
        super(value, color);
    }

    /**
     * overriding act method for skip card in a game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        int index=0;
        for (Player player : game.getPlayers()){
            if (player.isPlaying){
                if (game.isClockwise()){
                    if (index==game.getPlayers().size()-1) {
                        index = 0;
                    }else {
                        index++;
                    }
                    game.getPlayers().get(index).canPlay=false;
                    return;
                }else {
                    if (index==0){
                        index=game.getPlayers().size()-1;
                    }else {
                        index--;
                    }
                    game.getPlayers().get(index).canPlay=false;
                    return;
                }

            }
            index++;
        }
    }
}