package com.ironhack.homeworkRPGSIM;


import java.util.*;


class MainMenu {

    // **** INITIAL VARIABLE CREATION ****
    private static int ch =0;
    public static int playerNumber = 1;
    private static final Scanner scanner = new Scanner(System.in);
    // **** THESE PARTIES ARE REFERENCED THROUGHOUT ****
    static ArrayList<Character> party1 = new ArrayList<>();
    static ArrayList<Character> party2 = new ArrayList<>();
    // **** GRAVEYARD HASHMAP ****
    static HashMap<Integer, Character> graveyard = new HashMap<>();

    // **** MAIN MENU METHOD  - USED TO INITIALISE THE GAME ****
    public static void mainMenu() {

        System.out.println ("*************** WELCOME TO MORTAL WOMBATS ***************");
        System.out.println();
        System.out.println ("Choose a game mode:");

        do {
            System.out.println("1 - 1 Player");
            System.out.println("2 - 2 Players");
            System.out.println("3 - Generate Random Game");


            getsChoice();


            switch (ch) {
                case 1 -> {
                    System.out.println("You chose 1 Player\n");
                    onePlayerGameMode();
                }
                case 2 -> {
                    System.out.println("You chose 2 Players\n");
                    twoPlayerGameMode();
                }
                case 3 -> {
                    System.out.println("You chose Random Game\n");
                    generateRandomGame();
                }
                default -> System.out.println("Please, choose a valid game mode.\n");
            }
        } while (ch != 1 && ch != 2 && ch != 3);

    }

    // **** ONE PLAYER MENUS ****
    public static void onePlayerGameMode() {
        System.out.println ("******************************");
        System.out.println ("\nDo you want to create your own party or generate a random one?");

        do {
            System.out.println ("1 - Create my own party");
            System.out.println ("You will choose your party and your opponent's will be random.");
            System.out.println ("2 - Generate a random party");
            System.out.println (" Both your and your opponent's parties will be random.");
            System.out.println ("3 - Import party");
            System.out.println ("You will import your party and your opponent's will be random.");
            System.out.println ("0 - Return to Main Menu");

            getsChoice();

            switch (ch) {
                case 1:
                    createOwnParty(); //addCharactersToParties(createOwnParty());
                    generateRandomParty(party1, party2); //addCharactersToParties(generateRandomParty());
                    break;
                case 2:
                    generateRandomParty(party1); //addCharactersToParties(generateRandomParty());
                    generateRandomParty(party1, party2); //addCharactersToParties(generateRandomParty());
                    break;
                case 3:
                    importParty(); //addCharactersToParties(importParty());
                    generateRandomParty(party1, party2); //addCharactersToParties(generateRandomParty());
                case 0:
                    mainMenu();
                default:
                    System.out.println("Please, choose a valid game mode.\n");
                    break;
            }
        } while (ch != 1 && ch != 2 && ch != 0);

        System.out.println("******** THE BATTLE IS ABOUT BEGIN! ******** ");
        Battle.battle(party1, party2, graveyard);
    }

    // **** TWO PLAYER GAME MODE ****
    public static void twoPlayerGameMode() {
        while(playerNumber < 3) {
            System.out.println("******************************");
            System.out.println("PLAYER " + playerNumber);
            System.out.println("\nDo you want to create your own party or generate a random one?");

            do {
                System.out.println("1 - Create my own party");
                System.out.println("2 - Generate a random party");
                System.out.println("3 - Import party");
                System.out.println("0 - Return to Main Menu");

                getsChoice();

                switch (ch) {
                    case 1:
                        createOwnParty(); //addCharactersToParties(createOwnParty());
                        playerNumber++;
                        break;
                    case 2:
                        if(playerNumber == 1) {
                            // **** THIS WILL CREATE A RANDOM PARTY OF A RANDOM SIZE FOR PLAYER 1 ****
                            generateRandomParty(party1); //addCharactersToParties(generateRandomParty());
                            playerNumber++;
                        }else {
                            // **** THIS WILL CREATE A RANDOM PARTY THE SAME SIZE AS PLAYER 1'S PARTY ****
                            generateRandomParty(party1, party2);
                            playerNumber++;
                        }
                        break;
                    case 3:
                        importParty(); //addCharactersToParties(importParty());
                        playerNumber++;
                    case 0:
                        mainMenu();
                    default:
                        System.out.println("Please, choose a valid game mode.\n");
                        break;
                }
            } while (ch != 1 && ch != 2 && ch != 0);
        }
        System.out.println("******** THE BATTLE IS ABOUT BEGIN! ******** ");
        Battle.battle(party1, party2, graveyard);
    }

