package com.ironhack.homeworkRPGSIM;


import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        UniqueID id = new UniqueID();
        Scanner scan = new Scanner(System.in);

        HashMap<Integer, Character> party1 = new HashMap<>();
        HashMap<Integer, Character> party2 = new HashMap<>();
        HashMap<Integer, Character> graveyard = new HashMap<>();

        MainMenu.mainMenu();

        //Warrior testwar = new Warrior();

        //testwar.customiseWarrior();

        //System.out.println(testwar);

        Warrior warrior1 = new Warrior("Jeff Jefferson", 200, 200, 200);
        party1.put(warrior1.getId(), warrior1);
        //warrior1.attack();


        Wizard wizard1 = new Wizard("Pete Peterson", 200, 200, 200);
        party2.put(wizard1.getId(), wizard1);
        //wizard1.attack();

        System.out.println(wizard1.getId());
        System.out.println(party1.get(1));


        for (Integer i : party1.keySet()) {
            System.out.println(i);
            System.out.println(party1.get(i));
        }

        System.out.println(MainMenu.party1.get(1));

        battle(MainMenu.party1.get(1), MainMenu.party2.get(2), party1, party2, graveyard);

        for (Integer i : party1.keySet()) {
            System.out.println(party1.get(i));
        }
        for (Integer i : party2.keySet()) {
            System.out.println(party2.get(i));
        }
        System.out.println("The Graveyard has claimed");
        for (Integer i : graveyard.keySet()) {
            System.out.println(graveyard.get(i));
        }

        createRandomParty(party1, id);
        createRandomParty(party2, id);

    }

    public static void battle(Character character1, Character character2, HashMap party1, HashMap party2, HashMap graveyard) {

        int round = 1;

        while (character1.hp > 0 && character2.hp > 0) {
            System.out.println("Combat round: " + round);
            character1.hp = character1.hp - character2.attack();
            System.out.println(character1.getName() + " has " + character1.hp + " hp remaining");
            character2.hp = character2.hp - character1.attack();
            System.out.println(character2.getName() + " has " + character2.hp + " hp remaining");
            if (character1.hp <= 0) {
                character1.setAlive(false);
                System.out.println(character1.getName() + " has died!");
                graveyard.put(character1.getId(), character1);
                party1.remove(character1.getId());
            }
            if (character2.hp <= 0) {
                character2.setAlive(false);
                System.out.println(character2.getName() + " has died!");
                graveyard.put(character2.getId(), character2);
                party2.remove(character2.getId());
            }
            round++;
        }

    }

    public static void createRandomParty(HashMap party1, UniqueID id) {

        for (int i = 0; i < 10; i++) {
            int randomNum = new Random().nextInt(2);
            if (randomNum == 0) {
                Wizard wizard1 = new Wizard("Pete Peterson", 50 + new Random().nextInt(50), 10 + new Random().nextInt(40), 1 + new Random().nextInt(49));
                party1.put(wizard1.getId(), wizard1);
            } else if (randomNum == 1) {
                Warrior warrior1 = new Warrior("Jeff Jefferson", 100 + new Random().nextInt(100), 10 + new Random().nextInt(40), 1 + new Random().nextInt(9));
                party1.put(warrior1.getId(), warrior1);
            }

        }
        System.out.println("Party created with these brave adventurers: ");
        for (Object i1 : party1.keySet()) {
            System.out.println(party1.get(i1));
        }

    }


}
