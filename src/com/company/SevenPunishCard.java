package com.company;

/**
 * dirty seven class
 * cards with number 7 and their punishment
 * @author shahryarsz
 * @version 1.1
 */
public class SevenPunishCard extends SpecialCard{
    /**
     * constructor for seven cards
     * @param value value of them
     * @param color color of them
     */
    public SevenPunishCard(String value, String color) {
        super(value, color);
    }

    /**
     * overriding act method for dirty seven action in a game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        int index = 0;
        for (Player player : game.getPlayers()) {
            if (player.isPlaying) {
                //finding the next guy index
                player.hasPunish=false;
                if (game.isClockwise()){
                    if (index == game.getPlayers().size() - 1) {
                        index = 0;
                    } else {
                        index++;
                    }
                }else {
                    if (index==0){
                        index = game.getPlayers().size()-1;
                    }else {
                        index--;
                    }
                }
                //checking if next guy has 7 or not and update the punish
                for (Card card : game.getPlayers().get(index).playerCards) {
                    if (card.value.equals("7")) {
                        if (card.color.equals("black")) {
                            game.punish += 4;
                        } else {
                            game.punish += 2;
                        }
                        game.getPlayers().get(index).hasPunish = true;
                        return;
                    }
                }

                //next guy doesn't have 7
                if (this.color.equals("black") && game.punish==2)
                    game.punish=4;


                System.out.println(colorString("red" , "\n\n" + game.getPlayers().get(index).name + " should grab " + game.punish + " cards from storage.\n"));
                for (int i=0;i< game.punish;i++){game.getPlayers().get(index).grabStorage(game.getStorage());}
                game.getPlayers().get(index).hasPunish = false;
                if (game.punish > 2) {
                    game.punish = 2;
                }
                return;
            }
            index++;
        }
    }
}
