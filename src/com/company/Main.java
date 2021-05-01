package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * main class of dirty seven project
 * @author shahryarsz
 * @version 1.0
 */
public class Main {
    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nWelcome to yegain's dirty seven game:)\n\n");
            System.out.println("Select game mode:\n1.Multiplayer\n2.Play with Bots\n3.Exit");
            int modeChoice = scanner.nextInt();
            if (modeChoice<=0 || modeChoice>3){
                System.out.println("\nWrong input\n");
                continue;
            }
            scanner.nextLine();
            if (modeChoice==2){
                System.out.print("How many players?");
                int choice = scanner.nextInt();
                if (choice > 5 || choice < 3) {
                    System.out.println("\nThere should be 3 , 4 or 5 players!\n");
                    continue;
                }
                scanner.nextLine();
                System.out.print("\nYour name:");
                String playerName = scanner.nextLine();
                Player player = new Human(playerName);
                players.add(player);
                switch (choice){
                    case 3:
                        for (int i=1;i<=2;i++){
                            Player bot = new Bot("Bot"+i);
                            players.add(bot);
                        }
                        break;
                    case 4:
                        for (int i=1;i<=3;i++){
                            Player bot = new Bot("Bot"+i);
                            players.add(bot);
                        }
                        break;
                    case 5:
                        for (int i=1;i<=4;i++){
                            Player bot = new Bot("Bot"+i);
                            players.add(bot);
                        }
                        break;
                }
            }else if (modeChoice==1){
                System.out.print("How many players?");
                int choice = scanner.nextInt();
                if (choice > 5 || choice < 3) {
                    System.out.println("\nThere should be 3 , 4 or 5 players!\n");
                    continue;
                }
                scanner.nextLine();
                switch (choice){
                    case 3:
                        for (int i=1;i<=3;i++){
                            System.out.print("Player "+i+" name:");
                            String name = scanner.nextLine();
                            Player player = new Human(name);
                            players.add(player);
                            System.out.println();
                        }
                        break;
                    case 4:
                        for (int i=1;i<=4;i++){
                            System.out.print("Player "+i+" name:");
                            String name = scanner.nextLine();
                            Player player = new Human(name);
                            players.add(player);
                            System.out.println();
                        }
                        break;
                    case 5:
                        for (int i=1;i<=5;i++){
                            System.out.print("Player "+i+" name:");
                            String name = scanner.nextLine();
                            Player player = new Human(name);
                            players.add(player);
                            System.out.println();
                        }
                        break;
                }
            }else {
                return;
            }
            Game game = new Game(players);
            game.gameLoop();
            game.scoreBoard();
        }

    }

}
