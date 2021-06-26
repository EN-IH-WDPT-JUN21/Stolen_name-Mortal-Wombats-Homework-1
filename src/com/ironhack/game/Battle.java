package com.ironhack.game;

import com.ironhack.characters.Warrior;
import com.ironhack.characters.Wizard;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Battle {

    private static int ch =0;
    private static int playerNumber = 1;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        mainMenu();


        scanner.close();
    }


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
        int characterNumber = 1;
        String name;
        int hp;
        int role;
        //Mana or stamina
        int attackResources;
        //Strength or intelligence
        int attackPower;
        ArrayList<Character> party = new ArrayList<>();

        System.out.println ("******************************");
        System.out.println ("\nFor each Character, you will have to define their names and their stats.");


        do {
            do {
                System.out.println("What's character" + characterNumber + "'s name?");
                name = scanner.next();
                System.out.println("Are you satisfied with " + name + "? Y/N");
                ch = scanner.next().charAt(0);
            } while (ch != 'Y' && ch != 'y');

            do {
                System.out.println("What's " + name + "'s hp?");
                hp = scanner.nextInt();
                System.out.println("Are you satisfied with " + name + "'s hp being " + hp + "? Y/N");
                ch = scanner.next().charAt(0);
            } while (ch != 'Y' && ch != 'y');

            do {
                System.out.println("Is " + name + " a Warrior or a Wizard?");
                System.out.println("1 - Warrior");
                System.out.println("2 - Wizard");
                role = scanner.nextInt();
                if (role == 1) {
                    System.out.println("Are you satisfied with " + name + "'s being a Warrior? Y/N");
                    ch = scanner.next().charAt(0);
                } else if (role == 2) {
                    System.out.println("Are you satisfied with " + name + "'s being a Wizard? Y/N");
                    ch = scanner.next().charAt(0);
                } else {
                    System.out.println("Please select a valid option.");
                }
            } while (ch != 'Y' && ch != 'y');

            if (role == 1) {
                do {
                    System.out.println("What's " + name + "'s stamina?");
                    attackResources = scanner.nextInt();
                    System.out.println("Are you satisfied with " + name + "'s stamina being " + attackResources + "? Y/N");
                    ch = scanner.next().charAt(0);
                } while (ch != 'Y' && ch != 'y');
                do {
                    System.out.println("What's " + name + "'s strength?");
                    attackPower = scanner.nextInt();
                    System.out.println("Are you satisfied with " + name + "'s strength being " + attackPower + "? Y/N");
                    ch = scanner.next().charAt(0);
                } while (ch != 'Y' && ch != 'y');
            } else {
                do {
                    System.out.println("What's " + name + "'s mana?");
                    attackResources = scanner.nextInt();
                    System.out.println("Are you satisfied with " + name + "'s mana being " + attackResources + "? Y/N");
                    ch = scanner.next().charAt(0);
                } while (ch != 'Y' && ch != 'y');
                do {
                    System.out.println("What's " + name + "'s intelligence?");
                    attackPower = scanner.nextInt();
                    System.out.println("Are you satisfied with " + name + "'s intelligence being " + attackPower + "? Y/N");
                    ch = scanner.next().charAt(0);
                } while (ch != 'Y' && ch != 'y');
            }




            if (role == 1) {
          //     Warrior w = new Warrior(name, hp, attackResources, attackPower);
          //     party.add(w);

            }
            else {
             //   Wizard w = new Wizard(name, hp, attackResources, attackPower);
                //     party.add(w);
            }



            System.out.println(name + " was created with success!\n");
            System.out.println("STATS:");
            System.out.println("- HP: " + hp);
            System.out.println(role == 1 ? "- Stamina: " + attackResources : "- Mana: " + attackResources);
            System.out.println(role == 2 ? "- Strength: " + attackPower : "- Intelligence: " + attackPower);

            System.out.println();

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


