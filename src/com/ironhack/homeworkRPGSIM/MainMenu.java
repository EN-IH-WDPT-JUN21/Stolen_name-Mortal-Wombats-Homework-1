package com.ironhack.homeworkRPGSIM;


import javax.swing.text.AttributeSet;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class MainMenu {

    // **** BATTLE PROPERTIES DECLARATION ****
    private static int ch = 0; //stores the user's option choices
    public static int playerNumber = 1; //stores the player in 2 player game mode
    private static final Scanner scanner = new Scanner(System.in); //Scanner

    static ArrayList<Character> party1 = new ArrayList<>(); //player1 party
    static ArrayList<Character> party2 = new ArrayList<>(); //player2 party


    // **** GRAVEYARD PROPERTIES DECLARATION ****
    static private final String eg = "[\uD83D\uDD73]";  // String icon for empty grave

    static List<String> party1Died = new ArrayList<>(); //list of dead characters on player1's party
    static List<String> party2Died = new ArrayList<>(); //list of dead characters on player2's party

    // Creates array for empty graveyard
    static String[][] gr =
            {
                    {eg, eg, eg, eg, eg, eg},
                    {eg, eg, eg, eg, eg, eg},
                    {eg, eg, eg, eg, eg, eg},
                    {eg, eg, eg, eg, eg, eg},
                    {eg, eg, eg, eg, eg, eg},
            };

    // Creates the graveyard caption
    static String[] legend = {"  |    ======================",
            "  |     [\uD83D\uDD73] - EMPTY GRAVE",
            "  |     [💀] - PARTY 1 GRAVE",
            "  |     [\uD83D\uDC7B] - PARTY 2 GRAVE",
            "  |    ======================"};



    // **** MAIN MENU METHOD  - USED TO INITIALISE THE GAME ****
    public static void mainMenu() throws IOException {

        System.out.println("\n" +
                "**********************************************************\n" +
                "**********~~~~~~~.~~~~~~~~~~~~~~~~~~~~~,=~~~~~~***********\n" +
                "*********~~~~~.~~~~~~~~~~~~~~~~~~~~~~~~~.~~~~~************\n" +
                "**********~~~.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.~~~***********\n" +
                "**********~.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.~***********\n" +
                "**********~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~***********\n" +
                "**********~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~***********\n" +
                "*********~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**********\n" +
                "*********~~~~~.......~~~~~~~~~~~~~~~~~.......~~~~*********\n" +
                "********:~~~~~~~~....~~~~~~~~~~~~~~~~~....~~~~~~~~********\n" +
                "*******~~~~~~~~~~~..~~~~~~~~~~~~~~~~~..~~~~~~~~~~~~*******\n" +
                "******~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~******\n" +
                "******~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*****\n" +
                "******~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*****\n" +
                "******~~~~~~~~~~~~~~~.................~~~~~~~~~~~~~~~*****\n" +
                "*******,=~~~~~~~~~~~~~~.............~~~~~~~~~~~~~~~,******\n" +
                "************~~~~~~~~~~~~~.........~~~~~~~~~~~~~***********\n" +
                "****************,=~~~~~~~~~:...:~~~~~~~~~~,***************\n" +
                "**********************************************************\n" );
        //System.out.println();
        System.out.println ("*********************************************************");
        System.out.println ("*************** WELCOME TO MORTAL WOMBATS ***************");
        System.out.println ("*********************************************************");
        System.out.println();
        System.out.println ("Choose a game mode:");

        do {
            System.out.println("1 - 1 Player");
            System.out.println("2 - 2 Players");
            System.out.println("3 - Generate Random Game");

            getsChoice(); //Gets the player choice and stores it in ch property

            //Sends the user to the method chosen
            switch (ch) {
                case 1 -> onePlayerGameMode();
                case 2 -> {
                    twoPlayerGameMode();
                }
                case 3 -> {
                    generateRandomGame();
                }
                default -> System.out.println("Please, choose a valid game mode.\n");
            }
        } while (ch != 1 && ch != 2 && ch != 3);

        scanner.close();
        System.exit(0);
    }

    // **** ONE PLAYER MENU ****
    public static void onePlayerGameMode() throws IOException {
        System.out.println ("\n*********************************************************");
        System.out.println ("\nYou've chosen 1 Player Mode.\n(You will be competing against a random generated party).");
        System.out.println ("\nCreate your Party:");

        do {
            System.out.println ("1 - Create all the characters");
            System.out.println ("2 - Generate a random party");
            System.out.println ("3 - Import party");
            System.out.println ("0 - Return to Main Menu");

            getsChoice(); //Gets the player choice and stores it in ch property

            //Sends the user to the method chosen
            switch (ch) {
                case 1:
                    // **** USER CHOOSES NAMES AND STATS FOR EACH CHARACTER ****
                    createOwnParty();
                    wantsToExportParty(party1, 1);
                    System.out.println(party1);
                    // **** CREATES A RANDOM PARTY THE SAME SIZE AS PLAYER 1'S PARTY ****
                    generateRandomParty(party1, party2);
                    break;
                case 2:
                    // **** CREATES A RANDOM PARTY OF A RANDOM SIZE FOR PLAYER 1 ****
                    generateRandomParty(party1);
                    wantsToExportParty(party1, 1);
                    // **** CREATES A RANDOM PARTY THE SAME SIZE AS PLAYER 1'S PARTY ****
                    generateRandomParty(party1, party2);
                    wantsToExportParty(party2, 2);
                    break;
                case 3:
                    // **** USER IMPORTS PARTY FROM CSV FILE ****
                    importParty(party1, 1);
                    // **** CREATES A RANDOM PARTY THE SAME SIZE AS PLAYER 1'S PARTY ****
                    generateRandomParty(party1, party2);
                    // **** THIS WILL CREATE A RANDOM PARTY THE SAME SIZE AS PLAYER 1'S PARTY ****
                    wantsToExportParty(party1, 1);
                    break;
                case 0:
                    mainMenu();
                default:
                    System.out.println("Please, choose a valid option.\n");
                    break;
            }
        } while (ch != 1 && ch != 2 && ch !=3);

        System.out.println("\n************** THE BATTLE IS ABOUT BEGIN! ************** ");
        System.out.println("Press Enter to Start the Battle.");
        scanner.nextLine();


        Battle.battle(party1, party2, gr, party1Died, party2Died);
        // Generates and shows graveyard
        Battle.generateGraveyard(party1, party2, gr, legend, party1Died, party2Died);
    }

    // **** TWO PLAYER GAME MODE ****
    public static void twoPlayerGameMode() throws IOException {

        //Prevents that the message is shown befrore Player2's Menu
        if (playerNumber == 1) {
            System.out.println ("\n*********************************************************");
            System.out.println ("\nYou've chosen 2 Player Mode.");
        }

        //Shows the Menu for Player 1 and repeats for Player 2
        while(playerNumber < 3) {
            System.out.println("\n+++ MENU PLAYER " + playerNumber + " +++\n");
            System.out.println("Create your party:");

            do {
                System.out.println ("1 - Create all the characters");
                System.out.println ("2 - Generate a random party");
                System.out.println ("3 - Import party");
                System.out.println ("0 - Return to Main Menu");

                getsChoice(); //Gets the player choice and stores it in ch property

                switch (ch) {
                    case 1:
                        // **** USER CHOOSES NAMES AND STATS FOR EACH CHARACTER ****
                        if(playerNumber == 1) {
                            createOwnParty();
                            wantsToExportParty(party1, 1);
                            playerNumber++;
                        }else {
                            createOwnParty();
                            wantsToExportParty(party2, 2);
                            playerNumber++;
                        }
                        break;
                    case 2:
                        if(playerNumber == 1) {
                            // **** CREATES A RANDOM PARTY OF A RANDOM SIZE FOR PLAYER 1 ****
                            generateRandomParty(party1);
                            wantsToExportParty(party1, 1);
                        }else {
                            // **** CREATES A RANDOM PARTY THE SAME SIZE AS PLAYER 1'S PARTY ****
                            generateRandomParty(party1, party2);
                            wantsToExportParty(party2, 2);
                        }
                        playerNumber++;
                        break;
                    case 3:
                        // **** USER IMPORTS PARTY FROM CSV FILE ****
                        if(playerNumber == 1) {
                            importParty(party1, playerNumber);
                            wantsToExportParty(party1, 1);
                            playerNumber++;
                        }else {
                            importParty(party2, playerNumber);
                            wantsToExportParty(party2, 2);
                            playerNumber++;
                        }
                        break;
                    case 0:
                        mainMenu();
                    default:
                        System.out.println("Please, choose a valid option.\n");
                        break;
                }
            } while (ch != 1 && ch != 2 && ch !=3);
        }

        System.out.println("\n******** THE BATTLE IS ABOUT BEGIN! ******** ");
        System.out.println("Press Enter to Start the Battle.");
        scanner.nextLine();

        Battle.battle(party1, party2, gr, party1Died, party2Died);
        // Generate and show graveyard
        Battle.generateGraveyard(party1, party2, gr, legend, party1Died, party2Died);
    }

    // **** GENERATES RANDOM PARTIES FOR BOTH PLAYERS AND STARTS THE BATTLE ****
    private static void generateRandomGame() {
        playerNumber = 0;
        generateRandomParty(party1);
        generateRandomParty(party1, party2);
        Battle.battle(party1, party2, gr, party1Died, party2Died);
        // Generates and shows graveyard
        Battle.generateGraveyard(party1, party2, gr, legend, party1Died, party2Died);

    }

    private static void createOwnParty() throws IOException {

        ArrayList<Character> party = new ArrayList<>();

        System.out.println ("\n*********************************************************");
        System.out.println ("\nYou've chosen to create all the characters.");


        do {
            System.out.println("\nWhat type of Character would you like to create?");
            System.out.println("1 - Warrior");
            System.out.println("2 - Wizard");

            do {
                getsChoice(); //Gets the player choice and stores it in ch property

                switch (ch) {
                    case 1:
                        Warrior warrior = new Warrior(); // CREATES NEW WARRIOR OBJECT WITH ONLY ID VALUE
                        warrior.customiseWarrior(); // CALLS CUSTOMISE METHOD TO MANUALLY INPUT STATS
                        party.add(warrior); // ADDS NEW WARRIOR TO PARTY
                        System.out.println("\nBrave new Warrior " + warrior.getName() + " joins the party! They have a HP of " + warrior.getHp() +
                                ", a stamina of " + warrior.getStamina() + " and a strength of " + warrior.getStrength() + ".");
                        break;
                    case 2:
                        Wizard wizard = new Wizard(); // CREATES NEW WIZARD OBJECT WITH ONLY ID VALUE
                        wizard.customiseWizard(); // CALLS CUSTOMISE METHOD TO MANUALLY INPUT STATS
                        party.add(wizard); // ADDS NEW WARRIOR TO PARTY
                        System.out.println("\nWise old Wizard " + wizard.getName() + " joins the party! They have a HP of " + wizard.getHp() +
                                ", a mana pool of " + wizard.getMana() + " and an intelligence of " + wizard.getIntelligence() + ".");
                        break;
                    default:
                        System.out.println("Please, choose a valid number.\n");
                        break;
                }
            }while(ch != 1 && ch != 2);


            System.out.println("\n************\n");
            System.out.println("Do you want to create a new character?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");

            do {
                getsChoice(); //Gets the player choice and stores it in ch property

                switch (ch) {
                    case 1:
                    case 2:
                        break;
                    default:
                        System.out.println("Please, choose a valid option.\n");
                        break;
                }
            }while(ch != 1 && ch != 2);

        } while (ch != 2);

        System.out.println("Your party was created with success!");


        // **** WILL ASSIGN PARTY TO EITHER PLAYER 1 (PARTY 1) OR PLAYER 2
        if(party1.isEmpty()) {
            party1 = party;
        }
        else{
            party2 = party;
        }



    }

    // **** RANDOMLY GENERATES PARTY SAME SIZE AS PARTY 1
    private static void generateRandomParty(ArrayList<Character> party1, ArrayList<Character> party2) {
        for (int i = 0; i < party1.size(); i++) {
            int randomNum = new Random().nextInt(2);
            if (randomNum == 0) {
                Wizard wizard1 = new Wizard(randomName(), 50 + new Random().nextInt(50), 10 +
                        new Random().nextInt(41), 1 + new Random().nextInt(50));
                party2.add(wizard1);
            } else {
                Warrior warrior1 = new Warrior(randomName(), 100 + new Random().nextInt(100), 10 +
                        new Random().nextInt(41), 1 + new Random().nextInt(10));
                party2.add(warrior1);
            }
        }

        System.out.println("\nPlayer 2's Party created with these brave adventurers: ");
        for (Character character : party2) {
            System.out.println("- " + character.toString());
        }
    }

    // **** RANDOMLY GENERATES NEW PARTY WITH A RANDOM PARTY SIZE
    private static void generateRandomParty(ArrayList<Character> party1) {
        int randomPartySize = 1 + new Random().nextInt(5); // MAX PARTY SIZE SET TO 5
        for (int i = 0; i < randomPartySize; i++) {
            int randomNum = new Random().nextInt(2);
            if (randomNum == 0) {
                Wizard wizard1 = new Wizard(randomName(), 50 + new Random().nextInt(50), 10 + new
                        Random().nextInt(41), 1 + new Random().nextInt(50));
                party1.add(wizard1);
            } else {
                Warrior warrior1 = new Warrior(randomName(), 100 + new Random().nextInt(100), 10 +
                        new Random().nextInt(41), 1 + new Random().nextInt(10));
                party1.add(warrior1);
            }
        }
        System.out.println("\nPlayer 1's Party created with these brave adventurers:");
        for (Object o : party1) {
            System.out.println("- " + o.toString());
        }

    }

    //**** GETS INPUT FROM USER AND STORES IT IN CH PROPERTY ****
    private static void getsChoice() {

        do {
            try {
                ch = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                ch = -1;
                System.out.println("Please, choose a valid option.\n");
                scanner.next();
            }
        }while (ch == -1 );

    }

    //**** ASKS THE USER IF THEY WANT TO EXPORT THEIR PARTY ****
    private static void wantsToExportParty(ArrayList<Character> party, int n) throws IOException {

        System.out.println("\nDo you want to export the party into a csv file so you can use it again in the future?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");

        do {
            getsChoice(); //Gets the player choice and stores it in ch property

            switch (ch) {
                case 1:
                    readToCSV(party, n);
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Please, choose a valid option.\n");
                    break;
            }
        }while(ch != 1 && ch != 2);


    }

    // **** RANDOM NAME GENERATOR ****
    private static String randomName(){
        String randomName;
        int randomNum = new Random().nextInt(20);

        ArrayList<String> names = new ArrayList<>();
        names.add("Grim Weeper");
        names.add("I.T.");
        names.add("John Weak");
        names.add("Ironing Man");
        names.add("Jason Burned");
        names.add("Still Bill");
        names.add("Florist Gump");
        names.add("Ellen Replay");
        names.add("Katniss Nevergreen");
        names.add("San Holo");
        names.add("Gary Poter");
        names.add("Determinator");
        names.add("Scooby-Undo");
        names.add("Sherlock Homeless");
        names.add("Mr. Alt+T");
        names.add("Conan The Barberian");
        names.add("Ciderella");
        names.add("Joan of Dark");
        names.add("Java the Hut");
        names.add("Minimus Decimus Meridius");

        randomName = names.get(randomNum);
        if(party1.toString().contains(randomName) || party2.toString().contains(randomName)){
            do {
                randomName = randomName.concat(" Jr");
            }while(party1.toString().contains(randomName) || party2.toString().contains(randomName));
        }
        return randomName;
    }

    //**** IMPORT PARTY FROM CSV FILE ***
    private static void importParty(ArrayList<Character> party, int n) {
        List<Character> impCharacters = readFromCSV("ExportedParty"+n+".csv", n);

        for (Character c : impCharacters) {
            party.add(c);
        }
    }

    //**** READ CSV FILE ****
    private static List<Character> readFromCSV(String s, int n) {
        List<Character> impCharacters = new ArrayList<>();
        Path path = Paths.get("ExportedParty"+n+".csv");

        try(BufferedReader read = Files.newBufferedReader(path, StandardCharsets.US_ASCII)){
            String line = read.readLine();
                while (line != null) {
                    String[] impStats = line.trim().split(",");
                    Character ch1 = createCharacter(impStats);
                    impCharacters.add(ch1);
                    line = read.readLine();
                   }
        } catch (IOException e){
            e.printStackTrace();
        }
        return impCharacters;
    }

    //**** CREATE WIZARD FROM STRING ARRAY ****
    private static Character createCharacter(String[] metadata) {
        int type = Integer.parseInt(metadata[0]);
        //int id = Integer.parseInt(metadata[1]);
        String name = metadata[1];
        int hp = Integer.parseInt(metadata[2].trim());
        int mana = Integer.parseInt(metadata[3].trim());
        int intelligence = Integer.parseInt(metadata[4].trim());
        int stamina = Integer.parseInt(metadata[3].trim());
        int strength = Integer.parseInt(metadata[4].trim());

        //to ensure the same limits apply regardless of the data in the import file
        if (type == 2) {
            if (hp >= 50 && hp <= 100) {
                hp = hp;
            } else if (hp < 50) {
                hp = 50;
            } else {
                hp = 100;
            }
        } else if(type == 1){
            if (hp >= 100 && hp <= 200) {
                hp = hp;
            } else if (hp < 100) {
                hp = 100;
            } else {
                hp = 200;
            }
        }

        if (mana >= 10 && mana <= 50) {
            mana = mana;
        } else if (mana < 10) {
            mana = 10;
        } else {
            mana = 50;
        }

        if (intelligence >= 1 && intelligence <= 50) {
            intelligence = intelligence;
        } else if (intelligence < 1) {
            intelligence = 1;
        } else {
            intelligence = 50;
        }

        if (stamina >= 10 && stamina <= 50) {
            stamina = stamina;
        } else if (stamina < 10) {
            stamina = 10;
        } else {
            stamina = 50;
        }

        if (strength >= 1 && strength <= 10) {
            strength = strength;
        } else if (strength < 1) {
            strength = 1;
        } else {
            strength = 10;
        }

        if(type == 2) {
            return new Wizard(name, hp, mana, intelligence);
        } else {
            return new Warrior(name, hp, stamina, strength);
        }
    }

    //**** EXPORT PARTY TO CSV FILE ****
    public static void readToCSV(ArrayList<Character> party, int n) throws IOException {
        FileWriter writer = new FileWriter("ExportedParty"+n+".csv", false);
        for(int r = 0; r < party.size(); r++){
            String str = (party.get(r).toString());
            if(str.contains("Warrior")){
                str = str.replace("Warrior: ","");
                str = str.replace("| HP:",",");
                str = str.replace("| Stamina:",",");
                str = str.replace("| Strength:",",");
                System.out.println(str);
                str = "1,"+str;
            } else if (str.contains("Wizard")){
                str = str.replace("Wizard: ","");
                str = str.replace("| HP: ",",");
                str = str.replace("| Mana: ",",");
                str = str.replace("| Intelligence: ",",");
                str = "2," + str;
            }
            writer.append(str);
            writer.append("\n");
        }
        writer.close();

        System.out.println("\nParty exported with success.");
        System.out.println("\n-----------------");
    }

}
