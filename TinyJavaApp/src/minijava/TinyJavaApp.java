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
            
            System.out.println("row " + row + "\ncode line: " + line );
            System.out.println("=======================");
            success = parser.parseLine(line,row,0);
            
            
            // RESET NEXT ROW
            System.out.println("\n");// SPACE
            row++;
        }
        


    }

}
