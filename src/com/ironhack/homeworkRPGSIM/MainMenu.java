package com.ironhack.homeworkRPGSIM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.util.stream.DoubleStream.concat;

class MainMenu {

    private static int ch =0;
    private static int playerNumber = 1;
    private static final Scanner scanner = new Scanner(System.in);
    static HashMap<Integer, Character> party1 = new HashMap<>();
    static HashMap<Integer, Character> party2 = new HashMap<>();


    public static void mainMenu() {

        System.out.println ("*************** WELCOME TO MORTAL WOMBATS ***************");
        System.out.println();
        System.out.println ("Choose a game mode:");

        do {
            System.out.println("1 - 1 Player");
            System.out.println("2 - 2 Players");
            System.out.println("3 - Generate Random Game");


            getsChoice();


            switch (ch) {
                case 1 -> {
                    System.out.println("You chose 1 Player\n");
                    onePlayerGameMode();
                }
                case 2 -> {
                    System.out.println("You chose 2 Players\n");
                    twoPlayerGameMode();
                }
                case 3 -> {
                    System.out.println("You chose Random Game\n");
                    generateRandomGame();
                }
                default -> System.out.println("Please, choose a valid game mode.\n");
            }
        } while (ch != 1 && ch != 2 && ch != 3);

    }


    public static void onePlayerGameMode() {
        System.out.println ("******************************");
        System.out.println ("\nDo you want to create your own party or generate a random one?");

        do {
            System.out.println ("1 - Create my own party");
            System.out.println ("You will choose your party and your opponent's will be random.");
            System.out.println ("2 - Generate a random party");
            System.out.println (" Both your and your opponent's parties will be random.");
            System.out.println ("3 - Import party");
            System.out.println ("You will import your party and your opponent's will be random.");
            System.out.println ("0 - Return to Main Menu");

            getsChoice();

            switch (ch) {
                case 1:
                    createOwnParty(); //addCharactersToParties(createOwnParty());
                    generateRandomParty(); //addCharactersToParties(generateRandomParty());
                    System.out.println("******** MAY THE BATTLE BEGIN! ******** ");
                    //battle();
                    break;
                case 2:
                    generateRandomParty(); //addCharactersToParties(generateRandomParty());
                    generateRandomParty(); //addCharactersToParties(generateRandomParty());
                    System.out.println("******** MAY THE BATTLE BEGIN! ******** ");
                    //battle();
                    break;
                case 3:
                    importParty(); //addCharactersToParties(importParty());
                    generateRandomParty(); //addCharactersToParties(generateRandomParty());
                    System.out.println("******** MAY THE BATTLE BEGIN! ******** ");
                    //battle();
                case 0:
                    mainMenu();
                default:
                    System.out.println("Please, choose a valid game mode.\n");
                    break;
            }
        } while (ch != 1 && ch != 2 && ch != 0);

    }

    public static void twoPlayerGameMode() {
        while(playerNumber < 3) {
            System.out.println("******************************");
            System.out.println("PLAYER " + playerNumber);
            System.out.println("\nDo you want to create your own party or generate a random one?");

            do {
                System.out.println("1 - Create my own party");
                System.out.println("2 - Generate a random party");
                System.out.println("3 - Import party");
                System.out.println("0 - Return to Main Menu");

                getsChoice();

                switch (ch) {
                    case 1:
                        createOwnParty(); //addCharactersToParties(createOwnParty());
                        playerNumber++;
                        break;
                    case 2:
                        generateRandomParty(); //addCharactersToParties(generateRandomParty());
                        playerNumber++;
                        break;
                    case 3:
                        importParty(); //addCharactersToParties(importParty());
                        playerNumber++;
                    case 0:
                        mainMenu();
                    default:
                        System.out.println("Please, choose a valid game mode.\n");
                        break;
                }
            } while (ch != 1 && ch != 2 && ch != 0);
        }
        System.out.println("battle!");
    }

    private static ArrayList<Character> createOwnParty() {

        char ch;
        int characterNumber = 0;


        ArrayList<Character> party = new ArrayList<>();

        System.out.println ("******************************");
        System.out.println ("\nFor each Character, you will have to define their names and their stats.");


        do {
            boolean validCharacter = false;

            System.out.println("\n What type of Character would you like to create?" +
                    "\n 1. Warrior" +
                    "\n 2. Wizard");

            while (!validCharacter) {

                try {
                    int selection = scanner.nextInt();
                    if(!party1.isEmpty()){
                        if(selection == 1) {
                            Warrior warrior = new Warrior();
                            warrior.customiseWarrior();
                            validCharacter = true;
                            party2.put(warrior.getId(),warrior);
                            party.add(warrior);
                        }
                        else if (selection == 2){
                            Wizard wizard = new Wizard();
                            wizard.customiseWizard();
                            validCharacter = true;
                            party2.put(wizard.getId(),wizard);
                            party.add(wizard);
                        }
                    } else if(selection == 1) {
                        Warrior warrior = new Warrior();
                        warrior.customiseWarrior();
                        validCharacter = true;
                        party1.put(warrior.getId(),warrior);
                    }
                    else if (selection == 2){
                        Wizard wizard = new Wizard();
                        wizard.customiseWizard();
                        validCharacter = true;
                        party1.put(wizard.getId(),wizard);
                    }
                } catch (Exception er) {
                    System.out.println("Please input a valid number: ");
                    scanner.next();
                }
            }

            System.out.println("Do you want to create a new character? Y/N");
            ch = scanner.next().charAt(0);
            characterNumber++;
        } while (ch == 'Y' || ch == 'y');


        System.out.println("Your party was created with success!");

     /*   for( Character c : getParty1Players()){
            System.out.println (c.getName() + " : HP: " + c.getHp() + " | "(...));
        }
        */


        return party;


    }

    private static void generateRandomParty() {
    }

    private static void importParty() {
    }

    private static void generateRandomGame() {
    }

    private static void getsChoice() {

        try {
            ch = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please, choose a valid game mode.\n");
            scanner.next();
            getsChoice();
        }

    }

}
