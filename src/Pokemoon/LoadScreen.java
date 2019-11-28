package Pokemoon;

import static Pokemoon.Main.loadScreenscan;  
import static Pokemoon.Main.chooseMoonScan;
import static Pokemoon.Main.isNumber;
import static Pokemoon.Main.moons;
import static Pokemoon.Main.options;
import static Pokemoon.Main.overseer;
import static Pokemoon.Main.p;
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

public class LoadScreen {
    
    public static void loadScreen() throws IOException {

        String title = "~~~~WELCOME TO POKEMOON~~~~\n"
                + "---------------------------\n"
                + "- Options   | Information\n"
                + "---------------------------\n";

        System.out.println(title);
        do { //loop this if input is not valid
            do { //loop if isNumber is not changed. 
                String menu = "- 1) Create | Creates a new game\n"
                        + "- 2) Load   | Loads a new game\n"
                        + "- 3) Exit\n"
                        + "- Option: ";

                System.out.println(menu);

                if (loadScreenscan.hasNextInt()) {
                    options = loadScreenscan.nextInt();
                    isNumber = true;
                } else {
                    System.out.println("Invalid Input! Select 1,2 or 3");
                    isNumber = false;
                    loadScreenscan.next();
                }

            } while (!isNumber);

            switch (options) {
                case 1:
                    //creates a players own file
                    overseer.create(p);
                    break;
                case 2:
                    //loads a previously created file
                    overseer.load(p);
                    break;
                case 3:
                    //exits game
                    System.exit(0);
                    break;
            }
        } while (options < 0 || options > 3);
    }
    

    
}
