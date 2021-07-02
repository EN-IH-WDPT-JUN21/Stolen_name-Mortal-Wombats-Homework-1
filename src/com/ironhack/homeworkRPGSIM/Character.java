package com.ironhack.homeworkRPGSIM;

abstract public class Character {
    UniqueID uniqueId = new UniqueID();
    protected int id = uniqueId.generateID();
    protected String name;
    protected int hp;
    protected boolean isAlive = true;

    public Character(){
    }

    public Character(int id){
        setId(id);
    }

    public Character(String name, int hp) {
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
