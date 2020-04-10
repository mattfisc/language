/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

import java.awt.Color;
import static java.awt.Color.getColor;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Matthew Fischer
 */

public class TinyJavaApp {
    
        
  
    public static void main(String[] args) {
      
        
        
//        ReadInput reader = new ReadInput();
//        reader.reader();
//        for(int i = 0; i < reader.text.size(); i++){
//            System.out.println( reader.getLine() );
//        }
//        
        Token token = new Token();
        Token.list.add(token);
        
        Symbol.list.add(new Symbol("x"));
        System.out.println(Symbol.findSymbol("y"));
       
         
//        Parser parser;
//        
//        boolean success = true;
//        
//        while(success){
//            String line= " ";
//            if(line == null){
//                
//            }
//        }

    }

}
