package com.company;

public class SevenPunishCard extends SpecialCard{

    public SevenPunishCard(String value, String color) {
        super(value, color);
    }

    @Override
    public void act(Game game) {
        int index = 0;
        for (Player player : game.getPlayers()) {
            if (player.isPlaying) {
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
                if (this.color.equals("black"))
                    game.punish=4;

                System.out.println(colorString("red" , "\n\n" + game.getPlayers().get(index).name + " should grab" + game.punish + " cards from storage.\n"));
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
