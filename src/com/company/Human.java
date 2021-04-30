package com.company;

import java.util.Scanner;

public class Human extends Player {

    public Human(String name) {
        super(name);
    }


    @Override
    public void act(Game game) {
        Scanner scanner = new Scanner(System.in);
        for (Player player : game.getPlayers()){
            if (player.isPlaying && player.canPlay){
                while (true){
                    System.out.println("\n"+player.name+" 's turn\n");
                    game.showMainCard(game.getMainCard());
                    System.out.println("\nYour cards:\n");
                    player.showCards();
                    if (player.cantPlay(game.getMainCard() , game.mainColor)) {
                        System.out.print("\nPress enter to grab a card from storage.");
                        scanner.nextLine();
                        player.grabStorage(game.getStorage());
                        if (!(player.cantPlay(game.getMainCard() , game.mainColor))) {                             //player can play
                            continue;
                        } else {                                                         //can't play anymore
                            System.out.println("\nnow your cards are:\n");
                            player.showCards();
                            break;
                        }
                    }else {
                        System.out.print("\nplease choose a card:");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        if (game.checkPlay(player.chooseCard(choice), game.getMainCard())) {
                            System.out.println("\nWrong input\n");
                            continue;
                        }else {
                            game.getStorage().addStorage(game.getMainCard());
                            game.setMainCard(player.chooseCard(choice));
                            game.setMainColor(game.getMainCard().color);
                            game.getMainCard().act(game);                                   //CART ACTION EXPECTED
                            player.removeCard(game.getMainCard());
                            if (player.playerCards.isEmpty()) {
                                System.out.println("\n" + player.name + " wins!!");
                                game.setWinner(false);
                                return;
                            }
                            System.out.println("\nnow your cards are:\n");
                            player.showCards();
                            if (game.isHasPrize()) {
                                System.out.println("\nYou can choose another card!\n");
                                game.setHasPrize(false);
                                continue;
                            }
                            System.out.print("\nPress enter to continue");
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
