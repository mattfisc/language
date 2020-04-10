/*
 * By:     Matthew Fischer
 * Date:   4/10/2020
 */
package minijava;

import java.util.StringTokenizer;

/**
 *
 * @author Matthew Fischer
 */
public class Parser {
    
    public Parser(){
        
    }
    
    public void parseLine(String line){
        // parse one line, until error, or end
        StringTokenizer token = new StringTokenizer(line," ");
        
        
        //System.out.println(token.nextToken());
        //System.out.println(token.nextToken());
        while(token.hasMoreTokens()){
            System.out.print(token.nextToken() + " ");
            
        }
    }
    
    
    // grammer rules
}
