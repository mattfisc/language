/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Matthew Fischer
 */
public class ParserTwo {
    
    Queue<Token> q = new LinkedList<Token>();
    boolean program = false;
    boolean begin = false;
    boolean end = false;
    
    public void parseCode(){
        int row = -1;
        for(Token t : Token.list){
            
            // LOOK FOR PROGRAM
            if(t.text == "class")
                program = true;
            
            // IS PROGRAM
            if(program == true){
                
                // LOOK FOR BEGINING
                if(t.text == "main")
                    begin = true;
                
                // IS PROGRAM
                if(begin == true){
                    
                    // IGNORE WHITE SPACE
                    if(TType.white_space == t.type && TType.comment == t.type)
                        Token.list.remove(t);

                    // INCREMENT ROW BY LINE TOKEN
                    if(TType.line == t.type){
                        Token.list.remove(t);
                        valid_check_last_line();
                        q.clear();
                        row++;
                    }
                    
                    // ADD ROW TO QUEUE
                    if(t.row == row){
                        q.add(t);
                    }
                            
                    
                
                    
                    
                    
                    
                    // LOOK FOR END
                    if(t.text == "END")
                        end = true;

                }
                
            }
                   
        }
    }
    
    public void valid_check_last_line(){
        
    }

    
}
