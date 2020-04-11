/*
 * By:     Matthew Fischer
 * Date:   4/10/2020
 */
package minijava;

import java.util.function.Consumer;

/**
 *
 * @author Matthew Fischer
 */

public class TinyJavaApp {
    
        
  
    public static void main(String[] args) {
        
        ReadInput reader = new ReadInput();
        reader.reader();
        
        Parser parser = new Parser();
        
        boolean success = true;
        int row = 0;
        
        for(String line : reader.text){

            success = parser.parseLine(row,line);
            if(success == true){
                System.out.println("row " + row + " code line:  IS VALID!!!\n" + line + "\n");
            }
            else{
                System.out.println("ERROR on row " + row + " code line:\n" + line);
            }
            
            
            // RESET NEXT ROW
            System.out.println("");// SPACE
            row++;
        }
        

        
        

    }

}
