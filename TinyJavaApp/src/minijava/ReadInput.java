/*
 * By:     Matthew Fischer
 * Date:   
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

    public ReadInput(){
        try{
            input = new Scanner(new File("code.txt"));
        }catch(Exception e){
            System.out.println("error reading file");
        }
  

        text = new ArrayList();
        
    }
    
    public void reader(){
        
        int count = 0;
        while(input.hasNextLine()){
            count++;
            text.add(input.nextLine());
        }
        
    }
    
    // sent one line of code until null
    public String getLine(){
        if(text.isEmpty()){
            return null;
        }
        
        String x = text.get(0);
        text.remove(0);

        return x;
        
    }
}
