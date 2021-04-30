package com.company;

import java.util.Scanner;

public class Bot extends Player{

    public Bot(String name){
        super(name);
    }

    public Card botChoose(Card mainCard , String mainColor){
        boolean checkBoss=false;
        for (Card card : this.playerCards){
            if (card instanceof BossCard){
                checkBoss=true;
            }
        }
        for (Card card : playerCards){
            if (card.value.equals(mainCard.value) || card.color.equals(mainColor)){
                return card;
            }else if (card instanceof BossCard){
                    return card;
                }
            }
        return null;
    }

    @Override
    public void act(Game game) {
        Scanner scanner = new Scanner(System.in);
        for (Player player : game.getPlayers()){
            if (player.isPlaying && player.canPlay){
                while (true){
                    if (player instanceof Bot) {
                        System.out.println("\n\n" + player.name + "'s turn:\n\n");
                        game.showMainCard(game.getMainCard());
                        player.showCards();
                        System.out.print("\nPress enter to see bot choice!");
                        scanner.nextLine();
                        Card botCard = ((Bot) player).botChoose(game.getMainCard(), game.mainColor);
                        if (botCard == null) {
                            System.out.print("\nBot should grab a card from storage!Press enter to continue!");
                            scanner.nextLine();
                            player.grabStorage(game.getStorage());
                            if (!(player.cantPlay(game.getMainCard() , game.mainColor))) {                             //can play
                                continue;
                            } else {
                                player.showCards();
                                break;                                                                      //cant play
                            }
                        } else {
                            game.getStorage().addStorage(game.getMainCard());                                   //PLAYING
                            game.setMainCard(botCard);
                            game.setMainColor(game.getMainCard().color);
                            game.getMainCard().act(game);                                      //CART ACTION EXPECTED
                            player.removeCard(game.getMainCard());
                            if (player.playerCards.isEmpty()) {
                                System.out.println("\n" + player.name + " wins!!");
                                game.setWinner(false);
                                return;
                            }
                            game.showMainCard(game.getMainCard());
                            if (game.isHasPrize()) {
                                System.out.println("\n" + player.name + " has another shot.\n");
                                game.setHasPrize(false);
                                continue;
                            }
                            player.isPlaying = false;
                            System.out.print("\nPress enter to continue");
                            scanner.nextLine();
                            break;
                        }
                    }
                }
            }
        }
    }


}
