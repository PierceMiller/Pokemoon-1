package Pokemoon;

import static Pokemoon.FileManager.p;
import static Pokemoon.Main.chooseMoonScan;
import static Pokemoon.Main.isNumber;
import static Pokemoon.Main.moons;
import static Pokemoon.Main.starterMoon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * Pierce Miller
 * 14872510
 */

public class chooseMoons {
    
    public static void chooseMoons() throws IOException {

        System.out.println("\n---What moon would you like to start off with?---");

        do { //loop this if input is not valid
            do { //loop if isNumber is not changed. 
                System.out.println(moons);

                if (chooseMoonScan.hasNextInt()) {
                    starterMoon = chooseMoonScan.nextInt();
                    isNumber = true;
                } else {
                    System.out.println("\nInvalid Input! Select 1,2 or 3");
                    isNumber = false;
                    chooseMoonScan.next();
                }
            } while (!isNumber);

            switch (starterMoon) {
                case 1:

                    BufferedReader reader = new BufferedReader(new FileReader(p.username + ".txt"));

                    String line = "",
                     oldtext = "";
                    while ((line = reader.readLine()) != null) {
                        oldtext += line + "\n";
                    }

                    String newtext = oldtext.replace("blank", "Charmanda");

                    FileWriter writer = new FileWriter(p.username + ".txt");
                    writer.write(newtext);
                    writer.close();

                    p.getPetName();
                    p.setPetName("Charmanda");

                    break;
                case 2:

                    BufferedReader reader2 = new BufferedReader(new FileReader(p.username + ".txt"));

                    String line2 = "",
                     oldtext2 = "";
                    while ((line2 = reader2.readLine()) != null) {
                        oldtext2 += line2 + "\n";
                    }

                    String newtext2 = oldtext2.replace("blank", "Bulbasaw");

                    FileWriter writer2 = new FileWriter(p.username + ".txt");
                    writer2.write(newtext2);
                    writer2.close();

                    p.getPetName();
                    p.setPetName("Bulbasaw");

                    break;
                case 3:

                    BufferedReader reader3 = new BufferedReader(new FileReader(p.username + ".txt"));

                    String line3 = "",
                     oldtext3 = "";
                    while ((line3 = reader3.readLine()) != null) {
                        oldtext3 += line3 + "\n";
                    }

                    String newtext3 = oldtext3.replace("blank", "Sqweretile");

                    FileWriter writer3 = new FileWriter(p.username + ".txt");
                    writer3.write(newtext3);
                    writer3.close();

                    p.getPetName();
                    p.setPetName("Sqweretile");

                    break;
            }
        } while (starterMoon < 0 || starterMoon > 3);
    }   
}
