package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private ArrayList<Card> cards;
    private Storage storage;
    private boolean clockwise;
    private boolean winner;
    private boolean hasPrize;
    private Card mainCard;
    protected int turn;
    protected String mainColor;

    public Game(ArrayList<Player> players1){
        turn=0;
        hasPrize=false;
        winner = true;
        clockwise = true;
        //adding players
        players = new ArrayList<>();
        players.addAll(players1);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("black");
        colors.add("blue");
        colors.add("green");
        colors.add("red");
        cards = new ArrayList<>();
        //adding normal cards
        for (String color : colors){
            NormalCard normalCard1 = new NormalCard("3" , color);
            NormalCard normalCard2 = new NormalCard("4" , color);
            NormalCard normalCard3 = new NormalCard("5" , color);
            NormalCard normalCard4 = new NormalCard("6" , color);
            NormalCard normalCard5 = new NormalCard("9" , color);
            NormalCard normalCard6 = new NormalCard("C" , color);
            NormalCard normalCard7 = new NormalCard("D" , color);
            cards.add(normalCard1);
            cards.add(normalCard2);
            cards.add(normalCard3);
            cards.add(normalCard4);
            cards.add(normalCard5);
            cards.add(normalCard6);
            cards.add(normalCard7);
            //adding special cards
            if (color.equals("black")){
                SpecialCard specialCard1 = new FourPunishCard("7" , color);
                cards.add(specialCard1);
            }else {
                SpecialCard specialCard1 = new TwoPunishCard("7" , color);
                cards.add(specialCard1);
            }
            SpecialCard specialCard2 = new GiveCard("2" , color);
            SpecialCard specialCard3 = new BossCard("B" , color);
            SpecialCard specialCard4 = new SkipCard("A" , color);
            SpecialCard specialCard5 = new ReverseCard("10" , color);
            SpecialCard specialCard6 = new PrizeCard("8" , color);
            cards.add(specialCard2);
            cards.add(specialCard3);
            cards.add(specialCard4);
            cards.add(specialCard5);
            cards.add(specialCard6);
        }
        //giving cards to the players
        Random random = new Random();
        for (Player player : players){
            for (int i=0;i<7;i++){
                int x = random.nextInt(cards.size());
                player.playerCards.add(cards.get(x));
                cards.remove(x);
            }
        }
        //adding other carts to storage
        storage = new Storage();
//        storage.storeCards.addAll(cards);
        int size = cards.size();
        for (int i = 0 ; i< size ; i++){
            int index = random.nextInt(cards.size());
            storage.storeCards.add(cards.get(index));
            cards.remove(index);
        }
        for (Card card : storage.storeCards){
            colorPrint(card.color, card.value);
        }
        //choosing first main cart
        mainCard = storage.storeCards.get(0);
        mainColor = mainCard.color;
        storage.storeCards.remove(0);
    }

