/*
 * By:     Matthew Fischer
 * Date:   
 */
package minijava;

import java.util.LinkedList;

/**
 *
 * @author Matthew Fischer
 */
public class Token {
    public static LinkedList<Token> list;
    
    TType type;
    String text;
    
    int line;// TOKEN ROW
    int col;//  TOKEN COL
    
    public Token(){
        line = 0;
        col = 0;
        
        text = "";
    }
    public Token(TType type, String text, int line, int col){
        this.type = type;
        this.text = text;
        this.line = line;
        this.col = col;
    }
    // contains //
    public boolean add(Token token){
        return list.add(token);
    }
    
    // printing errors
    public void printToken(){
        // print everything
        System.out.println(this.text);
    }
}
