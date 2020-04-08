/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

import java.awt.Color;
import static java.awt.Color.getColor;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Matthew Fischer
 */

public class TinyJavaApp {
    
    private LexicalAnalizer analizer;
    //String data;
    private Scanner input;

    
    public TinyJavaApp(){
        analizer = new LexicalAnalizer();
        
        
    }
        
    public void readFile(){
        
        try{
            input = new Scanner(new File("code.txt"));
        }catch(Exception e){
            System.out.println("Error reading file");
        }
      
    }
    
    public String getLine(){
        String data = input.nextLine();
        return data;
    }
    
    
    public static void main(String[] args) {
        TinyJavaApp p = new TinyJavaApp();
        
        String line;
        
        while(p.input.hasNext()){
            p.readFile();
            line = p.getLine();
        
            if(p.analizer.program  == null)
                p.analizer.searchProgram(line);
            else
                p.analizer.expression(line);
        
        }

    }

}
