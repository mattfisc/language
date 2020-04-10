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
        
        for(String str : reader.text){
            parser.parseLine(str);
        }



        
        

    }

}
