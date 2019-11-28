package Pokemoon;

import static Pokemoon.FileManager.s;
import static Pokemoon.FileManager.time;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * Pierce Miller 14872510
 */
public class Main {

    public static Scanner loadScreenscan = new Scanner(System.in);
    public static Scanner chooseMoonScan = new Scanner(System.in);
    public static FileManager overseer = new FileManager();
    public static chooseMoons choose = new chooseMoons();
    public static LoadScreen load = new LoadScreen();
    public static Player p = new Player();
    public static int options = 0;
    public static int starterMoon = 0;
    public static boolean isNumber = true;
    public static String moons = " - 1) Charmanda\n"
            + " - 2) Bulbasaw\n"
            + " - 3) Sqweretile\n";

    public static void game() throws IOException {

        int runAway = 0;
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);

        // Enemy variables
        String[] enemies = {"Fearroo", "Rayhorn", "Treekoo", "Torchick", "Sulamance", "Tiranita", "Genga"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        // Pet variables
        int moonHealth = 100;
        int moonAttackDmg = 50;
        int numHealPots = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; //percent

        boolean running = true;

        System.out.println("\n\t>Starting your game...");
        s.Delay(time);

        Pokemoon: //allows the game to continue 
        while (running) {

            System.out.println("--------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)]; //enemy health value is randomised from 0 - 75
            System.out.println("   # A wild " + enemy + " has appeared! #\n");

            //continue to loop through the game while the enemies health is greater than 0
            while (enemyHealth > 0) {

                String userChoice = p.getPetName() + "'s HP: " + moonHealth
                        + "\t" + enemy + "'s HP: " + enemyHealth
                        + "\n---------------------------\n"
                        + "- Options   | Information\n"
                        + "---------------------------\n"
                        + "- 1) Attack | Both " + enemy + " and " + p.getPetName() + " will fight each other.\n"
                        + "- 2) Potion | Your moon will drink a potion to give it some health\n"
                        + "- 3) Run    | Chicken out\n";

                System.out.println(userChoice);
                String input = scan.nextLine();

                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(moonAttackDmg); //randomise the players damage dealt value between 0 - 50
                    int damageTaken = rand.nextInt(enemyAttackDamage); //randomise the enemies damage dealt value between 0 - 25

                    enemyHealth -= damageDealt;
                    moonHealth -= damageTaken;

                    System.out.println("\t>" + p.getPetName() + " attacked " + enemy + " for " + damageDealt + " damage");
                    System.out.println("\t>" + p.getPetName() + " recieved " + damageTaken + " in retaliation\n");
                    p.score++;

                    if (moonHealth < 1) {
                        System.out.println("\t>You have taken too much damage, you are too weak to go on");
                        break;
                    }
                } else if (input.equals("2")) {

                    if (numHealPots > 0) {
                        moonHealth += healthPotionHealAmount; //add health potion value to player health
                        numHealPots--; //take away one from health 

                        System.out.println("\t>" + p.getPetName() + " drank a potion and healed " + healthPotionHealAmount
                                + "\n\t>" + p.getPetName() + " now has " + moonHealth
                                + "\n\t>You now have: " + numHealPots + " pots left\n");
                    } else {
                        System.out.println("You have ran out of pots, defeat a pokemoon for a chance to obtain one");
                    }

                } else if (input.equals("3")) {

                    System.out.println("\t>You run away from " + enemy);
                    runAway++;

                    if (runAway == 2) {
                        System.out.println("\t>You can run away, only one more time");
                    }

                    if (runAway == 3) {
                        System.out.println("\t>You ran away to much");

                        BufferedReader reader = new BufferedReader(new FileReader(p.username + ".txt"));

                        String line = "", oldtext = "";
                        while ((line = reader.readLine()) != null) {
                            oldtext += line + "\n";
                        }

                        String newtext = oldtext.replace("0", Integer.toString(p.score));

                        //Individual File
                        FileWriter writer = new FileWriter(p.username + ".txt");
                        writer.write(newtext);

                        //Leader board
                        FileWriter fw = new FileWriter("LeaderBoard.txt", true);
                        fw.write(p.username + " : " + p.score + "\n");
                        
                        writer.close();
                        fw.close();
                        
                        System.out.println("\t\n~~~~THANK YOU " + p.username + " YOU SCORED: " + p.score + " POINT(S), PLEASE COME AGAIN~~~~");
                        System.exit(0);

                    }

                    continue Pokemoon;

                } else {
                    System.out.println("\tInvalid Command");
                }
            }
            if (moonHealth < 1) {
                System.out.println("\t>" + p.getPetName() + " comes back to you weak from fighting " + enemy);
                break;
            }
            System.out.println("--------------------------------------\n"
                    + " # " + enemy + " was defeated! # \n"
                    + p.getPetName() + " has " + moonHealth + " HP left\n");

            if (rand.nextInt(100) < healthPotionDropChance) {
                numHealPots++;
                System.out.println(enemy + " dropped a health pot\n"
                        + "You now have " + numHealPots + "health pot(s)");
            }

            String whatNow = ("--------------------------------------\n"
                    + " What now?\n"
                    + " - 1) Continue\n"
                    + " - 2) Exit\n");

            System.out.println(whatNow);
            String input = scan.nextLine();

            while (!input.equals("1") && !input.equals("2")) {

                System.out.println("invalid command");
                input = scan.nextLine();
            }

            if (input.equals("1")) {
                System.out.println("\t>You continue your adventure.");
                s.Delay(time);
            } else if (input.equals("2")) {
                System.out.println("\t>Not everyone can handle it.");
                break;
            }
        }

        BufferedReader reader = new BufferedReader(new FileReader(p.username + ".txt"));

        String line = "", oldtext = "";
        while ((line = reader.readLine()) != null) {
            oldtext += line + "\n";
        }

        String newtext = oldtext.replace("0", Integer.toString(p.score));

        //Individual file
        FileWriter writer = new FileWriter(p.username + ".txt");
        writer.write(newtext);
        writer.close();

        //Saves all users to one file and displays all the scores 
        FileWriter fw = new FileWriter("ScoreBoard.txt", true);
        fw.write(p.username + " : " + p.score + "\n");
        fw.close();
        
        Scanner leader = new Scanner(new FileReader("ScoreBoard.txt"));
        ArrayList<String> board = new ArrayList<>();

        while (leader.hasNextLine()) {
             board.add(leader.nextLine());
        }
        
        System.out.println("\t\n~~~~THANK YOU " + p.username + " YOU SCORED: " + p.score + " POINT(S), PLEASE COME AGAIN~~~~");

    }

    public static void main(String args[]) throws IOException {

        load.loadScreen();

        if (options == 1) {
            choose.chooseMoons();
        }

        game();

    }
}
