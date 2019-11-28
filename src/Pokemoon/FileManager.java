package Pokemoon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * Pierce Miller
 * 14872510
 */

public class FileManager {

    public static Sleep s = new Sleep();
    public static long time = 3000;
    public static Player p = new Player();

    public void create(Player p) throws IOException {

        Scanner scan = new Scanner(System.in);

        System.out.print("Username: ");
        String user = scan.next();
        p.setUsername(user);

        int score = p.getScore(); 
        String username = p.getUsername();
        String petname = p.getPetName();

        File f = new File(username + ".txt");

        if (f.exists()) {

            do {

                System.out.print("Differnet username: ");
                user = scan.next();
                p.setUsername(user);

                score = p.getScore();
                username = p.getUsername();
                petname = p.getPetName();

                f = new File(username + ".txt");
            } while (f.exists());
        }

        if (!f.exists()) {
            System.out.println("\n\t>Profile being created, creating game...");
            s.Delay(time);

            PrintWriter pw = new PrintWriter(new FileWriter(f));
            pw.println(username);
            pw.println(score);
            pw.println(petname);
            pw.close();
        }
    }

    public void save(Player p) throws IOException {

        int score = p.getScore();
        String username = p.getUsername();
        String petname = p.getPetName();

        File f = new File(username + ".txt");
        if (f.exists()) {
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(f));
                pw.println(username);
                pw.println(score);
                pw.println(petname);

                pw.close();
            } catch (FileNotFoundException nt) {
                System.out.println("File not found: " + nt.getLocalizedMessage());
            }
        }
    }
    
    public void load(Player p) throws IOException {

        int count = 0;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("- File name: ");
            String username = scan.next();
            File f = new File(username + ".txt");

            if (!f.exists()) {

                count++;

                if (count >= 5) {

                    int question = 0;
                    boolean isNumber = true;
                    Scanner attemptsScan = new Scanner(System.in);

                    System.out.println(">You have used up all your attempts");

                    do {
                        do {

                            System.out.println("\n>Did you want to create an account?: "
                                    + "---------------------------------------\n"
                                    + "\n - 1) Yes | Create a user"
                                    + "\n - 2) No  | Exit");

                            if (attemptsScan.hasNextInt()) {
                                question = attemptsScan.nextInt();
                                isNumber = true;
                            } else {
                                System.out.println("Invalid Input! Select 1,2 or 3");
                                isNumber = false;
                                attemptsScan.next();
                            }
                        } while (!isNumber);

                        switch (question) {
                            case 1:
                                create(p);
                                break;
                            case 2:
                                System.out.println("\n~~GOODBYE~~");
                                System.exit(0);
                                break;
                        }

                    } while (question <= 2 || question > 0);
                }

            }

            if (f.exists()) {
                System.out.println("\nFile found! Loading...");

                try {

                    int score;
                    String name;
                    String petname;

                    BufferedReader reader = new BufferedReader(new FileReader(f));

                    name = reader.readLine();
                    score = Integer.parseInt(reader.readLine());
                    petname = reader.readLine();

                    reader.close();

                    Player.setUsername(name);
                    Player.setScore(score);
                    Player.setPetName(petname);

                    System.out.println("\t>Username: " + p.getUsername());
                    s.Delay(time); //dramatic affect
                    System.out.println("\t>Score: " + p.getScore());
                    s.Delay(time);
                    System.out.println("\t>Pet Name: " + p.getPetName());
                    s.Delay(time);

                } catch (Exception e) {
                    e.printStackTrace();

                }
                break;
            }
        } while (count < 5);
    }
}
