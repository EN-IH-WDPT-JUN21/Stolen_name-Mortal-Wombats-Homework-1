package com.ironhack.homeworkRPGSIM;

import java.util.Scanner;

public class Wizard extends Character implements Attacker{

    Scanner scan = new Scanner(System.in);

    private int mana;
    private int intelligence;

    //**** Setting Variables for Max and Min Stats ****

    final int MAXINTELLIGENCE = 50;
    final int MAXMANA = 50;
    final int MAXHP = 100;
    final int MININTELLIGENCE = 1;
    final int MINMANA = 10;
    final int MINHP = 50;

    // **** Constructors ****

    public Wizard(String name, int hp, int mana, int intelligence) {
        super(name, hp);
        setMana(mana);
        setIntelligence(intelligence);

    }

    public Wizard() {

    }

    // **** Getters & Setters ****

    public int getMana() {
        return mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setHp(int hp){
        if(hp < MINHP){
            System.out.println("Wizards' HP must be "+ MINHP +" or more, setting HP for this character to " + MINHP + ".");
            this.hp = MINHP;
        } else if(hp > MAXHP){
            System.out.println("Wizards' HP cannot be more than " + MAXHP +", setting HP for this character to " +
                    MAXHP + ".");
            this.hp = MAXHP;
        } else {
            this.hp = hp;
        }

    }


    public void setMana(int mana) {
        if(mana < MINMANA){
            System.out.println("Mana must be " + MINMANA +" or more, setting mana for this character to " +
                    MINMANA+".");
            this.mana = MINMANA;
        } else if(mana > MAXMANA){
            System.out.println("Mana cannot be more than " + MAXMANA +", setting mana for this character to "
                    + MAXMANA+".");
            this.mana = MAXMANA;
        } else {
            this.mana = mana;
        }
    }

    public void setIntelligence(int intelligence) {

        if(intelligence < MININTELLIGENCE){
            System.out.println("Intelligence must be " + MININTELLIGENCE +" or more, setting intelligence for this " +
                    "character to " + MININTELLIGENCE+".");
            this.intelligence = MININTELLIGENCE;
        } else if(intelligence >MAXINTELLIGENCE){
            System.out.println("Intelligence cannot be more than " + MAXINTELLIGENCE +", setting intelligence for this " +
                    "character to " + MAXINTELLIGENCE+".");
            this.intelligence = MAXINTELLIGENCE;
        } else {
            this.intelligence = intelligence;
        }
    }

    // **** ATTACK FUNCTION ****

    public int attack() {
        int damage;

        if(mana>=5){
            damage = intelligence;
            mana = mana - 5;
            System.out.println(name + " casts Fireball which inflicts " + damage + " damage. Zap!");
        } else {
            damage = 1;
            mana ++;
            System.out.println(name + " is so tired they can only manage a Staff hit, which inflicts " + damage + " damage. Pathetic.");
        }

        return damage;
    }

    // **** TO STRING METHOD FOR PRETTIER FORMATTING ****

    @Override
    public String toString() {
        return "Wizard: " + name +
                " | HP: " + hp +
                "| Mana: " + mana +
                "| Intelligence: " + intelligence;
    }

    // **** METHOD TO MANUALLY CREATE CHARACTER ****

    public void customiseWizard() {
        boolean validName = false;
        boolean validHp = false;
        boolean validMana = false;
        boolean validIntelligence = false;

        System.out.println("\nGive your Wizard a name:");
        while (!validName) {
            try {
                setName(scan.nextLine());
                validName = true;
            } catch (Exception er) {
                System.out.println("Please input a valid name: ");
                scan.next();
            }
        }
        System.out.println("\nSet "+ getName() + "'s starting health (between "+ MINHP + " and " + MAXHP + "):");
        while (!validHp) {
            try {
                setHp(scan.nextInt());
                validHp = true;
            } catch (Exception er) {
                System.out.println("Please input a number: ");
                scan.next();
            }
        }
        System.out.println("\nSet "+ getName() + "'s starting mana (between "+ MINMANA + " and " + MAXMANA + "):");
        while (!validMana) {
            try {
                setMana(scan.nextInt());
                validMana = true;
            } catch (Exception er) {
                System.out.println("Please input a number: ");
                scan.next();
            }
        }
        System.out.println("\nSet " + getName() + "'s intelligence (between "+ MININTELLIGENCE + " and " + MAXINTELLIGENCE + "):");
        while (!validIntelligence) {
            try {
                setIntelligence(scan.nextInt());
                validIntelligence = true;
            } catch (Exception er) {
                System.out.println("Please input a number: ");
                scan.next();
            }
        }
    }
}
