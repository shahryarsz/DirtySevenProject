package com.company;

public class ReverseCard extends SpecialCard{
    public ReverseCard(String value, String color) {
        super(value, color);
    }

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
