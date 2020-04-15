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
            
            System.out.println("\nLine : " + row + "\n====================");
            success = parser.parseLine(line,row,0);
            
            
            
            row++;
        }
        // PASS TWO
        Grammar g = new Grammar();
        g.validate_grammar();
        
        System.out.println("symbol size " + Symbol.list.size());
        for(int i =0; i < Symbol.list.size();i++){
            System.out.println("Symbol " + Symbol.list.get(i).identifierType);
        }

    }

}
