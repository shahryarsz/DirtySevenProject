package com.company;

import java.util.Random;
import java.util.Scanner;

public class BossCard extends SpecialCard{

    public BossCard(String value, String color) {
        super(value, color);
    }

    @Override
    public void act(Game game) {
        while (true) {
            for (Player player : game.getPlayers()){
                if (player.isPlaying){
                    if (player instanceof Human){
                        Scanner scanner = new Scanner(System.in);
                        System.out.print(colorString("cyan" , "\nchoose next main card color:"));
                        String color = scanner.nextLine();
                        if (color.equals("red") || color.equals("blue") || color.equals("black") || color.equals("green")){
                            game.setMainColor(color);
                            return;
                        } else {
                            System.out.println(colorString("red" , "\nyou should choose between : green , blue , black and red!\n"));
                        }
                    }else {
                        Random random = new Random();
                        String color1="";
                        int x = random.nextInt(4);
                        switch (x){
                            case 0:
                                color1 = "black";
                                break;
                            case 1:
                                color1 = "red";
                                break;
                            case 2:
                                color1 = "green";
                                break;
                            case 3:
                                color1 = "blue";
                                break;
                        }
                        System.out.println(colorString("purple" , "\nyou should choose between : green , blue , black and red!\n"));
                        game.setMainColor(color1);
                        return;
                    }
                }
            }

        }
    }


}
