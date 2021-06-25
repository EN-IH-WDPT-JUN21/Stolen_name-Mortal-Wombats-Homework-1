package MortalWombats;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class graveyard {
    private ArrayList<String> graveyard_Array;
    private String tombstone;
    private String[][] gr;

    public graveyard(ArrayList<String> graveyard_Array) {
        this.graveyard_Array = graveyard_Array;
    }

    public ArrayList<String> getGraveyard_Array() {
        return graveyard_Array;
    }

    public void addGrave(String[][] gr) {

        int randomRowMin = 0;
        int randomRowMax = 4;
        int randomColMin = 0;
        int randomColMax = 4;

        // generate random values for row and column of Graveyard Array
        /*int randomRow = (int)Math.floor(Math.random()*(randomRowMax-randomRowMin)+randomRowMax);
        int randomColumn = (int)Math.floor(Math.random()*(randomColMax-randomColMin+1)+randomColMax);*/

        int randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
        int randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

        if (gr[randomRow][randomColumn].equals("| |")) {
            gr[randomRow][randomColumn] = "[✞]";
        } else if (gr[randomRow][randomColumn].equals("[✞]")) {
            while (gr[randomRow][randomColumn].equals("[✞]")) {
                randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
                randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);
                gr[randomRow][randomColumn] = "[✞]";
            }
        }
    }

    @Override
    public String toString() {
        return "graveyard_Array";
    }
}
