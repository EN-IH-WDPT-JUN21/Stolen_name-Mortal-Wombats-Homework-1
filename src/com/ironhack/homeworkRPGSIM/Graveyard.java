package com.ironhack.homeworkRPGSIM;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Graveyard {

    // Different string icons that can be used when printing graveyard
    private final String ghost = "[\uD83D\uDC7B]";
    private final String hole = "[\uD83D\uDD73]";
    private final String skull = "[💀]";

    private final String emptyGrave = hole;

    // Set graveyard for both parties to 0
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

    public Graveyard() {
        // Create graveyard 2D array
    }

    // Creating graveyard for Party 1
    public void addGraveParty1(String[][] gr) {

        randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
        randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

        if (!gr[randomColumn][randomRow].equals(emptyGrave)) {
            do {
                randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
                randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);
            } while (!gr[randomColumn][randomRow].equals(emptyGrave));
        }
        gr[randomColumn][randomRow] = skull;
        party1_graveyard++;
    }

    public void addGraveParty2(String[][] gr) {

        randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
        randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

        if (!gr[randomColumn][randomRow].equals(emptyGrave)) {
            do {
                randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
                randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

            } while (!gr[randomColumn][randomRow].equals(emptyGrave));
        }
        gr[randomColumn][randomRow] = ghost;
        party2_graveyard++;
    }

    public void party1DeadCount(List<String> party1Died, Character character1Name) {
        party1Died.add(character1Name.getName());
    }

    public void party2DeadCount(List<String> party2Died, Character character2Name) {
        party2Died.add(character2Name.getName());
    }

    public int getParty1Graveyard() {
        return party1_graveyard;
    }

    public int getParty2Graveyard() {
        return party2_graveyard;
    }

}
