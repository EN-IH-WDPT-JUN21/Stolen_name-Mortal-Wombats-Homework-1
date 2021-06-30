package com.ironhack.homeworkRPGSIM;

import java.util.concurrent.ThreadLocalRandom;

public class Graveyard {
    // Different string icons that can be used when printing graveyard
    private String cross = "[✞]";
    private String ghost = "[\uD83D\uDC7B]";
    private String hole = "[\uD83D\uDD73]";
    private String skull = "[💀]";

    private String emptyGrave = hole;
    private String fullGraveParty1 = skull;
    private String fullGraveParty2 = ghost;

    // Create graveyard 2D array and set graveyard for both parties to 0
    private String[][] gr;
    static int party1_graveyard = 0;
    static int party2_graveyard = 0;

    // Row and column limits needed for random generator
    int randomRowMin = 0;
    int randomRowMax = 5;
    int randomColMin = 0;
    int randomColMax = 4;

    // Initializing random row and column
    int randomRow;
    int randomColumn;

    public Graveyard(String[][] gr) {
        this.gr = gr;
    }

    // Creating graveyard for Party 1
    public void addGraveParty1(String[][] gr) {

        randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
        randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

        int count = 0;
        if (gr[randomColumn][randomRow].equals(emptyGrave)) {
            gr[randomColumn][randomRow] = fullGraveParty1;
        } else if (gr[randomColumn][randomRow].equals(fullGraveParty1)) {
            do {
                count++;
                System.out.println(count);
                randomRow = (int) ((Math.random() * (randomRowMax - randomRowMin)) + randomRowMax);
                randomColumn = (int) ((Math.random() * (randomColMax - randomColMin)) + randomColMax);
                gr[randomColumn][randomRow] = fullGraveParty1;
            } while (gr[randomColumn][randomRow].equals(fullGraveParty1));
        }
        /*int count = 0;
        while (!gr[randomColumn][randomRow].equals(fullGraveParty1)) {
            count++;
            System.out.println(count);
            randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
            randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);
            gr[randomColumn][randomRow] = fullGraveParty1;
        }*/
        party1_graveyard++;
    }

    public void addGraveParty2(String[][] gr) {

        randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
        randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

        int count2 = 0;
        if (gr[randomColumn][randomRow].equals(emptyGrave)) {
            gr[randomColumn][randomRow] = fullGraveParty2;
        } else if (gr[randomColumn][randomRow].equals(fullGraveParty2)) {
            do {
                count2++;
                System.out.println(count2);
                randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
                randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);
                gr[randomColumn][randomRow] = fullGraveParty2;
            } while (gr[randomColumn][randomRow].equals(fullGraveParty2));

        }

        /*while (!gr[randomColumn][randomRow].equals(fullGraveParty2)) {
            count2++;
            System.out.println(count2);
            randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
            randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);
            gr[randomColumn][randomRow] = fullGraveParty1;
        }*/
        party2_graveyard++;
    }

    public int getParty1Graveyard() {
        return party1_graveyard;
    }

    public int getParty2Graveyard() {
        return party2_graveyard;
    }

}
