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
    
    public static LinkedList<Token> list = new LinkedList<Token>();
    
    public TType type;
    public String text;
    
    public int row;// TOKEN ROW
    public int col;//  TOKEN COL
    
    // DEFAULT CONSTRUCTOR
    public Token(){
        
        row = 0;
        col = 0;
        text = "";
    }
    
    // CONSTRUCTOR
    public Token(TType type, String text, int row, int col){
        this.type = type;
        this.text = text;
        this.row = row;
        this.col = col;
    }
    
}
