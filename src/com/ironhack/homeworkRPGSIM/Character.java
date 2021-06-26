package com.ironhack.homeworkRPGSIM;

abstract public class Character {
    protected int id;
    protected String name;
    protected int hp;
    protected boolean isAlive = true;

    public Character(int id, String name, int hp) {
        setId(id);
        setName(name);
        setHp(hp);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    abstract public int attack();


}