//    public boolean isCanPlay() {
//        return canPlay;
//    }

    public Storage getStorage() {
        return this.storage;
    }

    public Player getNextPlayer(Player player){
        int index=0;
        for (Player p : players){
            if (p.equals(player)){
                return players.get(index+1)==null ? players.get(0) : players.get(index+1);
            }
            index++;
        }
        return null;
    }

    public void setCanPlay(Player player){
        int index=0;
        for (Player p : players){
            if (p.equals(player)){
                if (index==0){
                    players.get(players.size()-1).canPlay=true;
                }else {
                    players.get(index - 1).canPlay = true;
                }
            }
            index++;
        }
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isHasPrize() {
        return this.hasPrize;
    }

    public void setClockwise(boolean b) {
        this.clockwise = b;
    }

    public boolean isClockwise() {
        return clockwise;
    }

    public Card getMainCard(){
        return this.mainCard;
    }
    public void setMainCard(Card card) {
        this.mainCard = card;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void colorPrint(String color , String text){
        if (color.equals("black"))
            System.out.print(ANSI_BLACK + text + ANSI_RESET);
        else if (color.equals("blue"))
            System.out.print(ANSI_BLUE + text + ANSI_RESET);
        else if (color.equals("red"))
            System.out.print(ANSI_RED + text + ANSI_RESET);
        else if (color.equals("green"))
            System.out.print(ANSI_GREEN + text + ANSI_RESET);
        else if (color.equals("cyan"))
            System.out.print(ANSI_CYAN + text + ANSI_RESET);
        else
            System.out.println(ANSI_PURPLE + text + ANSI_RESET);
    }

    public void scoreBoard(){
        System.out.println("\nScore board:\n");
        for (Player player : players){
            colorPrint("purple" , player.name + " : " + player.playerScore());
        }
    }

    public void setWinner(boolean b) {
        this.winner = b;
    }
    public boolean isWinner(){
        return winner;
    }
//printing things for test!
//    public void printPlayerCards(){
//        for (Player player : players){
//            System.out.println(player.name+":");
//            for (Card card : player.playerCards){
//                colorPrint(card.color, card.value);
//            }
//            System.out.println();
//        }
//    }
//    public void printStorageCart(){
//        for (Card card : storage.storeCards){
//            colorPrint(card.color, card.value);
//        }
//    }

//    public void showMainCard(Card card){
//        System.out.println("\n\n\n");
//        colorPrint(card.color, "        ┍━━━━━━━━━┑\n"+
//                                    "        │ "+card.value+"       │\n"+
//                                    "        │         │\n"+
//                                    "        │         │\n"+
//                                    "        │       "+card.value+" │\n"+
//                                    "        ┕━━━━━━━━━┙\n");
//    }

    public void showMainCard(Card card){
        System.out.print("main card:");
        colorPrint(card.color, card.value);
        System.out.println();
    }

    public boolean checkPlay(Card playCard , Card mainCard){
        boolean checkBoss=false;
        for (Player player : players){
            if (player.isPlaying){
                for (Card card : player.playerCards){
                    if (card instanceof BossCard){
                        checkBoss=true;
                    }
                }
            }
        }
        if (playCard.value.equals(mainCard.value) || playCard.color.equals(mainColor) || checkBoss)
            return false;
        else
            return true;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void setHasPrize(boolean hasPrize) {
        this.hasPrize = hasPrize;
    }

    public void showStatus(){
        int i=1;
        for (Player player : players){
            System.out.println(ANSI_CYAN+i+")"+ANSI_RESET+
                    ANSI_PURPLE+player.name+" has "+player.playerCards.size()+" cards."+ANSI_RESET);
            i++;
        }
    }


    public void gameLoop(){
        Random random = new Random();

//        Human myPlayer = (Human) players.get(0);
        Scanner scanner = new Scanner(System.in);

        while (winner){
            while (clockwise){
                if (turn==players.size()) {
                    turn = 0;
                }
                showStatus();
                Player player = players.get(turn);
                setCanPlay(player);
                player.isPlaying = true;
                player.act(this);
                turn++;
                player.isPlaying=false;
            }
            while (!clockwise) {
                if (turn==-1) {
                    turn = players.size()-1;
                }
                showStatus();
                Player player =players.get(turn);
                setCanPlay(player);
                player.isPlaying = true;
                player.act(this);
                turn--;
                player.isPlaying = false;
            }
        }



//        while (true) {
//            for (Player player : players) {                                                    //giving turns to each player
//                setCanPlay(player);
//                //my player turn
//                if (player instanceof Human && player.canPlay) {
//                    player.isPlaying = true;
//                    Human myPlayer = (Human) player;
//                    while (winner) {
//                        System.out.println("\n\n" + myPlayer.name + "'s turn:\n\n");
//                        showMainCard(mainCard);
//                        System.out.println("\nyour cards:");
//                        myPlayer.showCards();
//                        if (myPlayer.cantPlay(mainCard)) {
//                            System.out.print("\nPress enter to grab a card from storage.");
//                            scanner.nextLine();
//                            myPlayer.grabStorage(storage);
//                            if (!(myPlayer.cantPlay(mainCard))) {                             //player can play
//                                continue;
//                            } else {                                                         //can't play anymore
//                                System.out.println("\nnow your cards are:\n");
//                                myPlayer.showCards();
//                                break;
//                            }
//                        } else {
//                            System.out.print("\nplease choose a card:");
//                            int choice = scanner.nextInt();
//                            scanner.nextLine();
//                            if (checkPlay(myPlayer.chooseCard(choice), mainCard)) {
//                                System.out.println("\nWrong input\n");
//                            } else {                                                        //PLAYING!!!!!!
//                                storage.addStorage(mainCard);
//                                mainCard = myPlayer.chooseCard(choice);
//                                mainCard.act(this);                                   //CART ACTION EXPECTED
//                                myPlayer.removeCard(mainCard);
//                                if (myPlayer.playerCards.isEmpty()) {
//                                    System.out.println("\n" + myPlayer.name + " wins!!");
//                                    winner = false;
//                                    return;
//                                }
//                                System.out.println("\nnow your cards are:\n");
//                                myPlayer.showCards();
//                                if (hasPrize) {
//                                    System.out.println("\nYou can choose another card!\n");
//                                    hasPrize = false;
//                                    continue;
//                                }
//                                System.out.print("\nPress enter to continue");
//                                scanner.nextLine();
//                                player.isPlaying = false;
//                                break;
//                            }
//                        }
//                    }
//                }
//                //bot turn
//                if (player instanceof Bot && player.canPlay) {
//
//                    while (winner) {
//                        System.out.println("\n\n" + player.name + "'s turn:\n\n");
//                        showMainCard(mainCard);
//                        player.showCards();
//                        System.out.print("\nPress enter to see bot choice!");
//                        scanner.nextLine();
//                        Card botCard = ((Bot) player).botChoose(mainCard);
//                        if (botCard == null) {
//                            System.out.print("\nBot should grab a card from storage!Press enter to continue!");
//                            scanner.nextLine();
//                            player.grabStorage(storage);
//                            if (!(player.cantPlay(mainCard))) {                             //can play
//                                continue;
//                            } else {
//                                player.showCards();                                         //cant play
//                            }
//                        } else {
//                            storage.addStorage(mainCard);                                   //PLAYING
//                            mainCard = botCard;
//                            mainCard.act(this);                                      //CART ACTION EXPECTED
//                            player.removeCard(mainCard);
//                            if (player.playerCards.isEmpty()) {
//                                System.out.println("\n" + player.name + " wins!!");
//                                winner = false;
//                                return;
//                            }
//                            showMainCard(mainCard);
//                            if (hasPrize) {
//                                System.out.println("\n" + player.name + " has another shot.\n");
//                                hasPrize = false;
//                                continue;
//                            }
//                            player.isPlaying = false;
//                            System.out.print("\nPress enter to continue");
//                            scanner.nextLine();
//                            break;
//                        }
//                    }
//                }
//            }//end of foreach
        }

}










