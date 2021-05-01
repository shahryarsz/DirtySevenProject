package com.company;

/**
 * reverse card( number 10 )
 * when you play reverse card the game directions change in the opposite way
 * @author shahryarsz
 * @version 1.0
 */
public class ReverseCard extends SpecialCard{
    /**
     * constructor for reverse cards
     * @param value value of reverse card
     * @param color color of reverse card
     */
    public ReverseCard(String value, String color) {
        super(value, color);
    }

    /**
     * overriding act methode for reverse cards action in a game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        int index=0;
        for (Player player : game.getPlayers()){
            if (player.isPlaying){
                if (game.isClockwise()){
                    game.setTurn(index-1);
                }else {
                    game.setTurn(index+1);
                }
                player.isPlaying = false;
                game.setClockwise(!game.isClockwise());
                return;
            }
            index++;
        }
    }
}
