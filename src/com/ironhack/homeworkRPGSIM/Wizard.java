package com.ironhack.homeworkRPGSIM;

public class Wizard extends Character implements Attacker{

    private int mana;
    private int intelligence;

    public Wizard(int id, String name, int hp, int mana, int intelligence) {
        super(id, name, hp);
        setMana(mana);
        setIntelligence(intelligence);
        System.out.println("Wise old Wizard " + name + " joins the party, they have a hp of " + getHp() +
                " a mana pool of " + getMana() + " and an intelligence of " + getIntelligence());
    }

    public int getMana() {
        return mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setHp(int hp){
        if(hp < 50){
            System.out.println("hp for Wizards must be 50 or more, setting hp for this character to 50");
            this.hp = 50;
        } else if(hp > 100){
            System.out.println("hp for Wizards cannot be more than 100, setting hp for this character to 100");
            this.hp = 100;
        } else {
            this.hp = hp;
        }

    }


    public void setMana(int mana) {
        if(mana < 10){
            System.out.println("mana must be 10 or more, setting mana for this character to 10");
            this.mana = 10;
        } else if(mana >50){
            System.out.println("mana cannot be more than 50, setting mana for this character to 50");
            this.mana = 50;
        } else {
            this.mana = mana;
        }
    }

    public void setIntelligence(int intelligence) {

        if(intelligence < 1){
            System.out.println("intelligence must be 1 or more, setting intelligence for this character to 1");
            this.intelligence = 1;
        } else if(intelligence >50){
            System.out.println("intelligence cannot be more than 50, setting intelligence for this character to 50");
            this.intelligence = 50;
        } else {
            this.intelligence = intelligence;
        }
    }



    public int attack() {
        int damage=0;

        if(mana>=5){
            damage = intelligence;
            mana = mana - 5;
            System.out.println(name + " casts Fireball which inflicts " + damage + " damage, Zap!");
        } else {
            damage = 1;
            mana ++;
            System.out.println(name + " is so tired they can only manage a Staff hit, which inflicts " + damage + " damage, pathetic");
        }

        return damage;
    }

    @Override
    public String toString() {
         return "Id: " + this.getId() +" Wizard name: " + this.getName() +
                ", hp: " + this.getHp();
    }
}
