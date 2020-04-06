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
    boolean block;
    
    public LexicalAnalizer(){
        program = false;
        block = false;
    }
    
    
    public void expression(String data) {
        
         StringTokenizer st;
         String type,identifier,expression;
         
        
        st = new StringTokenizer(data, " ");
        try {
            type = st.nextToken();
            identifier = st.nextToken();
            
            expression = st.nextToken();
            System.out.println("type: " + type + "\nidentifier: " + identifier 
                + "\nexpression: " + expression);
        } catch (Exception e) {
            System.out.println("error reading");
        }
    }
    
    // Search for a Program
    public void searchProgram(String data) {
       
        StringTokenizer st;
        String check,begin;
        String token = " ";
        st = new StringTokenizer(data, token);
        
        try {
            while(st.hasMoreTokens()){
                check = st.nextToken();

                if(check.equals("PROGRAM")){
                    programName = st.nextToken();
                    check = st.nextToken();
                    if(check.equals("BEGIN"))
                        program = true;

                }
            }
                
            
        } catch (Exception e) {
            System.out.println("No Program");
        }
    }
}

