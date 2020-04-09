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
        LinkedList<Symbol> sList = new LinkedList();
        LinkedList<Token> tList = new LinkedList();
        
        
        ReadInput reader = new ReadInput();
        reader.reader();
        for(int i = 0; i < reader.text.size(); i++){
            System.out.println( reader.getLine() );
        }
        
        
        
        Token t = new Token(TType.number,"int",10,10);
        tList.add(t);
        System.out.println(t.col);
        
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
