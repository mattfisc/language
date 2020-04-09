/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Matthew Fischer
 */
public class ReadInput {
    
    String[] programText;
    
    public int line;
    Scanner input;
    
    public ReadInput(){
        try{
            input = new Scanner(new File("code.txt"));
        }catch(Exception e){
            System.out.println("error reading file");
        }
        
        int index = 0;
        
        while(input.hasNextLine()){
            programText[index] = input.nextLine();
        }
    }
    
    // sent one line of code until null
    public String getLine(){
        return programText[line];
        
    }
}
