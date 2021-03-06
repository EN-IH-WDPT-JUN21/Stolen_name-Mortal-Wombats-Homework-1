package com.ironhack.homeworkRPGSIM;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Warrior extends Character implements Attacker {

    Scanner scan = new Scanner(System.in);

    private int stamina;
    private int strength;

    // **** Max & Min Values for Stamina,Strength and HP ****
    final int MAXSTAMINA = 50;
    final int MAXSTRENGTH = 10;
    final int MAXHP = 200;
    final int MINSTAMINA = 10;
    final int MINSTRENGTH = 1;
    final int MINHP = 100;

    // **** CONSTRUCTORS ****

    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        setStamina(stamina);
        setStrength(strength);
    }

    public Warrior() {

    }

    // **** GETTERS & SETTERS ****

    public int getStamina() {
        return stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setHp(int hp) {
        if (hp < MINHP) {
            System.out.println("HP for Warriors must be " + MINHP + " or more, setting hp for this character to "
                    + MINHP + ".");
            this.hp = MINHP;
        } else if (hp > MAXHP) {
            System.out.println("HP for Warriors cannot be more than " + MAXHP + ", setting HP for this character to " +
                    MAXHP + ".");
            this.hp = MAXHP;
        } else {
            this.hp = hp;
        }

    }

    public void setStamina(int stamina) {
        if (stamina < MINSTAMINA) {
            System.out.println("Stamina must be " + MINSTAMINA + " or more, setting Stamina for this character to " +
                    MINSTAMINA + ".");
            this.stamina = MINSTAMINA;
        } else if (stamina > MAXSTAMINA) {
            System.out.println("Stamina cannot be more than " + MAXSTAMINA + ", setting Stamina for this character to " +
                    +MAXSTAMINA + ".");
            this.stamina = MAXSTAMINA;
        } else {
            this.stamina = stamina;
        }
    }

    public void setStrength(int strength) {

        if (strength < MINSTRENGTH) {
            System.out.println("Strength must be " + MINSTRENGTH + " or more, setting Strength for this character to "
                    + MINSTRENGTH + ".");
            this.strength = MINSTRENGTH;
        } else if (strength > MAXSTRENGTH) {
            System.out.println("Strength cannot be more than " + MAXSTRENGTH + ", setting Strength for this character" +
                    " to " + MAXSTRENGTH + ".");
            this.strength = MAXSTRENGTH;
        } else {
            this.strength = strength;
        }
    }

    // **** ATTACK FUNCTION ****

    public int attack() {
        int damage;

        if (stamina >= 5) {
            damage = strength;
            stamina = stamina - 5;
            System.out.println(name + " does a Heavy Attack which inflicts " + damage + " damage. Take that!");
        } else {
            damage = (strength / 2 >= 1) ? strength / 2 : 1;
            stamina++;
            System.out.println(name + " is so tired they can only manage a Weak Attack, which inflicts " + damage + " damage. Pathetic.");
        }


        return damage;
    }

    // **** TO STRING METHOD FOR PRETTIER PRINTING ****

    @Override
    public String toString() {
        return "Warrior: " + name +
                "| HP: " + hp +
                "| Stamina: " + stamina +
                "| Strength: " + strength;
    }


    // **** METHOD TO MANUALLY CREATE CHARACTER ****

    public void customiseWarrior() {
        boolean validName = false;
        boolean validHp = false;
        boolean validStrength = false;
        boolean validStamina = false;

        System.out.println("\nGive your Warrior a name:");

        while (!validName) {
            try {
                setName(scan.nextLine());
                validName = true;
            } catch (InputMismatchException er) {
                System.out.println("Please input a valid name: ");
                scan.next();
            }
        }
        System.out.println("\nSet " + getName() + "'s starting health (between " + MINHP + " and " + MAXHP + "):");
        while (!validHp) {
            try {
                setHp(scan.nextInt());
                validHp = true;
            } catch (Exception er) {
                System.out.println("Please input a number:");
                scan.next();
            }
        }
        System.out.println("\nSet "+ getName() + "'s starting stamina (between " + MINSTAMINA + " and " + MAXSTAMINA + "):");
        while (!validStamina) {
            try {
                setStamina(scan.nextInt());
                validStamina = true;
            } catch (InputMismatchException er) {
                System.out.println("Please input a number: ");
                scan.next();
            }
        }
        System.out.println("\nSet "+ getName() + "'s strength (between " + MINSTRENGTH + " and " + MAXSTRENGTH + "):");
        while (!validStrength) {
            try {
                setStrength(scan.nextInt());
                validStrength = true;
            } catch (InputMismatchException er) {
                System.out.println("Please input a number: ");
                scan.next();
            }
        }
    }

}
