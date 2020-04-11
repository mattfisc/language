/*
 * By:     Matthew Fischer
 * Date:   4/10/2020
 */
package minijava;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Matthew Fischer
 */
public class ReadInput {
    
    ArrayList<String> text;
    Scanner input;
    int row;

    // CONSTRUCTOR
    public ReadInput(){
        row = 0;        
        try{
            input = new Scanner(new File("code.txt"));
        }catch(Exception e){
            System.out.println("error reading file");
        }
        
        text = new ArrayList();
        
    }
    
    // GET FILE PROGRAM
    public void reader(){
        int count = 0;
        while(input.hasNextLine()){
            count++;
            text.add(input.nextLine());
        }
        
    }
    
    // GET ONE LINE
    public String getLine(){
        if(text.isEmpty()){
            return null;
        }
        
        String x = text.get(row);
        row++;

        return x;
        
    }
}
