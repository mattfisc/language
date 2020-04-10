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
    private static LinkedList<Token> list;
    
    private TType type;
    private String text;
    
    private int line;// TOKEN ROW
    private int col;//  TOKEN COL
    
    // DEFAULT CONSTRUCTOR
    public Token(){
        line = 0;
        col = 0;
        text = "";
    }
    
    // CONSTRUCTOR
    public Token(TType type, String text, int line, int col){
        this.type = type;
        this.text = text;
        this.line = line;
        this.col = col;
    }
    
    // CONTAINS
    public boolean add(Token token){
        return list.add(token);
    }
    
    // PRINT ALL ATTRIBUTES
    public void printToken(){
        System.out.println("Type: " + this.type + " int:" 
                +this.text + "\nat row: " + this.line + " col: " 
                + this.col);
    }
}
