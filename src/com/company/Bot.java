package com.company;

import java.util.Scanner;

/**
 * Bot class for bot players
 * @author shahryarsz
 * @version 1.0
 */
public class Bot extends Player{
    /**
     * constructor for setting the name
     * @param name the name
     */
    public Bot(String name){
        super(name);
    }

    /**
     * method for selecting a card for bot
     * @param mainCard main card of the game
     * @param mainColor main color of the game
     * @return bot choice
     */
    public Card botChoose(Card mainCard , String mainColor){
        for (Card card : playerCards){
            if (card.value.equals(mainCard.value) || card.color.equals(mainColor)){
                return card;
            }else if (card instanceof BossCard){
                    return card;
                }
            }
        return null;
    }

    /**
     * overriding act method for bots in a game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        Scanner scanner = new Scanner(System.in);
        for (Player player : game.getPlayers()){
            if (player.isPlaying && player.canPlay){
                while (true){
                    if (player instanceof Bot) {
                        System.out.println(colorString("yellow" , "\n\n" + player.name + "'s turn:\n\n"));
                        game.showMainCard(game.getMainCard());
                        player.showCards();
                        System.out.print(colorString("white" , "\nPress enter to see bot choice!"));
                        scanner.nextLine();
                        Card botCard = ((Bot) player).botChoose(game.getMainCard(), game.mainColor);
                        if (botCard == null) {
                            System.out.print(colorString("purple" , "\nBot should grab a card from storage!Press enter to continue!"));
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
                            if (game.getMainCard().value.equals(game.lastCard.value) && game.getMainCard().color.equals(game.lastCard.color)){
                                game.updateStorage(game.getStorage());
                            }
                            game.setMainColor(game.getMainCard().color);
                            game.getMainCard().act(game);                                      //CART ACTION EXPECTED
                            player.removeCard(game.getMainCard());
                            if (player.playerCards.isEmpty()) {
                                System.out.println(colorString("yellow" , "\n" + player.name + " wins!!"));
                                game.setWinner(false);
                                return;
                            }
                            game.showMainCard(game.getMainCard());
                            if (game.isHasPrize()) {
                                System.out.println(colorString("cyan" , "\n" + player.name + " has another shot.\n"));
                                game.setHasPrize(false);
                                continue;
                            }
                            player.isPlaying = false;
                            System.out.print(colorString("white" , "\n" + player.name + " has another shot.\n"));
                            scanner.nextLine();
                            break;
                        }
                    }
                }
            }
        }
    }
}
