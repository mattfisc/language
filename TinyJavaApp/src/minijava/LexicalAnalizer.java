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
    
    boolean program;
    String programName;

    
    public LexicalAnalizer(){
        program = false;
        programName = "";
    }
    
    
    public void expression(String data) {
        
         StringTokenizer st;
         String type,identifier,expression;
         
        
        st = new StringTokenizer(data, " ");
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
        String token = " ";
        st = new StringTokenizer(data, token);
        
        try {

            check = st.nextToken();
            System.out.println(check);

            // START PROGRAM
            if(check.equals("PROGRAM")){
                programName = st.nextToken();

            // BEGIN CODE BLOCK
            if(check.equals("BEGIN"))
                program = true;

                
            }
                
            
        } catch (Exception e) {
            System.out.println("No Program");
        }
    }
}

