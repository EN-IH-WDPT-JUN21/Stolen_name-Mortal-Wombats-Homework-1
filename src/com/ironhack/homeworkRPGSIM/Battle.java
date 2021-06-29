package com.ironhack.homeworkRPGSIM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Battle {


    public static void battle(ArrayList party1, ArrayList party2, String[][] gr) {
        Scanner scanner = new Scanner(System.in);
        Character character1 = null;
        Character character2 = null;

        String eg = "[\uD83D\uDD73]"; // String for empty grave

        /*String[][] gr =
                {
                        {eg, eg, eg, eg, eg, eg, eg, eg, eg, eg},
                        {eg, eg, eg, eg, eg, eg, eg, eg, eg, eg},
                        {eg, eg, eg, eg, eg, eg, eg, eg, eg, eg},
                        {eg, eg, eg, eg, eg, eg, eg, eg, eg, eg},
                        {eg, eg, eg, eg, eg, eg, eg, eg, eg, eg},
                };*/
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
                System.out.println("Combat round: " + round);
                character1.hp = character1.hp - character2.attack();
                System.out.println(character1.getName() + " has " + character1.hp + " hp remaining");
                character2.hp = character2.hp - character1.attack();
                System.out.println(character2.getName() + " has " + character2.hp + " hp remaining");
                if (character1.hp <= 0) {
                    character1.setAlive(false);
                    character1.hp = 0;
                    System.out.println(character1.getName() + " has died!");
                    //MainMenu.graveyard.put(character1.getId(), character1);
                    graveyard.addGraveParty1(gr);
                    party1.remove(character1);
                }
                if (character2.hp <= 0) {
                    character2.setAlive(false);
                    character2.hp=0;
                    System.out.println(character2.getName() + " has died!");
                    //MainMenu.graveyard.put(character2.getId(), character2);
                    graveyard.addGraveParty2(gr);
                    party2.remove(character2);
                }
                round++;
            }
        } while (party1.size() > 0 && party2.size() > 0);
        System.out.println("The graveyard claimed the follow: \n" + graveyard + "\n");
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

}
