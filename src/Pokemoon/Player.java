package Pokemoon;

/**
 *
 * Pierce Miller
 * 14872510
 */

public class Player {
    
    public static int score = 0;
    public static String username;
    public static String petName = "blank";

    //User information
    public static String getUsername(){
        return username;
    }
    public static void setUsername(String username){
        Player.username = username;
    }
    public static int getScore(){
        return score;
    }
    public static void setScore(Integer score){
        Player.score = score;
    }
    
    //Pet Information
    public String getPetName(){
        return petName;
    }
    public static void setPetName(String getName){
        Player.petName = getName;
    }
}

