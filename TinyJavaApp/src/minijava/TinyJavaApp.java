/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

import java.awt.Color;
import static java.awt.Color.getColor;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Matthew Fischer
 */
public class TinyJavaApp {
    
    LexicalAnalizer analizer;
    //String data;
    
    public TinyJavaApp(){
        analizer = new LexicalAnalizer();
        
    }
        
    
    
    public static void main(String[] args) {
        TinyJavaApp p = new TinyJavaApp();

        String[] data = 
        {"PROGRAM p1 "
            + "BEGIN "
            + "int x = 1; "
            + "int y = 5; "
            + "x = y; "
            + "isnt z = 4; "
            + "END "};
        
        for (String data1 : data) {
            Scanner line = new Scanner(data1);
       
            if(!p.analizer.program  == false)
                p.analizer.searchProgram(line.nextLine());
            else{
                p.analizer.expression(line.nextLine());
            }
        }
        

        
    }

     
}
