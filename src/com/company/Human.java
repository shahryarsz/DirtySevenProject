package com.company;

import java.util.Scanner;

/**
 * class for human players
 * @author shahryarsz
 * @version 1.0
 */
public class Human extends Player {
    /**
     * constructor for creating a human
     * @param name human's name
     */
    public Human(String name) {
        super(name);
    }

    /**
     * overriding act method for human's action in a game
     * @param game the game
     */
    @Override
    public void act(Game game) {
        Scanner scanner = new Scanner(System.in);
        for (Player player : game.getPlayers()){
            if (player.isPlaying && player.canPlay){
                while (true){
                    System.out.println(colorString( "yellow", "\n"+player.name+" 's turn\n"));
                    game.showMainCard(game.getMainCard());
                    System.out.println(colorString("cyan" , "\nYour cards:\n"));
                    player.showCards();
                    if ((player.cantPlay(game.getMainCard() , game.mainColor))) {
                        System.out.print(colorString("purple" , "\nPress enter to grab a card from storage."));
                        scanner.nextLine();
                        player.grabStorage(game.getStorage());
                        if (!(player.cantPlay(game.getMainCard() , game.mainColor))) {                             //player can play
                            continue;
                        } else {                                                         //can't play anymore
                            System.out.println(colorString("cyan","\nnow your cards are:\n"));
                            player.showCards();
                            System.out.print(colorString("white" , "\nPress enter to continue"));
                            scanner.nextLine();
                            break;
                        }
                    }else {
                        System.out.print(colorString("cyan" , "\nPlease choose a card:\n"));
                        int choice = scanner.nextInt();
                        if (choice<=0 || choice>playerCards.size()){
                            System.out.println(colorString("red" , "\nWrong input\n"));
                            continue;
                        }
                        scanner.nextLine();
                        if (player.hasPunish && !(player.playerCards.get(choice-1).value.equals("7"))){
                            System.out.println(colorString("red" , "\nYou should pick a 7 card!\n"));
                            continue;
                        }
                        if (game.checkPlay(player.chooseCard(choice), game.getMainCard())) {
                            System.out.println(colorString("red" , "\nWrong input\n"));
                            continue;
                        }else {
                            game.getStorage().addStorage(game.getMainCard());
                            game.setMainCard(player.chooseCard(choice));
                            if (game.getMainCard().value.equals(game.lastCard.value) && game.getMainCard().color.equals(game.lastCard.color)){
                                game.updateStorage(game.getStorage());
                            }
                            game.setMainColor(game.getMainCard().color);
                            game.getMainCard().act(game);                                   //CART ACTION EXPECTED
                            player.removeCard(game.getMainCard());
                            if (player.playerCards.isEmpty()) {
                                System.out.println(colorString("yellow" , "\n" + player.name + " wins!!\n"));
                                game.setWinner(false);
                                return;
                            }
                            System.out.println(colorString("cyan" , "\nnow your cards are:\n"));
                            player.showCards();
                            if (game.isHasPrize()) {
                                System.out.println(colorString("yellow" , "\n\nYou can choose another card!\n"));
                                game.setHasPrize(false);
                                continue;
                            }
                            System.out.print(colorString("white" , "\nPress enter to continue"));
                            scanner.nextLine();
                            player.isPlaying = false;
                            break;
                        }
                    }
                }
            }
        }
    }
}
