package com.ironhack.homeworkRPGSIM;

public class Warrior extends Character implements Attacker{

    private int stamina;
    private int strength;

    public Warrior(int id, String name, int hp, int stamina, int strength) {
        super(id, name, hp);
        setStamina(stamina);
        setStrength(strength);

        System.out.println("Brave new Warrior " + name + " joins the party, they have a hp of " + getHp() +
                " a stamina of " + getStamina() + " and a strength of " + getStrength());
    }

    public int getStamina() {
        return stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setHp(int hp){
        if(hp < 100){
            System.out.println("hp for Warriors must be 100 or more, setting hp for this character to 100");
            this.hp = 100;
        } else if(hp > 200){
            System.out.println("hp for Warriors cannot be more than 200, setting hp for this character to 200");
            this.hp = 200;
        } else {
            this.hp = hp;
        }

    }

    public void setStamina(int stamina) {
        if(stamina < 10){
            System.out.println("Stamina must be 10 or more, setting Stamina for this character to 10");
            this.stamina = 10;
        } else if(stamina >50){
            System.out.println("Stamina cannot be more than 50, setting Stamina for this character to 50");
            this.stamina = 50;
        } else {
            this.stamina = stamina;
        }
    }

    public void setStrength(int strength) {

        if(strength < 1){
            System.out.println("Strength must be 1 or more, setting Strength for this character to 1");
            this.strength = 1;
        } else if(strength >10){
            System.out.println("Strength cannot be more than 10, setting Strength for this character to 10");
            this.strength = 10;
        } else {
            this.strength = strength;
        }
    }



    public int attack() {
        int damage=0;

        if(stamina>=5){
            damage = strength;
            stamina = stamina - 5;
            System.out.println(name + " does a Heavy Attack which inflicts " + damage + " damage, take that!");
        } else {
            damage = strength/2;
            stamina ++;
            System.out.println(name + " is so tired they can only manage a Weak Attack, which inflicts " + damage + " damage, pathetic");
        }


        return damage;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + " Warrior name: " + this.getName() +
                ", hp: " + this.getHp();
    }

}
