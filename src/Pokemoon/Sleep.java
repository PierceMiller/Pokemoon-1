package Pokemoon;

/**
 *
 * Pierce Miller
 * 14872510
 */

public class Sleep {
    
    public static void Delay(long time){
        try{
            Thread.sleep(time);
        }catch(Exception e){
            e.printStackTrace();
        }
    } 
}


