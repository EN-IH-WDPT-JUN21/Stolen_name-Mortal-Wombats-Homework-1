package MortalWombats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        graveyard grav = new graveyard(list);
        String[][] gr =
                {
                        {"| |", "| |", "| |", "| |", "| |"},
                        {"| |", "| |", "| |", "| |", "| |"},
                        {"| |", "| |", "| |", "| |", "| |"},
                        {"| |", "| |", "| |", "| |", "| |"},
                        {"| |", "| |", "| |", "| |", "| |"}
                };
        int randomRowMin = 0;
        int randomRowMax = 4;
        int randomColMin = 0;
        int randomColMax = 4;

        // generate random values for row and column of Graveyard Array
        /*int randomRow = (int)Math.floor(Math.random()*(randomRowMax-randomRowMin)+randomRowMax);
        int randomColumn = (int)Math.floor(Math.random()*(randomColMax-randomColMin+1)+randomColMax);*/

        int randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
        int randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

        String emptySpace = "| |";
        String cross = "✞";

        /*System.out.println(grav.getGraveyard_Array());
        grav.addGraves(emptySpace);
        grav.addGraves(emptySpace);
        grav.addGraves(emptySpace);
        grav.addGraves(emptySpace);
        grav.addGraves(emptySpace);
        grav.addGraves(emptySpace);
        grav.addGraves(emptySpace);
        System.out.println(grav.getGraveyard_Array().toString());*/

            /*r[randomRow][randomColumn] = "[✞]";

            randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
            randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);

            while (gr[randomRow][randomColumn].equals("[✞]")) {
                randomRow = ThreadLocalRandom.current().nextInt(randomRowMin, randomRowMax + 1);
                randomColumn = ThreadLocalRandom.current().nextInt(randomColMin, randomColMax + 1);
                gr[randomRow][randomColumn] = "[✞]";
            }
            gr[randomRow][randomColumn] = "[✞]";
        }*/
        /*gr[1][4] = "[✞]";
        gr[2][1] = "[✞]";
        gr[3][0] = "[✞]";
        gr[4][3] = "[✞]";*/
        for (int k = 0; k < 5; k++) {
            grav.addGrave(gr);
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //gr[i][j] = "|✞|";
                System.out.print(gr[i][j] + "   ");
            }
            System.out.println();
        }
        //System.out.println(Arrays.deepToString(gr));

    }
}
