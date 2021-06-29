package com.ironhack.homeworkRPGSIM;

import java.util.concurrent.ThreadLocalRandom;

public class Graveyard {
    private String cross = "[✞]";
    private String ghost = "[\uD83D\uDC7B]";
    private String hole = "\uD83D\uDD73";
    private String skull = "[💀]";

    private String emptyGrave = "[🕳]";
    private String fullGraveParty1 = skull;
    private String fullGraveParty2 = ghost;

    private String[][] graveyardArray;
    static int party1_graveyard = 0;
    static int party2_graveyard = 0;

    int randomRowMin = 0;
    int randomRowMax = 9;
    int randomColMin = 0;
    int randomColMax = 4;

    int randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
    int randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

    public Graveyard(String[][] graveyardArray) {
        this.graveyardArray = graveyardArray;
    }

    public void addGraveParty1(String[][] graveyardArray) {

        randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
        randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

        if (graveyardArray[randomColumn][randomRow].equals(emptyGrave)) {
            graveyardArray[randomColumn][randomRow] = fullGraveParty1;
        } else if (graveyardArray[randomColumn][randomRow].equals(fullGraveParty1)) {
            //while (graveyardArray[randomColumn][randomRow].equals(fullGraveParty1)) {
                randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
                randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);
                graveyardArray[randomColumn][randomRow] = fullGraveParty1;
            }
        party1_graveyard ++;
        }

    public void addGraveParty2(String[][] graveyardArray) {

        randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
        randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

        if (graveyardArray[randomColumn][randomRow].equals(emptyGrave)) {
            graveyardArray[randomColumn][randomRow] = fullGraveParty2;
        } else if (graveyardArray[randomColumn][randomRow].equals(fullGraveParty2)) {
            //while (graveyardArray[randomColumn][randomRow].equals(fullGraveParty2)) {
                randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
                randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);
                graveyardArray[randomColumn][randomRow] = fullGraveParty2;
            }
        party2_graveyard ++;
        }

    public int getParty1Graveyard() {
        return party1_graveyard;
    }

    public int getParty2Graveyard() {
        return party2_graveyard;
    }


    public String[][] getGraveyardArray() {
        return graveyardArray;
    }
}
