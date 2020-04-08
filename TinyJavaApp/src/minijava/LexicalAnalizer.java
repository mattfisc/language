/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

import java.util.StringTokenizer;

/**
 *
 * @author Matthew Fischer
 */
public class LexicalAnalizer {
    
    Program program;
    String programName;
    
    String token;
    
    String[] operatorData;
    String[] type;
    String[] expression;
    
    public LexicalAnalizer(){
        this.operatorData = new String[]{"+", "-", "*", "/",">" , "<" , ">=" , "<=" , "==" , "!=", "&&", "||"};
        token = " ";
        
    }
    
    
    public void expression(String data) {
        
        StringTokenizer st;
        String type,identifier,expression;
         
        
            //token = " ";
       
        st = new StringTokenizer(data, token);
        try {
            type = st.nextToken();
            identifier = st.nextToken();
            expression = st.nextToken();
            
            System.out.println("Type: " + type + "\nIdentifier: " + identifier 
                + "\nExpression: " + expression);
        } catch (Exception e) {
            System.out.println("Error reading");
        }
    }
    
    // Search for a Program
    public void searchProgram(String data) {
       
        StringTokenizer st;
        String check;
        
        
        if(program == null)
            System.out.println("hello");
        
        st = new StringTokenizer(data, token);
        try {

            check = st.nextToken();
            System.out.println(check);

            // START PROGRAM
            if(check.equals("PROGRAM")){
                String name = st.nextToken();
                

            // BEGIN CODE BLOCK
            if(check.equals("BEGIN"))
                
                program = new Program(st.nextToken());
            }
                
            
        } catch (Exception e) {
            System.out.println("No Program");
        }
    }
}

