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
    
    
    public TinyJavaApp(){
        
    }
    
    public static void main(String[] args) {
        LinkedList<Symbol> sList;
        LinkedList<Token> tList;
        
        
        ReadInput reader = new ReadInput();
        reader.reader();
        reader.getLine();
        
        for(int i = 0; i <= reader.text.size(); i++){
            System.out.println( reader.getLine() );
        }
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