    private static void createOwnParty() {

        char ch;

        ArrayList<Character> party = new ArrayList<>();

        System.out.println ("******************************");
        System.out.println ("\nFor each Character, you will have to define their names and their stats.");


        do {
            boolean validCharacter = false; // NEEDED TO HELP VALIDATE INPUTS

            System.out.println("\n What type of Character would you like to create?" +
                    "\n 1. Warrior" +
                    "\n 2. Wizard");

            while (!validCharacter) { // NEEDED TO VALIDATE INPUTS

                try {
                    int selection = scanner.nextInt();
                        if(selection == 1) {
                            Warrior warrior = new Warrior(); // CREATES NEW WARRIOR OBJECT WITH ONLY ID VALUE
                            warrior.customiseWarrior(); // CALLS CUSTOMISE METHOD TO MANUALLY INPUT STATS
                            party.add(warrior); // ADDS NEW WARRIOR TO PARTY
                            validCharacter = true;
                        }
                        else if (selection == 2){
                            Wizard wizard = new Wizard(); // CREATES NEW WIZARD OBJECT WITH ONLY ID VALUE
                            wizard.customiseWizard(); // CALLS CUSTOMISE METHOD TO MANUALLY INPUT STATS
                            party.add(wizard); // ADDS NEW WARRIOR TO PARTY
                            validCharacter = true;
                        }
                } catch (Exception er) {
                    System.out.println("Please input a valid number: ");
                    scanner.next();
                }
            }

            System.out.println("Do you want to create a new character? Y/N");
            ch = scanner.next().charAt(0);
        } while (ch == 'Y' || ch == 'y'); // THIS NEEDS FIXING AS ANY VALUE OUTSIDE OF Y OR y MOVED FORWARDS


        System.out.println("Your party was created with success!");

        // **** WILL ASSIGN PARTY TO EITHER PLAYER 1 (PARTY 1) OR PLAYER 2
        if(party1.isEmpty())
            party1 = party;
        else{
            party2 = party;
        }

    }
    // **** RANDOMLY GENERATES PARTY SAME SIZE AS PARTY 1
    private static void generateRandomParty(ArrayList party1, ArrayList party2) {
        for (int i = 0; i < party1.size(); i++) {
            int randomNum = new Random().nextInt(2);
            if (randomNum == 0) {
                Wizard wizard1 = new Wizard(randomName(), 50 + new Random().nextInt(101), 10 +
                        new Random().nextInt(51), 1 + new Random().nextInt(51));
                party2.add(wizard1);
            } else {
                Warrior warrior1 = new Warrior(randomName(), 100 + new Random().nextInt(201), 10 +
                        new Random().nextInt(51), 1 + new Random().nextInt(11));
                party2.add(warrior1);
            }

        }
        System.out.println("Party created with these brave adventurers: ");
        for(int i = 0; i < party2.size(); i++){
            System.out.println(party2.get(i).toString());
        }
    }
    // **** RANDOMLY GENERATES NEW PARTY WITH A RANDOM PARTY SIZE
    private static void generateRandomParty(ArrayList party1) {
        int randomPartySize = new Random().nextInt(5); // MAX PARTY SIZE SET TO 5
        for (int i = 0; i < randomPartySize; i++) {
            int randomNum = new Random().nextInt(2);
            if (randomNum == 0) {
                Wizard wizard1 = new Wizard(randomName(), 50 + new Random().nextInt(101), 10 + new
                        Random().nextInt(51), 1 + new Random().nextInt(51));
                party1.add(wizard1);
            } else {
                Warrior warrior1 = new Warrior(randomName(), 100 + new Random().nextInt(201), 10 +
                        new Random().nextInt(51), 1 + new Random().nextInt(11));
                party1.add(warrior1);
            }

        }
        System.out.println("Party created with these brave adventurers: \n");
        for(int i = 0; i < party1.size(); i++){
            System.out.println(party1.get(i).toString());
        }
    }

    private static void importParty() {
    }

    // **** GENERATES RANDOM PARTIES FOR BOTH PLAYERS AND STARTS THE BATTLE ****
    private static void generateRandomGame() {
        playerNumber = 0;
        generateRandomParty(party1);
        generateRandomParty(party1, party2);
        Battle.battle(party1, party2, graveyard);
    }

    private static void getsChoice() {

        try {
            ch = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please, choose a valid game mode.\n");
            scanner.next();
            getsChoice();
        }

    }

    // **** RANDOM NAME GENERATOR ****
    private static String randomName(){
        String randomName;
        int randomNum = new Random().nextInt(10);

        ArrayList<String> names = new ArrayList<>();
        names.add("JOOLS");
        names.add("JOPS");
        names.add("STOO");
        names.add("RJ");
        names.add("UBIK");
        names.add("CJ");
        names.add("CHRIS");
        names.add("PETE");
        names.add("TADGER");
        names.add("HECTOR");

        randomName = names.get(randomNum);
        if(party1.toString().contains(randomName) || party2.toString().contains(randomName)){
            return randomName.concat(" Jr");
        }else {
            return randomName;
        }
    }

}
