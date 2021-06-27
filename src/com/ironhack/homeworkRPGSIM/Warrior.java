package com.ironhack.homeworkRPGSIM;

import java.util.Scanner;

public class Warrior extends Character implements Attacker {

    Scanner scan = new Scanner(System.in);

    private int stamina;
    private int strength;

    // Max & Min Values for Stamina,Strength and HP
    final int MAXSTAMINA = 50;
    final int MAXSTRENGTH = 10;
    final int MAXHP = 200;
    final int MINSTAMINA = 10;
    final int MINSTRENGTH = 1;
    final int MINHP = 100;


    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        setStamina(stamina);
        setStrength(strength);

        System.out.println("Brave new Warrior " + name + " joins the party, they have a hp of " + getHp() +
                " a stamina of " + getStamina() + " and a strength of " + getStrength());
    }

    public Warrior() {

    }


    public int getStamina() {
        return stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setHp(int hp) {
        if (hp < MINHP) {
            System.out.println("hp for Warriors must be " + MINHP + " or more, setting hp for this character to "
                    + MINHP);
            this.hp = MINHP;
        } else if (hp > MAXHP) {
            System.out.println("hp for Warriors cannot be more than " + MAXHP + ", setting hp for this character to" +
                    MAXHP);
            this.hp = MAXHP;
        } else {
            this.hp = hp;
        }

    }

    public void setStamina(int stamina) {
        if (stamina < MINSTAMINA) {
            System.out.println("Stamina must be " + MINSTAMINA + " or more, setting Stamina for this character to " +
                    MINSTAMINA);
            this.stamina = MINSTAMINA;
        } else if (stamina > MAXSTAMINA) {
            System.out.println("Stamina cannot be more than " + MAXSTAMINA + ", setting Stamina for this character to " +
                    +MAXSTAMINA);
            this.stamina = MAXSTAMINA;
        } else {
            this.stamina = stamina;
        }
    }

    public void setStrength(int strength) {

        if (strength < MINSTRENGTH) {
            System.out.println("Strength must be " + MINSTRENGTH + " or more, setting Strength for this character to "
                    + MINSTRENGTH);
            this.strength = MINSTRENGTH;
        } else if (strength > MAXSTRENGTH) {
            System.out.println("Strength cannot be more than " + MAXSTRENGTH + ", setting Strength for this character" +
                    " to " + MAXSTRENGTH);
            this.strength = MAXSTRENGTH;
        } else {
            this.strength = strength;
        }
    }


    public int attack() {
        int damage = 0;

        if (stamina >= 5) {
            damage = strength;
            stamina = stamina - 5;
            System.out.println(name + " does a Heavy Attack which inflicts " + damage + " damage, take that!");
        } else {
            damage = strength / 2;
            stamina++;
            System.out.println(name + " is so tired they can only manage a Weak Attack, which inflicts " + damage + " damage, pathetic");
        }


        return damage;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", stamina=" + stamina +
                ", strength=" + strength +
                '}';
    }

    // This method can be called when we want the user to input the warriors details.
    // We would need to create a warrior object first
    public void customiseWarrior() {
        boolean validName = false;
        boolean validHp = false;
        boolean validStrength = false;
        boolean validStamina = false;

        System.out.println("Give your Warrior a name:> ");

        while (!validName) {
            try {
                setName(scan.next());
                validName = true;
            } catch (Exception er) {
                System.out.println("Please input a valid name: ");
                scan.next();
            }
        }
        System.out.println("Set " + getName() + "'s starting health:> ");
        while (!validHp) {
            try {
                setHp(scan.nextInt());
                validHp = true;
            } catch (Exception er) {
                System.out.println("Please input a number: ");
                scan.next();
            }
        }
        System.out.println("set "+ getName() + "'s starting stamina:> ");
        while (!validStamina) {
            try {
                setStamina(scan.nextInt());
                validStamina = true;
            } catch (Exception er) {
                System.out.println("Please input a number: ");
                scan.next();
            }
        }
        System.out.println("Set "+ getName() + "'s strength:> ");
        while (!validStrength) {
            try {
                setStrength(scan.nextInt());
                validStrength = true;
            } catch (Exception er) {
                System.out.println("Please input a number: ");
                scan.next();
            }
        }
        System.out.println("Brave new Warrior " + getName() + " joins the party, they have a hp of " + getHp() +
                " a stamina of " + getStamina() + " and a strength of " + getStrength());
    }
}
