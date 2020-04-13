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
        
        // PASS ONE
        for(String line : reader.text){
            
            System.out.println("Line : " + row + "\n====================");
            success = parser.parseLine(line,row,0);
            
            
            
            row++;
        }
        // PASS TWO
        Grammar g = new Grammar();
        g.validate_grammar();
        


    }

}
