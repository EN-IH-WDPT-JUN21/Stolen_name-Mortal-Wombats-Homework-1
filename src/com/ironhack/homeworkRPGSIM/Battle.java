package com.ironhack.homeworkRPGSIM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battle {

    public static void battle(ArrayList party1, ArrayList party2, String[][] gr, List<String> party1Died, List<String> party2Died) {
        Scanner scanner = new Scanner(System.in);
        Character character1 = null;
        Character character2 = null;

        String eg = "[\uD83D\uDD73]"; // String for empty grave

        Graveyard graveyard = new Graveyard(gr);



        do {
            if (MainMenu.playerNumber == 1 && party1.size() > 1) {
                character1 = chooseCharacter(party1);
                character2 = (Character) party2.get(new Random().nextInt(party2.size()));
            } else if (MainMenu.playerNumber == 1 && party1.size() == 1) {
                character1 = (Character) party1.get(0);
                character2 = (Character) party2.get(new Random().nextInt(party2.size()));
            }else if (MainMenu.playerNumber >= 2) {
                System.out.println("**** PLAYER 1 ****");
                character1 = chooseCharacter(party1);
                System.out.println("**** PLAYER 2 ****");
                character2 = chooseCharacter(party2);

                // **** THESE WILL ALLOW FOR AUTO CHARACTER SELECTION SHOULD A PARTY HAVE ONLY 1 MEMBER ****
            /* } else if (MainMenu.playerNumber == 2 && party1.size() > 1 && party2.size() == 1) {
                System.out.println("**** PLAYER 1 ****");
                character1 = chooseCharacter(party1);

                character2 = (Character) party2.get(0);
            } else if (MainMenu.playerNumber == 2 && party1.size() == 1 && party2.size() > 1) {
                character1 = (Character) party1.get(0);
                character2 = (Character) party2.get(0);
            } else if (MainMenu.playerNumber == 2 && party1.size() == 1 && party2.size() == 1) {
                character2 = (Character) party1.get(0);
                character2 = (Character) party2.get(0); */
            } else {
                character1 = (Character) party1.get(new Random().nextInt(party1.size()));
                character2 = (Character) party2.get(new Random().nextInt(party2.size()));
            }

            int round = 1;


            while (character1.hp > 0 && character2.hp > 0) {
                System.out.println("\nCombat round: " + round +
                                   "\n-----------------");
                character1.hp = character1.hp - character2.attack();
                character2.hp = character2.hp - character1.attack();
                if (character1.hp <= 0) {
                    character1.setAlive(false);
                    character1.hp = 0;
                    System.out.println(character1.getName() + " has " + character1.hp + " hp!");
                    System.out.println(character1.getName() + " has died!");
                    graveyard.addGraveParty1(gr);
                    graveyard.party1DeadCount(party1Died, character1);
                    party1.remove(character1);
                } else {
                    System.out.println(character1.getName() + " has " + character1.hp + " hp remaining");
                }
                if (character2.hp <= 0) {
                    character2.setAlive(false);
                    character2.hp=0;
                    System.out.println(character2.getName() + " has " + character2.hp + " hp!");
                    System.out.println(character2.getName() + " has died!");
                    graveyard.addGraveParty2(gr);
                    graveyard.party2DeadCount(party2Died, character2);
                    party2.remove(character2);
                } else {
                    System.out.println(character2.getName() + " has " + character2.hp + " hp remaining");
                }
                round++;
            }
        } while (party1.size() > 0 && party2.size() > 0);
    }

    public static Character chooseCharacter(ArrayList party) {
        Scanner scanner = new Scanner(System.in);
        Character character1 = null;


            int characterNumber = 1;
            System.out.println("Choose who you would like to send into battle? \n ");
            for (int i = 0; i < party.size(); i++) {
                System.out.println(characterNumber + " " + party.get(i));
                characterNumber++;
            }
            boolean validSelection = false;
            while (!validSelection) {
                try {
                    int selection = scanner.nextInt();
                    character1 = (Character) party.get(selection - 1);
                    validSelection = true;
                } catch (Exception e) {
                    System.out.println("Please, Choose a valid character number \n");
                    scanner.next();
                }
            }

        return character1;
    }

    // GENERATING AND SHOWING A GRAVEYARD
    public static void generateGraveyard(ArrayList party1, ArrayList party2, String[][] gr, String[] legend,
                                         List<String> party1Died, List<String> party2Died) {
        Graveyard graveyard = new Graveyard(gr);

        System.out.println("\n**************** A GRIM VIEW AT THE GRAVEYARD AFTER THE BATTLE! *****************\n");

        // Looping through 2D array and "printing graves" to graveyard at random indexes
        for (int i = 0; i < 5; i++) {
            System.out.print("      ");
            for (int j = 0; j < 6; j++) {
                System.out.print(gr[i][j] + "   ");
            }
            // Printing legend next to graveyard row by row
            System.out.println(legend[i]);
        }

        // PRINTING PROPER VICTORY MESSAGE DEPENDING ON WITH PARTY WON THE BATTLE
        if (party1.size() == 0 && party2.size() > 0) {
            if (graveyard.getParty2Graveyard() == 0) {
                System.out.println("\nPARTY 2 EMERGED FROM THIS BATTLE VICTORIOUS AND WITHOUT ANY LOSES!");
                System.out.println("DIED IN PARTY 1: " + party1Died);
            } else if (graveyard.getParty2Graveyard() == 1) {
                System.out.println("\nALTHOUGH THEY'VE BURIED " + graveyard.getParty2Graveyard() + " COMPANION, PARTY 2 EMERGED VICTORIOUS FROM THIS BATTLE!");
                System.out.println("DIED IN PARTY 1: " + party1Died);
                System.out.println("DIED IN PARTY 2: " + party2Died);
            } else {
                System.out.println("\nALTHOUGH THEY'VE BURIED " + graveyard.getParty2Graveyard() + " COMPANIONS, PARTY 2 EMERGED VICTORIOUS FROM THIS BATTLE!");
                System.out.println("DIED IN PARTY 1: " + party1Died);
                System.out.println("DIED IN PARTY 2: " + party2Died);
            }
        } else if (party2.size() == 0 && party1.size() > 0) {
            if (graveyard.getParty1Graveyard() == 0) {
                System.out.println("\nPARTY 1 EMERGED FROM THIS BATTLE VICTORIOUS AND WITHOUT ANY LOSES!");
                System.out.println("DIED IN PARTY 2: " + party2Died);
            }
            else if (graveyard.getParty1Graveyard() == 1) {
                System.out.println("\nALTHOUGH THEY'VE BURIED " + graveyard.getParty1Graveyard() + " COMPANION, PARTY 1 EMERGED VICTORIOUS FROM THIS BATTLE!");
                System.out.println("DIED IN PARTY 1: " + party1Died);
                System.out.println("DIED IN PARTY 2: " + party2Died);
            }
            else {
                System.out.println("\nALTHOUGH THEY'VE BURIED " + graveyard.getParty1Graveyard() + " COMPANIONS, PARTY 1 EMERGED VICTORIOUS FROM THIS BATTLE!");
                System.out.println("DIED IN PARTY 1: " + party1Died);
                System.out.println("DIED IN PARTY 2: " + party2Died);
            }
        } else if (party1.size() == 0 && party2.size() == 0) {
            System.out.println("ALL BRAVE ADVENTURERS DIED IN THIS EVEN BATTLE. THEY WILL STAY AT THIS GRAVEYARD FOREVER!");
            System.out.println("DIED IN PARTY 1: " + party1Died);
            System.out.println("DIED IN PARTY 2: " + party2Died);
        }
    }

}
