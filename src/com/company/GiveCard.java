package com.company;

import java.util.Random;
import java.util.Scanner;

public class GiveCard extends SpecialCard{

    public GiveCard(String value, String color) {
        super(value, color);
    }

    @Override
    public void act(Game game) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (Player player : game.getPlayers()) {
                if (player.isPlaying) {
                    if (player instanceof Human) {
                        System.out.print(colorString("cyan" , "\nchoose a card :"));
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice <= 0 || choice > player.playerCards.size()) {
                            System.out.println(colorString("red" , "\nWrong input\n"));
                        } else {
                            Card givingCard = player.getCard(choice);
                            System.out.print(colorString("cyan" , "\nwhich player do you want to give your card?(type the name)"));
                            String name = scanner.nextLine();
                            for (Player p : game.getPlayers()) {
                                if (p.name.equals(name)) {
                                    p.playerCards.add(givingCard);
                                    player.removeCard(givingCard);
                                    return;
                                }
                            }
                            System.out.println(colorString("cyan" , "\nPlayer not found. try again!\n"));
                        }
                    }else if (player instanceof Bot){
                        Random random = new Random();
                        int botChoiceCard = random.nextInt(player.playerCards.size())+1;
                        Card botGivingCard = player.getCard(botChoiceCard);
                        int maxSize=52;
                        for (Player player1 : game.getPlayers()){
                            if (!(player1.isPlaying)){
                                if (player1.playerCards.size()<maxSize){
                                    maxSize = player1.playerCards.size();
                                }
                            }
                        }
                        for (Player player1 : game.getPlayers()){
                            if (player1.playerCards.size()==maxSize){
                                player1.playerCards.add(botGivingCard);
                                player.removeCard(botGivingCard);
                                System.out.println(colorString("purple" , "\n"+player.name+" choose "+player1.name+" to give a card.\n"));
                                return;
                            }
                        }
                    }
                }
            }
            break;
        }
    }


}
